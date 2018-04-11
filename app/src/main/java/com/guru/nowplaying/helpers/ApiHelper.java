package com.guru.nowplaying.helpers;

import android.util.Log;

import com.guru.nowplaying.constants.Constants;

/**
 * Created by Guru on 10-04-2018.
 * class containing methods to generate url based on arguments
 */

public class ApiHelper {

    /**
     *construct url to get movie details by movie ID
     * @param pMovieID
     * @return the constructed URl
     */
    public String constructUrlByMovieId(String pMovieID)
    {

        https://api.themoviedb.org/3/movie/284054?api_key=461e99d35de36a0e9680f40b1b06073c&append_to_response=videos,person
        // append to response of youtube videos and cast data;


        Log.d("ConstructedURL", Constants.ROOT_URL+pMovieID+Constants.API_PREFIX+Constants.API_KEY_V3+"&"+Constants.APPEND_TO_RESPONSE+Constants.VIDEOS+","+Constants.CREDITS);
        return Constants.ROOT_URL+pMovieID+Constants.API_PREFIX+Constants.API_KEY_V3+"&"+Constants.APPEND_TO_RESPONSE+Constants.VIDEOS+","+Constants.CREDITS;

    }

    /**
     * method to construct url by page for now playing list.
     * @param pPage
     * @return
     */
    public String constructNowPlayingUrlByPage( String pPage)
    {
        //Sample URL  https://api.themoviedb.org/3/movie/now_playing?api_key=461e99d35de36a0e9680f40b1b06073c&page=1

        Log.d("ConstructedURL",Constants.ROOT_URL+Constants.NOW_PLAYING+ Constants.API_PREFIX+Constants.API_KEY_V3+"&"+Constants.PAGE+pPage);
        return Constants.ROOT_URL+Constants.NOW_PLAYING+ Constants.API_PREFIX+Constants.API_KEY_V3+"&"+Constants.PAGE+pPage;

    }

    public String constructUpcomingMoviesUrlByPage( String pPage)
    {
        //Sample URL  https://api.themoviedb.org/3/movie/now_playing?api_key=461e99d35de36a0e9680f40b1b06073c&page=1

        Log.d("ConstructedURL",Constants.ROOT_URL+Constants.UPCOMING+ Constants.API_PREFIX+Constants.API_KEY_V3+"&"+Constants.PAGE+pPage);
        return Constants.ROOT_URL+Constants.UPCOMING+ Constants.API_PREFIX+Constants.API_KEY_V3+"&"+Constants.PAGE+pPage;

    }

}
