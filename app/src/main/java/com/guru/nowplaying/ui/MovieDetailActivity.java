package com.guru.nowplaying.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guru.nowplaying.R;
import com.guru.nowplaying.adapter.CastListAdapter;
import com.guru.nowplaying.constants.Constants;
import com.guru.nowplaying.helpers.ApiHelper;
import com.guru.nowplaying.pojos.Cast;
import com.guru.nowplaying.pojos.MovieData;
import com.guru.nowplaying.pojos.Videos;
import com.guru.nowplaying.helpers.JsonParserHelper;
import com.guru.nowplaying.helpers.JsonUtilHelper;
import com.guru.nowplaying.helpers.db.MovieTable;
import com.guru.nowplaying.helpers.http.HttpHelper;
import com.guru.nowplaying.helpers.http.HttpUrlConnHelper;
import com.guru.nowplaying.interfaces.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class MovieDetailActivity extends AppCompatActivity  implements OnItemClickListener{

    ArrayList<Videos> mVideosList = new ArrayList<>();
    ArrayList<Cast> mCastList = new ArrayList<>();
    HttpHelper mHttpHelper;
    MovieData mMovieData;
    ProgressBar mProgressBar;
    Toolbar mToolbar;
    String mReceivedMovieID;
    public static String TAG = "MovieDetailActivity";

    TextView mTitleTv, mDescriptionTv, mTagLineTv, mWeblinkTv;
    //ProgressBar mVoteProgress;
    ImageView mPosterIv;
    ImageView mBackDropIv;
    NestedScrollView mMovieScrollView;
    RelativeLayout mMovieDetailLayout;
    RecyclerView mCastRecyclerView;
    CastListAdapter mCastListAdapter;
    FloatingActionButton mFavouriteFAB;
    ImageView mYoutubePlayButton;
    CollapsingToolbarLayout mCollapsingToolbar;
    Boolean mIsFavourite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
       // mToolbar.setNavigationIcon(R.drawable.back);

        mBackDropIv = findViewById(R.id.collapsing_image_poster);
        mProgressBar = findViewById(R.id.loading_progress);
       // mTitleTv = findViewById(R.id.movie_title);
        mDescriptionTv = findViewById(R.id.movie_description);
        mTagLineTv = findViewById(R.id.tag_line);
        mWeblinkTv = findViewById(R.id.web_link);
        mPosterIv = findViewById(R.id.movie_poster);
        mMovieScrollView = findViewById(R.id.movie_scroll_view);
        mMovieDetailLayout = findViewById(R.id.movie_detail_layout);
        mCastRecyclerView = findViewById(R.id.cast_recycler_list);
        mYoutubePlayButton = findViewById(R.id.play_youtube_button);
        mCollapsingToolbar = findViewById(R.id.toolbar_layout);
        mCastRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mCastListAdapter = new CastListAdapter(R.layout.cast_card_view,mCastList);
        mCastRecyclerView.setAdapter(mCastListAdapter);
        mCastListAdapter.setClickListener( this);
        mProgressBar.setVisibility(View.INVISIBLE);

        mFavouriteFAB = findViewById(R.id.fab);
        mFavouriteFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsFavourite = !mIsFavourite;
                if (mIsFavourite) {
                    mFavouriteFAB.setImageResource(R.drawable.is_fav);
                }
                else {
                    mFavouriteFAB.setImageResource(R.drawable.not_fav);
                }
                //db insertion
                new MovieTable(MovieDetailActivity.this).setMovieAsFavourite(mIsFavourite,mMovieData.getId());
            }
        });

        mWeblinkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mMovieData.getHomepage()));
                startActivity(lIntent);
            }
        });

        mYoutubePlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment lYoutubeFragment = new YoutubePlayerFragment();
                Bundle lArgsBundle = new Bundle();
                lArgsBundle.putString("YoutubeKey",mMovieData.getYoutubeVideoKey());
                lYoutubeFragment.setArguments(lArgsBundle);
                FragmentManager lFragmentManager = getSupportFragmentManager();
               // lFragmentManager.beginTransaction().replace(R.id.,lYoutubeFragment).commit();
            }
        });

        mHttpHelper = new HttpHelper();
        mMovieData = new MovieData();
        Intent lIntent = getIntent();
        mReceivedMovieID = lIntent.getStringExtra("MOVIE_ID");
        mHttpHelper.setRequestType("GET");
        mHttpHelper.setURL(new ApiHelper().constructUrlByMovieId(mReceivedMovieID));
        new FetchMovieById().execute(mHttpHelper);


        //Network calls and database requests

    }



    @Override
    public void onClick(View View, int Position) {
        Log.d(TAG,"setClick listener");

    }



    class FetchMovieById extends AsyncTask<HttpHelper, Void, MovieData> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
//            mProgressBar.setMax(100);


        }

        @Override
        protected void onPostExecute(MovieData movieData) {
            super.onPostExecute(movieData);

            Log.d(TAG, "Movie data" + movieData.getTitle());
            Log.d(TAG, "Movie data" + movieData.getTitle());



            Picasso.get().load(Constants.IMAGE_PREFIX_W300+movieData.getBackdropPath()).into(mBackDropIv);

//            Picasso.get().load(Constants.IMAGE_PREFIX_W300 + movieData.getPosterPath()).into(new Target() {
//                @Override
//                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                    mMovieScrollView.setBackground(new BitmapDrawable(getResources(), bitmap));
//
//                }
//
//                @Override
//                public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//                }
//
//                @Override
//                public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                }
//            });

            ;
            if (!movieData.isFavourite()) {
                mFavouriteFAB.setImageResource(R.drawable.not_fav);
                Log.d(TAG," fav data "+movieData.isFavourite());
            }
            else {
                mFavouriteFAB.setImageResource(R.drawable.is_fav);
                Log.d(TAG," fav data "+movieData.isFavourite());
            }

            Picasso.get().load(Constants.IMAGE_PREFIX_W300 + movieData.getPosterPath()).into(mPosterIv);
            Log.d(TAG, "Picasso  " + Constants.IMAGE_PREFIX_W300 + movieData.getPosterPath());
//            mTitleTv.setText(movieData.getTitle());
            mTagLineTv.setText(movieData.getTagline());
            mDescriptionTv.setText(movieData.getOverview());
            mWeblinkTv.setText(movieData.getTitle());
            getSupportActionBar().setTitle(mMovieData.getTitle());
            mCollapsingToolbar.setTitle(mMovieData.getTitle());




            mCastListAdapter.notifyDataSetChanged();
            mProgressBar.setVisibility(View.INVISIBLE);



        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected MovieData doInBackground(HttpHelper... httpHelpers) {
            JsonParserHelper mJsonParserHelper = new JsonParserHelper();
            JsonUtilHelper mJsonUtilHelper = new JsonUtilHelper();

            MovieTable lMovieTable = new MovieTable(MovieDetailActivity.this);
            mMovieData = lMovieTable.getMovieDataByID(mReceivedMovieID);
            if (mMovieData.isDetailPresent())
            {
                Log.d(TAG,"fetching from db");
                mCastList.addAll(mJsonParserHelper.ParseCastList(mJsonUtilHelper.jsonNestedObjectToArray("credits", "cast", mMovieData.getCastJsonArray())));
                return mMovieData;

            }
            else
                {
                    Log.d(TAG,"fetching from internet");


                HttpUrlConnHelper lHttpUrlConnHelper = new HttpUrlConnHelper();
                try {
                    lHttpUrlConnHelper.httpRequest(httpHelpers[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (httpHelpers[0].getStatusCode() == 200) {



                    mMovieData = mJsonParserHelper.parseMovieData(httpHelpers[0].getRawResponseData());
                    Log.d(TAG, "Movie data" + mMovieData.getTitle());

                    mVideosList.addAll(mJsonParserHelper.ParseNowVideosList(mJsonUtilHelper.jsonNestedObjectToArray("videos", "results", httpHelpers[0].getRawResponseData())));
                    mMovieData.setCastJsonArray(mJsonUtilHelper.jsonNestedObjectToArray("credits", "cast", httpHelpers[0].getRawResponseData()));
                   // mMovieData.setYoutubeVideoKey();
                    Log.d(TAG, "Size of videos" + mVideosList.size());
                    //Log.d(TAG, "Size of videos" + mVideosList.get(0).getName());
                    mCastList.addAll(mJsonParserHelper.ParseCastList(mJsonUtilHelper.jsonNestedObjectToArray("credits", "cast", httpHelpers[0].getRawResponseData())));
                    Log.d(TAG, "Size of cast" + mCastList.size());

                   // getting youtube Video key to play
                    for(Videos pVideos : mVideosList)
                    {
                        //Log.d(TAG,pVideos.getName());
                        if (pVideos.getName().contains("Official"))
                        {
                            mMovieData.setYoutubeVideoKey(pVideos.getKey());
                            Log.d(TAG,"youtube key  "+ pVideos.getKey());
                            break;
                        }
                        if (mMovieData.getYoutubeVideoKey() == null)
                        {
                            mMovieData.setYoutubeVideoKey(mVideosList.get(0).getKey());
                            Log.d(TAG,"youtube key  "+ pVideos.getKey());
                        }


                    }

                    lMovieTable.updateMovieDetailData(mMovieData);


                }
                return mMovieData;


            }


        }
    }
}
