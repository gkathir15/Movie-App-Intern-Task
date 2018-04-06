package com.guru.nowplaying.datamodel;

import android.util.Log;

import com.guru.nowplaying.constants.Constants;

/**
 * Created by Guru on 04-04-2018.
 */

public class MovieDataById {

    private String vote_average;
    private String runtime;
    private String id;
    private String title;
    private String original_title;
    private String backdrop_path;
    private String status;
    private String homepage;
    private String overview;
    private String release_date;
    private String poster_path;
    private String tagline;
    private boolean isFavourite;

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    /**
     *
     * @param pMovieID
     * @return the constructed URl
     */
    public String constructURL(String pMovieID)
        {

            https://api.themoviedb.org/3/movie/284054?api_key=461e99d35de36a0e9680f40b1b06073c&append_to_response=videos,person
            // append to response of youtube videos and cast data;


            Log.d("ConstructedURL",Constants.ROOT_URL+pMovieID+Constants.API_PREFIX+Constants.API_KEY_V3+"&"+Constants.APPEND_TO_RESPONSE+Constants.VIDEOS+","+Constants.CREDITS);
            return Constants.ROOT_URL+pMovieID+Constants.API_PREFIX+Constants.API_KEY_V3+"&"+Constants.APPEND_TO_RESPONSE+Constants.VIDEOS+","+Constants.CREDITS;

        }
    }

