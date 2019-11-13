import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.external.MovieDataServiceImpl;
import com.wongnai.interview.movie.external.MoviesResponse;
import com.wongnai.interview.movie.search.SimpleMovieSearchService;

import antlr.collections.List;

public class main
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		/*
		 * MovieDataServiceImpl tmp = new MovieDataServiceImpl(); tmp.fetchAll();
		 */
		
		//System.out.println("shasdasdsdasdasdasdiba".compareTo("akitaadasd"));
		
		SimpleMovieSearchService simpleMovieSearchService = new SimpleMovieSearchService();
		java.util.List<Movie> tmp = simpleMovieSearchService.search("Glorious");
		for(int i=0;i<tmp.size();i++)
		{
			System.out.println(tmp.get(i).getName());
		}
	}

}
