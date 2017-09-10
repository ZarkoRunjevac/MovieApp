package com.example.zarkorunjevac.movieapp.ui.activity;

import android.os.Bundle;

import com.example.zarkorunjevac.movieapp.BuildConfig;
import com.example.zarkorunjevac.movieapp.R;
import com.example.zarkorunjevac.movieapp.model.Movie;
import com.example.zarkorunjevac.movieapp.utils.Config;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class YouTubeActivity extends YouTubeBaseActivity {

    @BindView(R.id.player) YouTubePlayerView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube);
        ButterKnife.bind(this);

        final Movie movie=(Movie)getIntent().getSerializableExtra("movie");

        String url= Config.getVideoUrl(movie.getId());

        final YouTubePlayerView youTubePlayerView = player;

        AsyncHttpClient client=new AsyncHttpClient();
        client.get(url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray videoJsonResults = null;

                try {
                    videoJsonResults = response.getJSONArray("results");
                    movie.setYouTubeUrl(videoJsonResults.getJSONObject(0).getString("key"));

                    youTubePlayerView.initialize(BuildConfig.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                            youTubePlayer.loadVideo(movie.getYouTubeUrl());
                        }

                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
