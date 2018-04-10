package com.guru.nowplaying.helpers.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.guru.nowplaying.constants.MovieDataEntry;
import com.guru.nowplaying.pojos.MovieData;
import com.guru.nowplaying.pojos.MovieListingData;

import java.util.ArrayList;

/**
 * Created by Guru on 04-04-2018.
 */

public class MovieTable {

    public static String TAG = "MovieTable";
    private Context mContext;
    SQLiteDatabase mSqLiteDatabase;


    //AppDBHelper mAppDBHelper = new AppDBHelper(mContext);

    public MovieTable(Context pContext)     {
        this.mContext = pContext;
    }

    /**
     *
     * @param pSqLiteDatabase
     */
    public static void createNowPlayingTable(SQLiteDatabase pSqLiteDatabase)
    {

        String SQL_CREATE_STUDENT_DB = "CREATE TABLE " + MovieDataEntry.TABLE_NAME
                + '(' + MovieDataEntry.COLUMN_NAME_ID + " TEXT PRIMARY KEY NOT NULL,"+
                MovieDataEntry.COLUMN_NAME_DESCRIPTION+" TEXT ,"
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
    public ArrayList<MovieListingData> retrieveForNowPlayingList()
    {
        mSqLiteDatabase = new AppDBHelper(mContext).getReadableDatabase();
        ArrayList<MovieListingData> lMovieListingDataListArray = new ArrayList<>();
        String lSelectQuery = "SELECT * FROM "+ MovieDataEntry.TABLE_NAME;
        Cursor lCursor = mSqLiteDatabase.rawQuery(lSelectQuery,null);

        //MovieListingData(String backdrop_path, String id, String title, String release_date, String vote, String poster_path)


            if (lCursor.moveToFirst()) {
                do {                                        //Adding data Via Constructor.
                    MovieListingData lMovieListingData = new MovieListingData(
                            lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_BACKDROP_PATH)),
                            lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_ID)),
                            lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_TITLE)),
                            lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_RELEASE_DATE)),
                            lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_VOTE_AVERAGE)),
                            lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_POSTER)));

                    lMovieListingDataListArray.add(lMovieListingData);
                } while (lCursor.moveToNext());

            }

            lCursor.close();
            mSqLiteDatabase.close();
            return lMovieListingDataListArray;
        }






    /**
     *
     * @param pNowplayingArrayList
     */
    public void updateNowPlayingData(ArrayList<MovieListingData> pNowplayingArrayList)
    {
        mSqLiteDatabase = new AppDBHelper(mContext).getWritableDatabase();

        ContentValues lContentValues = new ContentValues();
        for(MovieListingData pMovieListingData : pNowplayingArrayList)
        {

            lContentValues.put(MovieDataEntry.COLUMN_NAME_BACKDROP_PATH, pMovieListingData.getBackdropPath());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_ID, pMovieListingData.getId());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_TITLE, pMovieListingData.getTitle());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_POSTER, pMovieListingData.getPosterPath());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_VOTE_AVERAGE, pMovieListingData.getVoteAverage());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_RELEASE_DATE, pMovieListingData.getReleaseDate());


               mSqLiteDatabase.insertWithOnConflict(MovieDataEntry.TABLE_NAME, null, lContentValues,SQLiteDatabase.CONFLICT_REPLACE);
            }
        mSqLiteDatabase.close();

        }

    /**
     *
     * @param pMovieData
     */
    public void updateMovieDetailData(MovieData pMovieData)
    {
        mSqLiteDatabase = new AppDBHelper(mContext).getWritableDatabase();

        ContentValues lContentValues = new ContentValues();


            lContentValues.put(MovieDataEntry.COLUMN_NAME_ID, pMovieData.getId());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_TAGLINE, pMovieData.getTagline() );
            lContentValues.put(MovieDataEntry.COLUMN_NAME_TITLE, pMovieData.getTitle());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_POSTER, pMovieData.getPosterPath());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_VOTE_AVERAGE, pMovieData.getVoteAverage());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_RELEASE_DATE, pMovieData.getReleaseDate());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_HOMEPAGE, pMovieData.getHomepage());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_YOUTUBE_VIDEO_KEY, pMovieData.getYoutubeVideoKey());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_CAST_DATA, pMovieData.getCastJsonArray());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_DESCRIPTION, pMovieData.getOverview());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_BACKDROP_PATH, pMovieData.getBackdropPath());
            lContentValues.put(MovieDataEntry.COLUMN_NAME_IS_DETAIL_STORED, true );



            mSqLiteDatabase.insertWithOnConflict(MovieDataEntry.TABLE_NAME, null, lContentValues,SQLiteDatabase.CONFLICT_REPLACE);

             mSqLiteDatabase.close();

    }

    /**
     * It is used to get the movie data by the movie id.
     * @param pMovieID
     * @return
     */
    public MovieData getMovieDataByID(String pMovieID)
    {
        //MovieData lMovieData = new MovieData();
        String lSearchQuery;
       // Cursor lCursor;
        MovieData lMovieData = null;
        // check is detail stored is true. else return false to the object and make it fetch from network.

        mSqLiteDatabase = new AppDBHelper(mContext).getReadableDatabase();
        lSearchQuery = "SELECT * FROM "+ MovieDataEntry.TABLE_NAME + " WHERE "+ MovieDataEntry.COLUMN_NAME_ID + "="+pMovieID;
       Cursor lCursor = mSqLiteDatabase.rawQuery(lSearchQuery,null);


        if(lCursor.moveToFirst()){
            Log.d(TAG,"CURSOr IS"+Boolean.getBoolean(lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_IS_DETAIL_STORED))));
        if (!Boolean.getBoolean(lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_IS_DETAIL_STORED))))
        {
            lMovieData = new MovieData(
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
            lCursor.close();
            mSqLiteDatabase.close();
        }
            return lMovieData;
        }

        else {
            lMovieData = new MovieData();
            lMovieData.setDetailPresent(false);
            return lMovieData;
        }
    }

    /**
     *
     * @param pIsFav
     * @param pMovieID
     */
    public void setMovieAsFavourite(boolean pIsFav,String pMovieID)
    {
        Log.d(TAG, "isFAV "+pIsFav);
        mSqLiteDatabase = new AppDBHelper(mContext).getWritableDatabase();
        ContentValues lContentValues = new ContentValues();

        lContentValues.put(MovieDataEntry.COLUMN_NAME_ID,pMovieID);
        lContentValues.put(MovieDataEntry.COLUMN_NAME_IS_FAVOURITE,pIsFav);
        mSqLiteDatabase.updateWithOnConflict(MovieDataEntry.TABLE_NAME,lContentValues,MovieDataEntry.COLUMN_NAME_ID+"= "+pMovieID,null,SQLiteDatabase.CONFLICT_REPLACE);
        mSqLiteDatabase.close();
    }

    /**
     *
     * @return array list of favourite movies
     */
    public ArrayList<MovieListingData> getFavouriteMovies()
    {
        ArrayList<MovieListingData> lFavouriteList = new ArrayList<>();
        mSqLiteDatabase = new AppDBHelper(mContext).getReadableDatabase();
        MovieListingData movieListingData = new MovieListingData();
       String  lSearchQuery = "SELECT * FROM "+ MovieDataEntry.TABLE_NAME + " WHERE "+ MovieDataEntry.COLUMN_NAME_IS_FAVOURITE + "= "+ "1";
        Cursor lCursor = mSqLiteDatabase.rawQuery(lSearchQuery,null);
        if (lCursor.moveToFirst())
        {
            do {
                movieListingData.setId(lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_ID)));
                movieListingData.setPosterPath(lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_POSTER)));
                movieListingData.setTitle(lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_TITLE)));
                movieListingData.setReleaseDate(lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_RELEASE_DATE)));
                movieListingData.setVoteAverage(lCursor.getString(lCursor.getColumnIndex(MovieDataEntry.COLUMN_NAME_VOTE_AVERAGE)));

                lFavouriteList.add(movieListingData);

            }while (lCursor.moveToNext());
        }
        lCursor.close();
        mSqLiteDatabase.close();

        return lFavouriteList;
    }

}



