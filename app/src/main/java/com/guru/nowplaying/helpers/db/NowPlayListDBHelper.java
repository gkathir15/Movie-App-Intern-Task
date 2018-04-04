package com.guru.nowplaying.helpers.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guru.nowplaying.constants.NowPlayingDBContract;
import com.guru.nowplaying.datamodel.NowPlayingList;

import java.util.ArrayList;

/**
 * Created by Guru on 04-04-2018.
 */

public class NowPlayListDBHelper {


    public void createNowPlayingTable(SQLiteDatabase pSqLiteDatabase)
    {
        String SQL_CREATE_STUDENT_DB = "CREATE TABLE" + NowPlayingDBContract.NowPlayingEntry.TABLE_NAME
                + '(' + NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_ID + " TEXT PRIMARY KEY NOT NULL,"
                + NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_TITLE + " TEXT NOT NULL,"
                + NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_RELEASE_DATE + " TEXT NOT NULL,"
                + NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_VOTE + " TEXT NOT NULL," +
                NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_POSTER+" TEXT NOT NULL,"+
                NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_BACKDROP_PATH+" TEXT NOT NULL,);";

        pSqLiteDatabase.execSQL(SQL_CREATE_STUDENT_DB);

    }

    public ArrayList<NowPlayingList> retriveNowPlayingList(SQLiteDatabase pSqLiteDatabase)
    {
        ArrayList<NowPlayingList> lNowPlayingListArrayList = new ArrayList<>();
        String lSelectQuery = "SELECT * FROM "+ NowPlayingDBContract.NowPlayingEntry.TABLE_NAME;
        Cursor lCursor = pSqLiteDatabase.rawQuery(lSelectQuery,null);

        //NowPlayingList(String backdrop_path, String id, String title, String release_date, String vote, String poster_path)

        try {
            if (lCursor.moveToFirst()) {
                do {                                        //Adding data Via Constructor.
                    NowPlayingList lNowPlayingList = new NowPlayingList(
                            lCursor.getString(lCursor.getColumnIndex(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_BACKDROP_PATH)),
                            lCursor.getString(lCursor.getColumnIndex(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_ID)),
                            lCursor.getString(lCursor.getColumnIndex(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_TITLE)),
                            lCursor.getString(lCursor.getColumnIndex(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_RELEASE_DATE)),
                            lCursor.getString(lCursor.getColumnIndex(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_VOTE)),
                            lCursor.getString(lCursor.getColumnIndex(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_POSTER)));

                    lNowPlayingListArrayList.add(lNowPlayingList);
                } while (lCursor.moveToNext());

            }
        }finally {
            lCursor.close();
            pSqLiteDatabase.close();
            return lNowPlayingListArrayList;
        }




    }


}
