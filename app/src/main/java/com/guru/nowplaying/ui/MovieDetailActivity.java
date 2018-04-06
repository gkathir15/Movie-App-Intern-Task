package com.guru.nowplaying.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.guru.nowplaying.R;
import com.guru.nowplaying.constants.Constants;
import com.guru.nowplaying.datamodel.CastJdo;
import com.guru.nowplaying.datamodel.MovieDataById;
import com.guru.nowplaying.datamodel.VideosJDO;
import com.guru.nowplaying.helpers.JsonParserHelper;
import com.guru.nowplaying.helpers.JsonUtilHelper;
import com.guru.nowplaying.helpers.http.HttpHelper;
import com.guru.nowplaying.helpers.http.HttpUrlConnHelper;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class MovieDetailActivity extends AppCompatActivity {

    ArrayList<VideosJDO> mVideosList = new ArrayList<>();
    ArrayList<CastJdo>    mCastList = new ArrayList<>();
    HttpHelper mhttpHelper;
    MovieDataById mMovieDataById;
    ProgressBar mProgressBar;
    public static String TAG = "MovieDetailActivity";

    TextView mTitleTv,mDescriptionTv,mTagLineTv,mWeblinkTv;
    ProgressBar mVoteProgress;
    ImageView mPosterIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTitleTv = findViewById(R.id.movie_title);
        mDescriptionTv = findViewById(R.id.movie_description);
        mTagLineTv = findViewById(R.id.tag_line);
        mWeblinkTv = findViewById(R.id.web_link);
        mPosterIv = findViewById(R.id.movie_poster);
       // mVoteProgress = findViewById(R.id.votes);
      //  mVoteProgress.setMax(10);
        //mVoteProgress.setVisibility(View.INVISIBLE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mhttpHelper = new HttpHelper();
        mMovieDataById = new MovieDataById();
        Intent mIntent = getIntent();
        mhttpHelper.setURL(mMovieDataById.constructURL(mIntent.getStringExtra("MOVIE_ID")));
        new FetchMovieById().execute(mhttpHelper);





        //Network calls and database requests

    }

    class FetchMovieById extends AsyncTask<HttpHelper,Void,MovieDataById>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            mProgressBar.setVisibility(View.VISIBLE);
//            mProgressBar.setMax(100);



        }

        @Override
        protected void onPostExecute(MovieDataById movieDataById) {
            super.onPostExecute(movieDataById);

            Log.d(TAG,"Movie data"+movieDataById.getTitle());

            Picasso.get().load(Constants.IMAGE_PREFIX+movieDataById.getPoster_path()).into(mPosterIv);
            Log.d(TAG,"Picasso  "+Constants.IMAGE_PREFIX+movieDataById.getPoster_path());
            mTitleTv.setText(movieDataById.getTitle());
            mTagLineTv.setText(movieDataById.getTagline());
            mDescriptionTv.setText(movieDataById.getOverview());
            mWeblinkTv.setText(movieDataById.getHomepage());
            // implement on click listener for webview implementation.
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                mVoteProgress.setProgress(Integer.parseInt(movieDataById.getVote_average()),true);
//            }
//            else
//            mVoteProgress.setProgress(Integer.parseInt(movieDataById.getVote_average()));


        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected MovieDataById doInBackground(HttpHelper... httpHelpers) {

            HttpUrlConnHelper lHttpUrlConnHelper = new HttpUrlConnHelper();
            try {
                lHttpUrlConnHelper.httpRequest(httpHelpers[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (httpHelpers[0].getStatusCode() == 200) {


                JsonParserHelper lJsonParserHelper = new JsonParserHelper();
                mMovieDataById = lJsonParserHelper.parseMovieData(httpHelpers[0].getRawResponseData());
                Log.d(TAG,"Movie data"+mMovieDataById.getTitle());
                JsonUtilHelper lJsonUtilHelper = new JsonUtilHelper();
                mVideosList.addAll(lJsonParserHelper.ParseNowVideosList(lJsonUtilHelper.jsonNestedObjectToArray("videos","results",httpHelpers[0].getRawResponseData())));
                Log.d(TAG,"Size of videos"+mVideosList.size());
                Log.d(TAG,"Size of videos"+mVideosList.get(0).getName());

                mCastList.addAll(lJsonParserHelper.ParseCastList(lJsonUtilHelper.jsonNestedObjectToArray("credits","cast",httpHelpers[0].getRawResponseData())));

                Log.d(TAG,"Size of cast"+mCastList.size());



            }



                return mMovieDataById;
        }


    }
}
