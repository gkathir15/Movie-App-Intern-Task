package com.guru.nowplaying.helpers.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.guru.nowplaying.constants.MovieDataEntry;
import com.guru.nowplaying.datamodel.MovieDataById;
import com.guru.nowplaying.datamodel.NowPlaying;

import java.util.ArrayList;

/**
 * Created by Guru on 04-04-2018.
 */

public class MovieDBHelper {

    public static String TAG = "MovieDBHelper";
    private Context mContext;
    SQLiteDatabase mSqLiteDatabase;


    AppDBHelper mAppDBHelper = new AppDBHelper(mContext);

    public MovieDBHelper(Context mContext)     {
        this.mContext = mContext;
    }

    /**
     *
     * @param pSqLiteDatabase
     */
    public static void createNowPlayingTable(SQLiteDatabase pSqLiteDatabase)
    {

        String SQL_CREATE_STUDENT_DB = "CREATE TABLE " + MovieDataEntry.TABLE_NAME
                + '(' + MovieDataEntry.COLUMN_NAME_ID + " TEXT PRIMARY KEY NOT NULL,"+
                MovieDataEntry.COLUMN_NAME_DESCRIPTION+"TEXT ,"
                + MovieDataEntry.COLUMN_NAME_TITLE + " TEXT NOT NULL,"
                + MovieDataEntry.COLUMN_NAME_RELEASE_DATE + " TEXT,"
                + MovieDataEntry.COLUMN_NAME_VOTE_AVERAGE + " TEXT," +
                MovieDataEntry.COLUMN_NAME_POSTER+" TEXT NOT NULL,"+
                MovieDataEntry.COLUMN_NAME_BACKDROP_PATH+" TEXT NOT NULL,"+
                MovieDataEntry.COLUMN_NAME_TAGLINE+" TEXT,"+
                MovieDataEntry.COLUMN_NAME_STATUS+" TEXT,"+
                MovieDataEntry.COLUMN_NAME_IS_FAVOURITE+" BOOLEAN,"+
                MovieDataEntry.COLUMN_NAME_HOMEPAGE+" TEXT,"+
                MovieDataEntry.COLUMN_NAME_YOUTUBE_VIDEO_KEY+" TEXT,"+
                MovieDataEntry.COLUMN_NAME_CAST_DATA+" TEXT,"+MovieDataEntry.COLUMN_NAME_IS_DETAIL_STORED+" BOOLEAN )";

        pSqLiteDatabase.execSQL(SQL_CREATE_STUDENT_DB);

    }

    /**
     *
     * @return
     */
    public ArrayList<NowPlaying> retrieveForNowPlayingList()
    {
        mSqLiteDatabase = new AppDBHelper(mContext).getReadableDatabase();
        ArrayList<NowPlaying> lNowPlayingListArray = new ArrayList<>();
        String lSelectQuery = "SELECT * FROM "+ MovieDataEntry.TABLE_NAME;
        Cursor lCursor = mSqLiteDatabase.rawQuery(lSelectQuery,null);

        //NowPlaying(String backdrop_path, String id, String title, String release_date, String vote, String poster_path)

        try {
            if (lCursor.moveToFirst()) {
                do {                                        //Adding data Via Constructor.
                    NowPlaying lNowPlaying = new NowPlaying(
                            lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_BACKDROP_PATH)),
                            lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_ID)),
                            lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_TITLE)),
                            lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_RELEASE_DATE)),
                            lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_VOTE_AVERAGE)),
                            lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_POSTER)));

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
    public void updateNowPlayingData(ArrayList<NowPlaying> pArrayList)
    {
        mSqLiteDatabase = new AppDBHelper(mContext).getWritableDatabase();

        ContentValues lContentValues = new ContentValues();
        for(NowPlaying pNowPlaying : pArrayList)
        {

            lContentValues.put(MovieDataEntry.COLUMN_NAME_BACKDROP_PATH, pNowPlaying.getBackdrop_path());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_ID, pNowPlaying.getId());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_TITLE, pNowPlaying.getTitle());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_POSTER, pNowPlaying.getPoster_path());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_VOTE_AVERAGE, pNowPlaying.getVote_average());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_RELEASE_DATE, pNowPlaying.getRelease_date());


               mSqLiteDatabase.insertWithOnConflict(MovieDataEntry.TABLE_NAME, null, lContentValues,SQLiteDatabase.CONFLICT_REPLACE);
            }
        mSqLiteDatabase.close();

        }

    /**
     *
     * @param pMovieDataById
     */
    public void updateMovieDetailData(MovieDataById pMovieDataById)
    {
        mSqLiteDatabase = new AppDBHelper(mContext).getWritableDatabase();

        ContentValues lContentValues = new ContentValues();


            lContentValues.put(MovieDataEntry.COLUMN_NAME_ID, pMovieDataById.getId());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_TAGLINE,pMovieDataById.getTagline() );
            lContentValues.put(MovieDataEntry.COLUMN_NAME_POSTER, pMovieDataById.getOverview());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_VOTE_AVERAGE, pMovieDataById.getStatus());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_RELEASE_DATE, pMovieDataById.isFavourite());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_RELEASE_DATE, pMovieDataById.getHomepage());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_RELEASE_DATE, pMovieDataById.getYoutubeVideoKey());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_RELEASE_DATE, pMovieDataById.getCastJsonArray());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_RELEASE_DATE, pMovieDataById.getCastJsonArray());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_IS_DETAIL_STORED, "true");



            mSqLiteDatabase.insertWithOnConflict(MovieDataEntry.TABLE_NAME, null, lContentValues,SQLiteDatabase.CONFLICT_REPLACE);

             mSqLiteDatabase.close();

    }

    /**
     *
     * @param pMovieID
     * @return
     */
    public MovieDataById getMovieDataByID(String pMovieID)
    {
        //MovieDataById lMovieDataById = new MovieDataById();
        String lSearchQuery;
        Cursor lCursor;
        // check is detail stored is true. else return false to the object and make it fetch from network.

        mSqLiteDatabase = new AppDBHelper(mContext).getReadableDatabase();
        lSearchQuery = "SELECT * FROM "+ MovieDataEntry.TABLE_NAME + " WHERE "+ MovieDataEntry.COLUMN_NAME_ID + " = "+pMovieID;
        lCursor = mSqLiteDatabase.rawQuery(lSearchQuery,null);

        if (lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_IS_DETAIL_STORED)).equalsIgnoreCase("true"))
        {
            MovieDataById lMovieDataById = new MovieDataById(
                    lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_VOTE_AVERAGE)),
                    null,
                    lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_ID)),
                    lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_TITLE)),
                    null,
                    lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_BACKDROP_PATH)),
                    lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_STATUS)),
                    lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_HOMEPAGE)),
                    lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_DESCRIPTION)),
                    lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_RELEASE_DATE)),
                    lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_POSTER)),
                    lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_TAGLINE)),
                    Boolean.getBoolean( lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_IS_FAVOURITE))),
                    lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_YOUTUBE_VIDEO_KEY)),
                    lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_CAST_DATA)),
                    Boolean.getBoolean(lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_IS_DETAIL_STORED))));





            return lMovieDataById;



        }
        else {
            MovieDataById lMovieDataById = new MovieDataById();
            lMovieDataById.setDetailPresent(false);
            return lMovieDataById;
        }



    }





    }



