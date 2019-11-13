import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.wongnai.interview.movie.external.MovieDataServiceImpl;

public class main
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		/*
		 * MovieDataServiceImpl tmp = new MovieDataServiceImpl(); tmp.fetchAll();
		 */
		MovieDataServiceImpl tmp = new MovieDataServiceImpl();
		tmp.fetchAll();
	}

}
