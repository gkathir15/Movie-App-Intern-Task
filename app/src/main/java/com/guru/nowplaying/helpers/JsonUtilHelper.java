package com.guru.nowplaying.helpers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Guru on 04-04-2018.
 */

public class JsonUtilHelper {

    JSONObject mJsonObject;
    public static String TAG ="JsonUtilHelper";

    public String jsonObjectToArray(String pObjKey,String pRawJson)
    {
        String lStripedArray= "error";
        try {
            mJsonObject = new JSONObject(pRawJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
             JSONArray jsonArray = mJsonObject.getJSONArray(pObjKey);
             Log.d(TAG,"JSON Array Size"+jsonArray.length());
             lStripedArray = jsonArray.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG,lStripedArray);


        return lStripedArray;
    }
}
