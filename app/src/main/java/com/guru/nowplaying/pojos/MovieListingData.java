package com.guru.nowplaying.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Guru on 04-04-2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieListingData {
    public MovieListingData() {
    }

    public MovieListingData(String backdropPath, String id, String title, String releaseDate, String vote, String posterPath) {
        this.backdropPath = backdropPath;
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.voteAverage = vote;
        this.posterPath = posterPath;
    }

    private String url;


    @JsonProperty("vote_average")
    private String voteAverage;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    private String adult;

    private String id;

    private String title;


    private String overview;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty("genre_ids")
    private String[] genreIds;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty("vote_count")
    private String voteCount;

    @JsonProperty("poster_path")
    private String posterPath;

    private String video;

    private String popularity;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVoteAverage()
    {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage)
    {
        this.voteAverage = voteAverage;
    }

    public String getBackdropPath()
    {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath)
    {
        this.backdropPath = backdropPath;
    }

    public String getAdult ()
    {
        return adult;
    }

    public void setAdult (String adult)
    {
        this.adult = adult;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getOverview ()
    {
        return overview;
    }

    public void setOverview (String overview)
    {
        this.overview = overview;
    }

    public String getOriginalLanguage()
    {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage)
    {
        this.originalLanguage = originalLanguage;
    }

    public String[] getGenreIds()
    {
        return genreIds;
    }

    public void setGenreIds(String[] genreIds)
    {
        this.genreIds = genreIds;
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public String getOriginalTitle()
    {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle)
    {
        this.originalTitle = originalTitle;
    }

    public String getVoteCount()
    {
        return voteCount;
    }

    public void setVoteCount(String voteCount)
    {
        this.voteCount = voteCount;
    }

    public String getPosterPath()
    {
        return posterPath;
    }

    public void setPosterPath(String posterPath)
    {
        this.posterPath = posterPath;
    }

    public String getVideo ()
    {
        return video;
    }

    public void setVideo (String video)
    {
        this.video = video;
    }

    public String getPopularity ()
    {
        return popularity;
    }

    public void setPopularity (String popularity)
    {
        this.popularity = popularity;
    }

}
