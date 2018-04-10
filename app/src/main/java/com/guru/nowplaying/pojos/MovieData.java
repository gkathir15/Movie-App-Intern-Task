package com.guru.nowplaying.pojos;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.guru.nowplaying.constants.Constants;

/**
 * Created by Guru on 04-04-2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieData {

    @JsonProperty("vote_average")
    private String voteAverage;

    private String runtime;
    private String id;
    private String title;

    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    private String status;
    private String homepage;
    private String overview;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("tagline")
    private String tagline;

    private boolean isFavourite;
    private String youtubeVideoKey;
    private String castJsonArray;
    private boolean isDetailPresent;

    public MovieData(String voteAverage, String runtime, String id, String title, String originalTitle, String backdropPath, String status, String homepage, String overview, String releaseDate, String posterPath, String tagline, boolean isFavourite, String youtubeVideoKey, String castJsonArray, boolean isDetailPresent) {
        this.voteAverage = voteAverage;
        this.runtime = runtime;
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
        this.status = status;
        this.homepage = homepage;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.tagline = tagline;
        this.isFavourite = isFavourite;
        this.youtubeVideoKey = youtubeVideoKey;
        this.castJsonArray = castJsonArray;
        this.isDetailPresent = isDetailPresent;
    }

    public MovieData() {

    }

    public boolean isDetailPresent() {
        return isDetailPresent;
    }

    public void setDetailPresent(boolean detailPresent) {
        isDetailPresent = detailPresent;
    }

    public String getCastJsonArray() {
        return castJsonArray;
    }

    public void setCastJsonArray(String castJsonArray) {
        this.castJsonArray = castJsonArray;
    }

    public String getYoutubeVideoKey() {
        return youtubeVideoKey;
    }

    public void setYoutubeVideoKey(String youtubeVideoKey) {
        this.youtubeVideoKey = youtubeVideoKey;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
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

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

}

