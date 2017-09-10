package com.example.zarkorunjevac.movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zarkorunjevac.movieapp.ui.activity.MovieListActivity;
import com.example.zarkorunjevac.movieapp.R;
import com.example.zarkorunjevac.movieapp.model.Movie;
import com.example.zarkorunjevac.movieapp.ui.activity.MovieDetailActivity;
import com.example.zarkorunjevac.movieapp.ui.activity.YouTubeActivity;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

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

  @Override
  public int getViewTypeCount() {
    return Movie.Type.values().length;
  }

  @Override
  public int getItemViewType(int position) {
    return getItem(position).getType().ordinal();
  }



  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

    final Movie movie = getItem(position);
    ViewHolder viewHolder;
    if (convertView == null) {

      int type=getItemViewType(position);

      convertView = getInflatedLayoutForType(type);
      viewHolder = new ViewHolder(convertView);
      convertView.setTag(viewHolder);

    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }

    convertView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent=new Intent(mContext.get(), MovieDetailActivity.class);
        intent.putExtra("movie",movie);
        mContext.get().startActivity(intent);
      }
    });
    if(movie.isPopular()){
        viewHolder.ivImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext.get(), YouTubeActivity.class);
                intent.putExtra("movie",movie);
                mContext.get().startActivity(intent);
            }
        });
    }


    viewHolder.tvTitle.setText(movie.getOriginalTitle());

    viewHolder.tvOverview.setText(movie.getOverview());

    viewHolder.ivImage.setImageResource(0);
    int orientation = mContext.get().getResources().getConfiguration().orientation;
    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
      if (movie.isPopular()) {
        Picasso.with(getContext())
            .load(movie.getBackdropPath())
            .transform(new RoundedCornersTransformation(10, 10))
            .placeholder(R.drawable.placeholder)
            .into(viewHolder.ivImage);
        Log.d(TAG, "getView: "+movie.getBackdropPath());
      } else {
        Picasso.with(getContext())
            .load(movie.getPosterPath())
            .transform(new RoundedCornersTransformation(10, 10))
            .placeholder(R.drawable.placeholder)
            .into(viewHolder.ivImage);
      }

    } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      if (movie.isPopular()) {
        Picasso.with(getContext())
            .load(movie.getBackdropPath())
            .transform(new RoundedCornersTransformation(10, 10))
            .placeholder(R.drawable.placeholder)
            .into(viewHolder.ivImage);

      } else {
        Picasso.with(getContext())
            .load(movie.getPosterPath())
            .transform(new RoundedCornersTransformation(10, 10))
            .placeholder(R.drawable.placeholder)
            .into(viewHolder.ivImage);
      }
    }

    return convertView;
  }

  private View getInflatedLayoutForType(int type) {
    if (type==Movie.Type.POPULAR.ordinal()) {
      LayoutInflater inflater = LayoutInflater.from(getContext());
      return inflater.from(mContext.get()).inflate(R.layout.item_popular_movie, null);
    } else {
      LayoutInflater inflater = LayoutInflater.from(getContext());
      return inflater.from(mContext.get()).inflate(R.layout.item_movie, null);
    }

  }
}
