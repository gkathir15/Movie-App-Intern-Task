package com.guru.nowplaying.helpers.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guru.nowplaying.constants.NowPlayingDBContract;
import com.guru.nowplaying.datamodel.NowPlaying;

import java.util.ArrayList;

/**
 * Created by Guru on 04-04-2018.
 */

public class NowPlayListDBHelper {

    public static String TAG = "NowPlayListDBHelper";
    private Context mContext;
    SQLiteDatabase mSqLiteDatabase;


    AppDBHelper mAppDBHelper = new AppDBHelper(mContext);

    public NowPlayListDBHelper(Context mContext) {
        this.mContext = mContext;
    }

    /**
     *
     * @param pSqLiteDatabase
     */
    public static void createNowPlayingTable(SQLiteDatabase pSqLiteDatabase)
    {

        String SQL_CREATE_STUDENT_DB = "CREATE TABLE " + NowPlayingDBContract.NowPlayingEntry.TABLE_NAME
                + '(' + NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_ID + " TEXT PRIMARY KEY NOT NULL,"
                + NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_TITLE + " TEXT NOT NULL,"
                + NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_RELEASE_DATE + " TEXT NOT NULL,"
                + NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_VOTE + " TEXT NOT NULL," +
                NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_POSTER+" TEXT NOT NULL,"+
                NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_BACKDROP_PATH+" TEXT NOT NULL);";

        pSqLiteDatabase.execSQL(SQL_CREATE_STUDENT_DB);

    }

    /**
     *
     * @return
     */
    public ArrayList<NowPlaying> retrieveNowPlayingList()
    {
        mSqLiteDatabase = new AppDBHelper(mContext).getReadableDatabase();
        ArrayList<NowPlaying> lNowPlayingListArray = new ArrayList<>();
        String lSelectQuery = "SELECT * FROM "+ NowPlayingDBContract.NowPlayingEntry.TABLE_NAME;
        Cursor lCursor = mSqLiteDatabase.rawQuery(lSelectQuery,null);

        //NowPlaying(String backdrop_path, String id, String title, String release_date, String vote, String poster_path)

        try {
            if (lCursor.moveToFirst()) {
                do {                                        //Adding data Via Constructor.
                    NowPlaying lNowPlaying = new NowPlaying(
                            lCursor.getString(lCursor.getColumnIndex(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_BACKDROP_PATH)),
                            lCursor.getString(lCursor.getColumnIndex(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_ID)),
                            lCursor.getString(lCursor.getColumnIndex(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_TITLE)),
                            lCursor.getString(lCursor.getColumnIndex(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_RELEASE_DATE)),
                            lCursor.getString(lCursor.getColumnIndex(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_VOTE)),
                            lCursor.getString(lCursor.getColumnIndex(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_POSTER)));

                    lNowPlayingListArray.add(lNowPlaying);
                } while (lCursor.moveToNext());

            }
        }finally {
            lCursor.close();
            mSqLiteDatabase.close();
            return lNowPlayingListArray;
        }




    }

    /**
     *
     * @param pArrayList
     */
    public void updateNowPlayingList(ArrayList<NowPlaying> pArrayList)
    {
        mSqLiteDatabase = new AppDBHelper(mContext).getWritableDatabase();

        ContentValues lContentValues = new ContentValues();
        for(NowPlaying pNowPlaying :pArrayList)
        {

            lContentValues.put(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_BACKDROP_PATH, pNowPlaying.getBackdrop_path());
            lContentValues.put(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_ID, pNowPlaying.getId());
            lContentValues.put(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_TITLE, pNowPlaying.getTitle());
            lContentValues.put(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_POSTER, pNowPlaying.getPoster_path());
            lContentValues.put(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_VOTE, pNowPlaying.getVote_average());
            lContentValues.put(NowPlayingDBContract.NowPlayingEntry.COLUMN_NAME_RELEASE_DATE, pNowPlaying.getRelease_date());


               mSqLiteDatabase.insertWithOnConflict(NowPlayingDBContract.NowPlayingEntry.TABLE_NAME, null, lContentValues,SQLiteDatabase.CONFLICT_IGNORE);
            }
        mSqLiteDatabase.close();

        }



    }



