package com.wongnai.interview.movie.sync;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.external.MovieData;
import com.wongnai.interview.movie.external.MovieDataService;
import com.wongnai.interview.movie.external.MoviesResponse;
import com.wongnai.interview.movie.search.InvertedIndexMovieSearchService;

@Component
public class MovieDataSynchronizer
{
	@Autowired
	private MovieDataService movieDataService;

	@Autowired
	private MovieRepository movieRepository;

	@Transactional
	public void forceSync()
	{
		// TODO: implement this to sync movie into repository\
		MoviesResponse moviesResponse = movieDataService.fetchAll();
		InvertedIndexMovieSearchService.initialInvertedIndex();
		for (int i = 0; i < moviesResponse.size(); i++)
		{
			MovieData movieData = moviesResponse.get(i);
			movieRepository.save(new Movie(movieData));
			InvertedIndexMovieSearchService.addInvertedIndex(movieData);
		}
	}
}
