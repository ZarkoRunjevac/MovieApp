package com.example.zarkorunjevac.movieapp.ui.activity;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.zarkorunjevac.movieapp.R;
import com.example.zarkorunjevac.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

  @BindView(R.id.imageView)
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

    Movie movie=(Movie)getIntent().getSerializableExtra("movie");

    tvTitle.setText(movie.getOriginalTitle());

    tvDescription.setText(movie.getOverview());
    rbRating.setRating((movie.getVoteAverage().floatValue()));


    Picasso.with(this)
        .load(movie.getBackdropPath())
        .placeholder(R.drawable.placeholder)
        .into(ivImage);

  }
}
