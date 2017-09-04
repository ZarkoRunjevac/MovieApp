package com.example.zarkorunjevac.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.zarkorunjevac.movieapp.adapters.MovieArrayAdapter;
import com.example.zarkorunjevac.movieapp.model.Movie;
import com.example.zarkorunjevac.movieapp.utils.Config;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieListActivity extends AppCompatActivity {

    private static final String TAG=MovieListActivity.class.getCanonicalName();

    private ArrayList<Movie> movies;

    private MovieArrayAdapter mMovieArrayAdapter;

    private ListView mMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        movies=new ArrayList<>();
        mMovieList=(ListView)findViewById(R.id.lstMovies);
        mMovieArrayAdapter=new MovieArrayAdapter(this, movies);
        mMovieList.setAdapter(mMovieArrayAdapter);

        AsyncHttpClient client=new AsyncHttpClient();

        client.get(Config.URL, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonArray=null;

               try {
                   movieJsonArray=response.getJSONArray("results");
                   movies.addAll(Movie.fromJSONARRay(movieJsonArray));
                   mMovieArrayAdapter.notifyDataSetChanged();

               }catch (JSONException e){
                   Log.e(TAG, "onSuccess: ", e);
               }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
