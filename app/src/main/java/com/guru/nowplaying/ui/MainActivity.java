package com.guru.nowplaying.ui;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.guru.nowplaying.R;
import com.guru.nowplaying.adapter.MovieListAdapter;
import com.guru.nowplaying.constants.Constants;
import com.guru.nowplaying.datamodel.NowPlaying;
import com.guru.nowplaying.helpers.db.NowPlayListDBHelper;
import com.guru.nowplaying.helpers.http.HttpHelper;
import com.guru.nowplaying.helpers.http.HttpUrlConnHelper;
import com.guru.nowplaying.helpers.JsonParserHelper;
import com.guru.nowplaying.helpers.JsonUtilHelper;
import com.guru.nowplaying.interfaces.OnItemClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class MainActivity extends AppCompatActivity implements OnItemClickListener {


    RecyclerView mNowPlayRecyclerView;
    HttpHelper mHttpHelper = new HttpHelper();
    NowPlaying mNowPlaying = new NowPlaying();
    public static String TAG = "MainActivity";
    MovieListAdapter mMovieListAdapter;
    ArrayList<NowPlaying> mNowPlayingArrayList = new ArrayList<>();
    NowPlayListDBHelper mNowPlayListDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNowPlayRecyclerView = findViewById(R.id.now_play_Recycler);
        mNowPlayListDBHelper = new NowPlayListDBHelper(this);
       // AppDBHelper lappDBHelper = new AppDBHelper(MainActivity.this);
        mNowPlayListDBHelper = new NowPlayListDBHelper(this);

        //hav network calls and db retrievals
        mHttpHelper.setURL(mNowPlaying.constructURL(Constants.API_KEY_V3,Constants.ROOT_URL,"1",Constants.NOW_PLAYING));
        new FetchMovieList().execute(mHttpHelper);

        mNowPlayRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mMovieListAdapter = new MovieListAdapter(R.layout.movie_poster_view,mNowPlayingArrayList);
        mNowPlayRecyclerView.setAdapter(mMovieListAdapter);
        mMovieListAdapter.setClickListener(this);
        mHttpHelper = new HttpHelper();
        mHttpHelper.setRequestType("GET");








    }

    @Override
    public void onClick(int pPosition) {

    }

    public class FetchMovieList extends AsyncTask<HttpHelper,Void,List<NowPlaying>>
    {

        @Override
        protected List<NowPlaying> doInBackground(HttpHelper... httpHelpers) {



            HttpUrlConnHelper lHttpUrlConnHelper = new HttpUrlConnHelper();
            Log.d(TAG+"  URL",httpHelpers[0].getURL());
            try {
                lHttpUrlConnHelper.httpRequest(httpHelpers[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }


            if (httpHelpers[0].getStatusCode() == 200)
            {
                JsonParserHelper lJsonParserHelper = new JsonParserHelper();
                JsonUtilHelper lJsonUtilHelper = new JsonUtilHelper();


                Log.d(TAG,"ArraylistSize 1111  "+mNowPlayingArrayList.size());
                mNowPlayListDBHelper.updateNowPlayingList(lJsonParserHelper.ParseNowPlayingList(lJsonUtilHelper.jsonObjectToArray("results",httpHelpers[0].getRawResponseData())));
                mNowPlayingArrayList.addAll(mNowPlayListDBHelper.retrieveNowPlayingList());
                Log.d(TAG,"Retrived from db Size"+mNowPlayingArrayList.size());
               return mNowPlayingArrayList;
            }

           // AppDBHelper lappDBHelper = new AppDBHelper(MainActivity.this);

            Log.d(TAG,"Size form db"+mNowPlayListDBHelper.retrieveNowPlayingList().size());

            Log.d(TAG,"ArraylistSize 1111  "+mNowPlayingArrayList.size());


            return null;
        }

        @Override
        protected void onPostExecute(List<NowPlaying> nowPlayings) {
            super.onPostExecute(nowPlayings);



            Log.d(TAG+"Status code"," "+mHttpHelper.getStatusCode());

            Log.d(TAG,""+mHttpHelper.getStatusCode());
            Log.d(TAG,""+mHttpHelper.getStatusMessage());

            mMovieListAdapter.notifyDataSetChanged();

           if(nowPlayings != null) {




               Log.d(TAG + "Result List size", String.valueOf(nowPlayings.size()));

               for (NowPlaying temp : nowPlayings) {
                   Log.d(TAG + "ID", temp.getId());
                   Log.d(TAG + "Movie name", temp.getTitle());
                   Log.d(TAG + "~~~~", "------------------");


               }
           }
        }
    }



}

