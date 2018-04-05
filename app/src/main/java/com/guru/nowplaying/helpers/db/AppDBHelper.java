package com.guru.nowplaying.helpers.db;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.guru.nowplaying.constants.Constants;
import com.guru.nowplaying.datamodel.NowPlayingList;

import java.util.ArrayList;

/**
 * Created by Guru on 04-04-2018.
 */

public class AppDBHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION =1;
    Context mContext;
   // NowPlayListDBHelper mNowPlayListDBHelper = new NowPlayListDBHelper(mContext);


    public AppDBHelper(Context pContext) {
        super(pContext, Constants.DATABASE_NAME, null, DATABASE_VERSION);
        mContext = pContext;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        NowPlayListDBHelper.createNowPlayingTable(db);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
