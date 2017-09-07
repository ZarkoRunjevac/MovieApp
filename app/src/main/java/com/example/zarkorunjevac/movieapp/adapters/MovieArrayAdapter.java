package com.example.zarkorunjevac.movieapp.adapters;

import static com.example.zarkorunjevac.movieapp.R.id.tvOverview;
import static com.example.zarkorunjevac.movieapp.R.id.tvTitle;
import static java.lang.System.load;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.zarkorunjevac.movieapp.MovieListActivity;
import com.example.zarkorunjevac.movieapp.R;
import com.example.zarkorunjevac.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by zarkorunjevac on 04/09/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

  private static final String TAG = MovieListActivity.class.getCanonicalName();
  private final WeakReference<Context> mContext;

  static class ViewHolder {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvOverview)
    TextView tvOverview;
    @BindView(R.id.ivMovieImage)
    ImageView ivImage;

    public ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }

  public MovieArrayAdapter(Context context, List<Movie> movies) {
    super(context, android.R.layout.simple_list_item_1, movies);
    mContext = new WeakReference<Context>(context);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

    Movie movie = getItem(position);
    ViewHolder viewHolder;
    if (convertView == null) {

      LayoutInflater inflater = LayoutInflater.from(getContext());
      convertView = getLayout(movie);
      viewHolder = new ViewHolder(convertView);
      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }

    viewHolder.tvTitle.setText(movie.getOriginalTitle());

    viewHolder.tvOverview.setText(movie.getOverview());

    viewHolder.ivImage.setImageResource(0);
    int orientation = mContext.get().getResources().getConfiguration().orientation;
    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
      if (movie.isPopular()) {
        Picasso.with(getContext())
            .load(movie.getBackdropPath())
            .placeholder(R.drawable.placeholder)
            .into(viewHolder.ivImage);
      } else {
        Picasso.with(getContext())
            .load(movie.getPosterPath())
            .placeholder(R.drawable.placeholder)
            .into(viewHolder.ivImage);
      }

    } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      if (movie.isPopular()) {
        Picasso.with(getContext())
            .load(movie.getBackdropPath())
            .placeholder(R.drawable.placeholder)
            .into(viewHolder.ivImage);
      } else {
        Picasso.with(getContext())
            .load(movie.getPosterPath())
            .placeholder(R.drawable.placeholder)
            .into(viewHolder.ivImage);
      }
    }

    return convertView;
  }

  private View getLayout(Movie movie) {
    if (movie.isPopular()) {
      LayoutInflater inflater = LayoutInflater.from(getContext());
      return inflater.from(mContext.get()).inflate(R.layout.item_popular_movie, null);
    } else {
      LayoutInflater inflater = LayoutInflater.from(getContext());
      return inflater.from(mContext.get()).inflate(R.layout.item_movie, null);
    }
  }
}
