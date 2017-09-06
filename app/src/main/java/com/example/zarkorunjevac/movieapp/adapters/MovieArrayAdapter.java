package com.example.zarkorunjevac.movieapp.adapters;

import static com.example.zarkorunjevac.movieapp.R.id.tvOverview;
import static com.example.zarkorunjevac.movieapp.R.id.tvTitle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zarkorunjevac.movieapp.MovieListActivity;
import com.example.zarkorunjevac.movieapp.R;
import com.example.zarkorunjevac.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zarkorunjevac on 04/09/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static final String TAG= MovieListActivity.class.getCanonicalName();

    private static class ViewHolder{
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1,movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Movie movie=getItem(position);
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.item_movie,parent,false);
            viewHolder.tvTitle=(TextView)convertView.findViewById(tvTitle);
            viewHolder.tvOverview=(TextView)convertView.findViewById(tvOverview);
            viewHolder.ivImage=(ImageView)convertView.findViewById(R.id.ivMovieImage);

            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }



        viewHolder.ivImage.setImageResource(0);



        viewHolder.tvTitle.setText(movie.getOriginalTitle());

        viewHolder.tvOverview.setText(movie.getOverview());

        Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.ivImage);

        return convertView;
    }
}
