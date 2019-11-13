package com.wongnai.interview.movie.external;

import java.util.List;

public class MovieData
{
	private String title;
	private int year;
	private List<String> cast;
	private List<String> genres;

	public MovieData()
	{
	}
	
	public MovieData(String title,int year,List<String> cast,List<String> genres)
	{
		setTitle(title);
		setYear(year);
		setCast(cast);
		setGenres(genres);
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public List<String> getCast()
	{
		return cast;
	}

	public void setCast(List<String> cast)
	{
		this.cast = cast;
	}

	public List<String> getGenres()
	{
		return genres;
	}

	public void setGenres(List<String> genres)
	{
		this.genres = genres;
	}
	
	//For print test
	public String toString()
	{
		return "Title : "+title + " | Year : "+year+" | Cast : "+cast+" | Genres : "+genres+"\n";
	}
}
