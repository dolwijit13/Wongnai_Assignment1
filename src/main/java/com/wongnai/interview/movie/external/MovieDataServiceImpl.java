package com.wongnai.interview.movie.external;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MovieDataServiceImpl implements MovieDataService
{
	public static final String MOVIE_DATA_URL = "https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/movies.json";

	@Autowired
	private RestOperations restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public MoviesResponse fetchAll()
	{
		// TODO:
		// Step 1 => Implement this method to download data from MOVIE_DATA_URL and fix
		// any error you may found.
		// Please noted that you must only read data remotely and only from given
		// source,
		// do not download and use local file or put the file anywhere else.

		InputStream is;
		try
		{
			// Read JSON
			is = new URL(MOVIE_DATA_URL).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONArray jsonArray;
			jsonArray = new JSONArray(jsonText);

			/*
			 * JSONObject jsonObject = jsonArray.getJSONObject(5); ArrayList<String> A =
			 * jsonArrayToArrayList(jsonObject.get("genres")); System.out.println(A);
			 */

			// *
			/// Crete MovieResponse 
			MoviesResponse moviesResponse = new MoviesResponse();
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String title = (String) jsonObject.get("title");
				int year = (int) jsonObject.get("year");
				ArrayList<String> cast = jsonArrayToArrayList(jsonObject.get("cast"));
				ArrayList<String> genres = jsonArrayToArrayList(jsonObject.get("genres"));

				moviesResponse.add(new MovieData(title, year, cast, genres));
			}
			// */

			is.close();
			return moviesResponse;
			
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private static String readAll(Reader rd) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1)
		{
			sb.append((char) cp);
		}
		return sb.toString();
	}

	private ArrayList<String> jsonArrayToArrayList(Object obj)
	{
		JSONArray jsonArray = (JSONArray) obj;

		ArrayList<String> ans = new ArrayList<String>();
		for (int i = 0; i < jsonArray.length(); i++)
		{
			try
			{
				ans.add(jsonArray.getString(i));
			} catch (JSONException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ans;
	}
}
