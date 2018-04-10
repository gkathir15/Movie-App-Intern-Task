package com.guru.nowplaying.helpers;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guru.nowplaying.pojos.Cast;
import com.guru.nowplaying.pojos.MovieData;
import com.guru.nowplaying.pojos.MovieListingData;
import com.guru.nowplaying.pojos.Videos;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Guru on 04-04-2018.
 */

public class JsonParserHelper {

    private ObjectMapper mObjectMapper = new ObjectMapper();
    public static String TAG = "JsonParserHelper";

    /**
     *parses data for now playing list saves to a array list
     * @param pJsonResponse
     * @return array list of saved values for NOW PLAY LIST
     */
    public ArrayList<MovieListingData> ParseNowPlayingList(String pJsonResponse)
    {
        Log.d(TAG+"raw Response to parser",pJsonResponse);

        ArrayList<MovieListingData> lNowPlayingData = null;
        try {
            lNowPlayingData = mObjectMapper.readValue(pJsonResponse,new TypeReference<ArrayList<MovieListingData>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG,"Problem with parsing");
        }
        return lNowPlayingData;
    }

    /**
     * method to parse  the MovieData
     * @param pJsonResponse
     * @return MovieData object
     */
    public MovieData parseMovieData(String pJsonResponse)
    {

        Log.d(TAG+"raw Response to parser",pJsonResponse);

        MovieData lMovieData;
        try {
            lMovieData = mObjectMapper.readValue(pJsonResponse,MovieData.class);
            return lMovieData;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG,"Problem with parsing");
        }

        return null;

    }

    /**
     * method to parse the video data from response
     * @param pJsonResponse
     * @return array list of Videos parsed.
     */
    public ArrayList<Videos> ParseNowVideosList(String pJsonResponse)
    {
        Log.d(TAG+"raw Response to parser",pJsonResponse);
        TypeReference<ArrayList<Videos>>lTypeReference = new TypeReference<ArrayList<Videos>>() {
        };

        ArrayList<Videos> lVideosList = null;
        try {
            lVideosList = mObjectMapper.readValue(pJsonResponse,lTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG,"Problem with parsing");
        }

        return lVideosList;
    }

    /**
     * method to parse the cast from response to arrayList of Cast's object.
     * @param pJsonResponse
     * @return array list of cast's ArrayList
     */
    public ArrayList<Cast> ParseCastList(String pJsonResponse)
    {
        Log.d(TAG+"raw Response to parser",pJsonResponse);
        TypeReference<ArrayList<Cast>>lTypeReference = new TypeReference<ArrayList<Cast>>() {
        };
        ArrayList<Cast> lCastList = null;
        try {
            lCastList = mObjectMapper.readValue(pJsonResponse,lTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG,"Problem with parsing");
        }

        return lCastList;
    }



}
