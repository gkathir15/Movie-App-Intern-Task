package com.guru.nowplaying.helpers;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guru.nowplaying.datamodel.CastJdo;
import com.guru.nowplaying.datamodel.MovieDataById;
import com.guru.nowplaying.datamodel.NowPlaying;
import com.guru.nowplaying.datamodel.VideosJDO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Guru on 04-04-2018.
 */

public class JsonParserHelper {

    private ObjectMapper mObjectMapper = new ObjectMapper();
    public static String TAG = "JsonParserHelper";



    /**
     *
     * @param pJsonResponse
     * @return
     */
    public ArrayList<NowPlaying> ParseNowPlayingList(String pJsonResponse)
    {
        Log.d(TAG+"raw Response to parser",pJsonResponse);
        TypeReference<ArrayList<NowPlaying>>lTypeReference = new TypeReference<ArrayList<NowPlaying>>() {
        };

        ArrayList<NowPlaying> lNowPlaying = null;
        try {
            lNowPlaying = mObjectMapper.readValue(pJsonResponse,new TypeReference<ArrayList<NowPlaying>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG,"Problem with parsing");
        }
        //readValues(pJsonObject, lTypeReference);

        return lNowPlaying;
    }

    /**
     *
     * @param pJsonResponse
     * @return
     */
    public MovieDataById parseMovieData(String pJsonResponse)
    {
        TypeReference<MovieDataById> lTypeReference = new TypeReference<MovieDataById>() {
        };
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY,false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS,false);
        // mObjectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE,true);
        MovieDataById lMovieDataById = new MovieDataById();
        try {
            lMovieDataById = mObjectMapper.readValue(pJsonResponse,lTypeReference);
            return  lMovieDataById;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG,"Problem with parsing");
        }

        return null;

    }

    /**
     *
     * @param pJsonResponse
     * @return
     */
    public ArrayList<VideosJDO> ParseNowVideosList(String pJsonResponse)
    {
        Log.d(TAG+"raw Response to parser",pJsonResponse);
        TypeReference<ArrayList<VideosJDO>>lTypeReference = new TypeReference<ArrayList<VideosJDO>>() {
        };
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY,false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS,false);
        // mObjectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE,true);
        ArrayList<VideosJDO> lVideosList = null;
        try {
            lVideosList = mObjectMapper.readValue(pJsonResponse,lTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG,"Problem with parsing");
        }
        //readValues(pJsonObject, lTypeReference);

        return lVideosList;
    }

    /**
     *
     * @param pJsonResponse
     * @return
     */
    public ArrayList<CastJdo> ParseCastList(String pJsonResponse)
    {
        Log.d(TAG+"raw Response to parser",pJsonResponse);
        TypeReference<ArrayList<CastJdo>>lTypeReference = new TypeReference<ArrayList<CastJdo>>() {
        };
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY,false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS,false);
        // mObjectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE,true);
        ArrayList<CastJdo> lCastList = null;
        try {
            lCastList = mObjectMapper.readValue(pJsonResponse,lTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG,"Problem with parsing");
        }
        //readValues(pJsonObject, lTypeReference);

        return lCastList;
    }



}
