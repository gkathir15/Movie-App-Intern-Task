package com.guru.nowplaying.constants;

import android.provider.BaseColumns;

/**
 * Created by Guru on 04-04-2018.
 */

public class NowPlayingDBContract {

    public static class NowPlayingEntry implements BaseColumns
    {
        public static String TABLE_NAME = "NowPlayingMovies";
        public static String COLUMN_NAME_ID = "ID";
        public static String COLUMN_NAME_TITLE = "TITLE";
        public static String COLUMN_NAME_RELEASE_DATE="RELEASE_DATE";
        public static String COLUMN_NAME_VOTE ="VOTE_COUNT";
        public static String COLUMN_NAME_POSTER = "POSTER_PATH";
        public static String COLUMN_NAME_BACKDROP_PATH ="BACKDROP_PATH";

    }
}
