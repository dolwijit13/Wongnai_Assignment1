package com.wongnai.interview.movie.sync;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.external.MovieDataService;
import com.wongnai.interview.movie.external.MoviesResponse;

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
		for(int i=0;i<moviesResponse.size();i++) movieRepository.save(new Movie(moviesResponse.get(i)));
	}
}
