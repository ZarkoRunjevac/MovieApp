package com.example.zarkorunjevac.movieapp.utils;

/**
 * Created by zarkorunjevac on 04/09/17.
 */

public class Config {

    public static String URL="https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    public static String getVideoUrl(int id){
        return String.format("https://api.themoviedb.org/3/movie/%s/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US", id);

    }
}
