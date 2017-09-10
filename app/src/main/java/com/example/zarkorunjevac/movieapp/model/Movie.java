package com.example.zarkorunjevac.movieapp.model;

/**
 * Created by zarkorunjevac on 04/09/17.
 */

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Movie  implements Serializable{

    private static final String TAG=Movie.class.getCanonicalName();

    private Integer voteCount;
    private Integer id;
    private Boolean video;
    private Double voteAverage;
    private String title;
    private Double popularity;
    private String posterPath;
    private String originalLanguage;
    private String originalTitle;
    private List<Object> genreIds = null;
    private String backdropPath;
    private Boolean adult;
    private String overview;
    private String releaseDate;

    public enum Type {
        POPULAR, REGULAR
    }



    private String youTubeUrl;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath=jsonObject.getString("poster_path");
        this.originalTitle=jsonObject.getString("original_title");
        this.overview=jsonObject.getString("overview");
        this.backdropPath=jsonObject.getString("backdrop_path");
        this.voteAverage=jsonObject.getDouble("vote_average");
        this.id=jsonObject.getInt("id");
    }

    public static ArrayList<Movie> fromJSONARRay(JSONArray array){
        ArrayList<Movie> movies=new ArrayList<>();
        for(int x=0;x<array.length();x++){
            try {
                movies.add(new Movie(array.getJSONObject(x)));
            }catch (JSONException e){
                Log.e(TAG, "fromJSONARRay: ", e);
            }

        }

        return movies;
    }

    public Type getType(){
        if(this.isPopular()) return Type.POPULAR;
        else return Type.REGULAR;
    }

    public boolean isPopular(){
        return voteAverage>5;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342%s",posterPath);
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Object> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Object> genreIds) {
        this.genreIds = genreIds;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342%s",backdropPath);

    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getYouTubeUrl() {
        return youTubeUrl;
    }

    public void setYouTubeUrl(String youTubeUrl) {
        this.youTubeUrl = youTubeUrl;
    }

}