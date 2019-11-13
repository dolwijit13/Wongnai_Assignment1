package com.wongnai.interview.movie.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieSearchService;
import com.wongnai.interview.movie.external.MovieData;
import com.wongnai.interview.movie.external.MovieDataService;
import com.wongnai.interview.movie.external.MovieDataServiceImpl;
import com.wongnai.interview.movie.external.MoviesResponse;

@Component("simpleMovieSearchService")
public class SimpleMovieSearchService implements MovieSearchService
{
	@Autowired
	private MovieDataService movieDataService;

	private boolean isSameMovie(String movieName, String queryText)
	{
		String[] words = movieName.split(" ");
		for (int i = 0; i < words.length; i++)
		{
			if (words[i].toLowerCase().compareTo(queryText) == 0)
				return true;
		}
		return false;
	}

	@Override
	public List<Movie> search(String queryText)
	{
		// TODO: Step 2 => Implement this method by using data from MovieDataService
		// All test in SimpleMovieSearchServiceIntegrationTest must pass.
		// Please do not change @Component annotation on this class
		queryText = queryText.toLowerCase();

		List<Movie> ans = new ArrayList<Movie>();
		MovieDataServiceImpl movieDataService = new MovieDataServiceImpl();
		MoviesResponse result = movieDataService.fetchAll();
		for (int i = 0; i < result.size(); i++)
		{
			MovieData movieData = result.get(i);
			if (isSameMovie(movieData.getTitle(), queryText))
				ans.add(new Movie(movieData.getId(), movieData.getTitle(), movieData.getCast()));
		}
		return ans;
	}
}
