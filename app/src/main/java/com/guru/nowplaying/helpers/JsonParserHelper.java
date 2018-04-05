package com.guru.nowplaying.helpers;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guru.nowplaying.datamodel.NowPlayingList;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guru on 04-04-2018.
 */

public class JsonParserHelper {

    private ObjectMapper mObjectMapper = new ObjectMapper();
    public static String TAG = "JsonParserHelper";





    public ArrayList<NowPlayingList> ParseNowPlayingList(String pJsonResponse)
    {
        Log.d(TAG+"raw Response to parser",pJsonResponse);
        TypeReference<ArrayList<NowPlayingList>>lTypeReference = new TypeReference<ArrayList<NowPlayingList>>() {
        };
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY,false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS,false);
       // mObjectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE,true);
        ArrayList<NowPlayingList> lNowPlayingList = null;
        try {
            lNowPlayingList = mObjectMapper.readValue(pJsonResponse,lTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG,"Problem with parsing");
        }
        //readValues(pJsonObject, lTypeReference);

        return lNowPlayingList;
    }

}
