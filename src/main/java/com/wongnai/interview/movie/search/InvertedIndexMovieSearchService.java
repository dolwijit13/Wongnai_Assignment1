package com.wongnai.interview.movie.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.MovieSearchService;
import com.wongnai.interview.movie.external.MovieData;

@Component("invertedIndexMovieSearchService")
@DependsOn("movieDatabaseInitializer")
public class InvertedIndexMovieSearchService implements MovieSearchService
{
	@Autowired
	private MovieRepository movieRepository;
	private static HashMap<String, ArrayList<Long>> invertedIndex;

	@Override
	public List<Movie> search(String queryText)
	{
		// TODO: Step 4 => Please implement in-memory inverted index to search movie by
		// keyword.
		// You must find a way to build inverted index before you do an actual search.
		// Inverted index would looks like this:
		// -------------------------------
		// | Term | Movie Ids |
		// -------------------------------
		// | Star | 5, 8, 1 |
		// | War | 5, 2 |
		// | Trek | 1, 8 |
		// -------------------------------
		// When you search with keyword "Star", you will know immediately, by looking at
		// Term column, and see that
		// there are 3 movie ids contains this word -- 1,5,8. Then, you can use these
		// ids to find full movie object from repository.
		// Another case is when you find with keyword "Star War", there are 2 terms,
		// Star and War, then you lookup
		// from inverted index for Star and for War so that you get movie ids 1,5,8 for
		// Star and 2,5 for War. The result that
		// you have to return can be union or intersection of those 2 sets of ids.
		// By the way, in this assignment, you must use intersection so that it left for
		// just movie id 5.
		List<Movie> ans = new ArrayList<Movie>();
		String[] terms = queryText.toUpperCase().split(" ");
		ArrayList<ArrayList<Long>> idxsForEachWord = new ArrayList<ArrayList<Long>>();

		// Find List of index for each word
		for (int i = 0; i < terms.length; i++)
		{
			ArrayList<Long> idxs = new ArrayList<Long>();
			if (invertedIndex.containsKey(terms[i]))
				idxs = invertedIndex.get(terms[i]);
			idxsForEachWord.add(idxs);
		}

		// Intersect index from all word

		ArrayList<Long> ansIdx = intersectNSortedList(idxsForEachWord);
		for (int i = 0; i < ansIdx.size(); i++)
		{
			Movie movie = movieRepository.findById(ansIdx.get(i)).get();
			ans.add(movie);
		}

		return ans;
	}

	private ArrayList<Long> intersectNSortedList(ArrayList<ArrayList<Long>> A)
	{
		if (A.size() <= 0)
			return new ArrayList<Long>();

		ArrayList<Long> ans = A.get(0);

		for (int i = 1; i < A.size(); i++)
			ans = intersect2SortedList(ans, A.get(i));

		return ans;
	}

	private ArrayList<Long> intersect2SortedList(ArrayList<Long> A, ArrayList<Long> B)
	{
		ArrayList<Long> ans = new ArrayList<Long>();
		int i = 0, j = 0;
		while (i < A.size() && j < B.size())
		{
			Long dataA = A.get(i);
			Long dataB = B.get(j);
			if (dataA.equals(dataB))
			{
				ans.add(dataA);
				i++;
				j++;
			} else if (dataA < dataB)
				i++;
			else
				j++;
		}
		return ans;
	}

	public static void initialInvertedIndex()
	{
		invertedIndex = new HashMap<String, ArrayList<Long>>();
	}
	
	public static void addInvertedIndex(MovieData movieData)
	{
		String[] terms = movieData.getTitle().split(" ");
		long idx = movieData.getId();
		for (int i = 0; i < terms.length; i++)
		{
			String string = terms[i].toUpperCase();
			invertedIndex.putIfAbsent(string, new ArrayList<Long>());
			invertedIndex.get(string).add(idx);
		}
	}
}
