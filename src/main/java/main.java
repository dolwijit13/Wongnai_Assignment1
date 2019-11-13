import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.wongnai.interview.movie.external.MovieDataServiceImpl;
import com.wongnai.interview.movie.external.MoviesResponse;

import antlr.collections.List;

public class main
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		/*
		 * MovieDataServiceImpl tmp = new MovieDataServiceImpl(); tmp.fetchAll();
		 */
		
		/*
		MovieDataServiceImpl tmp = new MovieDataServiceImpl();
		tmp.fetchAll();
		*/
		
		MovieDataServiceImpl movieDataService = new MovieDataServiceImpl();
		MoviesResponse result = movieDataService.fetchAll();
		//System.out.println(result.size());
		System.out.println(result.get(0).getGenres().getClass());
		System.out.println(Arrays.asList("").getClass());
		
		/*
		String test = "test";
		System.out.println(test.getClass());
		*/
		
		//List<String>L = new List<String>;
		//ArrayList<String>A = new ArrayList<String>();
		/*
		String s= "1,2,3";
		String[]s2 = s.split(",");
		ArrayList<String>A = new ArrayList<String>(Arrays.asList(s2));
		System.out.println(A);
		*/
	}

}
