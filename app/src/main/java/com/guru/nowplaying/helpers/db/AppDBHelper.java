package com.guru.nowplaying.helpers.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.guru.nowplaying.constants.Constants;

/**
 * Created by Guru on 04-04-2018.
 */

public class AppDBHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION =1;
    Context mContext;
   // MovieDBHelper mNowPlayListDBHelper = new MovieDBHelper(mContext);


    public AppDBHelper(Context pContext) {
        super(pContext, Constants.DATABASE_NAME, null, DATABASE_VERSION);
        mContext = pContext;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        MovieDBHelper.createNowPlayingTable(db);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
