package com.guru.nowplaying.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Display;

/**
 * Created by Guru on 05-04-2018.
 */

public class SharedPreferencesHelper {

    public String mNowPlayData ="nowPlayData";
    public String  mIsNowPlayingDataInDb ="isNowPlayDataPresent";
    public  static String TAG = "SharedPreferencesHelper";

    /**
     *
     * @param isStored
     * @param pContext
     */
    public void setNowPlayingDataStored(boolean isStored, Context pContext)
    {
        SharedPreferences lSharedPreferences = pContext.getSharedPreferences(mNowPlayData,Context.MODE_PRIVATE);
        SharedPreferences.Editor lEditor = lSharedPreferences.edit();
        lEditor.putBoolean(mIsNowPlayingDataInDb,true);
        lEditor.apply();
        Log.d(TAG,"Stored  "+isStored);

    }

    /**
     *
     * @param pContext
     * @return
     */
    public boolean isNowPlayDataPresent(Context pContext)
    {
        SharedPreferences lSharedPreferences = pContext.getSharedPreferences(mNowPlayData,Context.MODE_PRIVATE);
        Log.d(TAG,"  isStored  "+lSharedPreferences.getBoolean(mIsNowPlayingDataInDb,false));
        return  lSharedPreferences.getBoolean(mIsNowPlayingDataInDb,false);
    }
}
