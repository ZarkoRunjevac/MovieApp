package com.example.zarkorunjevac.movieapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.zarkorunjevac.movieapp.R;
import com.example.zarkorunjevac.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

  @BindView(R.id.ivMovieImage)
  ImageView ivImage;
  @BindView(R.id.tvTitle)
  TextView tvTitle;
  @BindView(R.id.tvDescription)
  TextView tvDescription;
  @BindView(R.id.rbRating)
  RatingBar rbRating;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_detail);
    ButterKnife.bind(this);

    final Movie movie=(Movie)getIntent().getSerializableExtra("movie");

    tvTitle.setText(movie.getOriginalTitle());

    tvDescription.setText(movie.getOverview());
    rbRating.setRating((movie.getVoteAverage().floatValue()));


    Picasso.with(this)
        .load(movie.getBackdropPath())
        .placeholder(R.drawable.placeholder)
        .into(ivImage);

      ivImage.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(MovieDetailActivity.this, YouTubeActivity.class);
              intent.putExtra("movie",movie);
              startActivity(intent);
          }
      });

  }
}
