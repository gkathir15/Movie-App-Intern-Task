package com.guru.nowplaying.ui;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.guru.nowplaying.R;
import com.guru.nowplaying.adapter.MovieListAdapter;
import com.guru.nowplaying.helpers.ApiHelper;
import com.guru.nowplaying.pojos.MovieListingData;
import com.guru.nowplaying.helpers.JsonParserHelper;
import com.guru.nowplaying.helpers.JsonUtilHelper;
import com.guru.nowplaying.helpers.SharedPreferencesHelper;
import com.guru.nowplaying.helpers.db.MovieTable;
import com.guru.nowplaying.helpers.http.HttpHelper;
import com.guru.nowplaying.helpers.http.HttpUrlConnHelper;
import com.guru.nowplaying.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNowPlayingList extends Fragment implements OnItemClickListener {

    RecyclerView mNowPlayRecyclerView;
    ProgressBar mLoadingProgress;
    HttpHelper mHttpHelper = new HttpHelper();
    MovieListingData mMovieListingData = new MovieListingData();
    public static String TAG = "FragmentNowPlayingList";
    MovieListAdapter mMovieListAdapter;
    ArrayList<MovieListingData> mMovieListingDataArrayList = new ArrayList<>();
    MovieTable mMovieTable;
    SharedPreferencesHelper mSharedPreferencesHelper = new SharedPreferencesHelper();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View lMovieListView = inflater.inflate(R.layout.fragment_now_playing_list, container, false);

        mLoadingProgress = lMovieListView.findViewById(R.id.movie_list_progress);
        mNowPlayRecyclerView = lMovieListView.findViewById(R.id.now_play_Recycler);
        mMovieTable = new MovieTable(getContext());
        // AppDBHelper lappDBHelper = new AppDBHelper(MainActivity.this);
        mMovieTable = new MovieTable(getContext());
        mHttpHelper.setRequestType("GET");
        //hav network calls and db retrievals
       new FetchMovieList(1).execute();
        mNowPlayRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mMovieListAdapter = new MovieListAdapter(R.layout.movie_card_view, mMovieListingDataArrayList);
        mNowPlayRecyclerView.setAdapter(mMovieListAdapter);
        mMovieListAdapter.setClickListener(this);

        return lMovieListView;
    }


    @Override
    public void onClick(View View, int pPosition) {

        Log.d(TAG, "" + "\n" + pPosition + "\n" + mMovieListingDataArrayList.get(pPosition).getId() + "\n" + mMovieListingDataArrayList.get(pPosition).getTitle());
        //Starting new activity to show movie description
        Intent lIntent = new Intent(getContext(), MovieDetailActivity.class);
        lIntent.putExtra("MOVIE_ID", mMovieListingDataArrayList.get(pPosition).getId());
        startActivity(lIntent);

    }

    public class FetchMovieList extends AsyncTask<Void, Void, List<MovieListingData>> {

        int mURlPageNo;

        public FetchMovieList(int pUrlPage) {
            mURlPageNo = pUrlPage;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingProgress.setVisibility(View.VISIBLE);
        }



        @Override
        protected List<MovieListingData> doInBackground(Void... voids) {


            if (!mSharedPreferencesHelper.isNowPlayDataPresent(getContext())) {
                mHttpHelper.setRequestType("GET");
                mHttpHelper.setURL(new ApiHelper().constructNowPlayingUrlByPage(String.valueOf(mURlPageNo)));
                HttpUrlConnHelper lHttpUrlConnHelper = new HttpUrlConnHelper();
                Log.d(TAG + "  URL", mHttpHelper.getURL());
                try {
                    lHttpUrlConnHelper.httpRequest(mHttpHelper);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                if (mHttpHelper.getStatusCode() == 200) {
                    JsonParserHelper lJsonParserHelper = new JsonParserHelper();
                    JsonUtilHelper lJsonUtilHelper = new JsonUtilHelper();


                    Log.d(TAG, "ArraylistSize 1111  " + mMovieListingDataArrayList.size());
                    mMovieTable.updateNowPlayingData(lJsonParserHelper.ParseNowPlayingList(lJsonUtilHelper.jsonObjectToArray("results", mHttpHelper.getRawResponseData())));
                    mMovieListingDataArrayList.addAll(mMovieTable.retrieveForNowPlayingList());
                    mSharedPreferencesHelper.setNowPlayingDataStored(true, getContext());
                    Log.d(TAG, "Retrived from db Size" + mMovieListingDataArrayList.size());
                    return mMovieListingDataArrayList;
                }
            } else {
                mMovieListingDataArrayList.addAll(mMovieTable.retrieveForNowPlayingList());
                Log.d(TAG, "Retrived from db Size" + mMovieListingDataArrayList.size());
                return mMovieListingDataArrayList;

            }

            // AppDBHelper lappDBHelper = new AppDBHelper(MainActivity.this);

            Log.d(TAG, "Size form db" + mMovieTable.retrieveForNowPlayingList().size());

            Log.d(TAG, "ArraylistSize 1111  " + mMovieListingDataArrayList.size());


            return null;
        }

        @Override
        protected void onPostExecute(List<MovieListingData> nowPlayingData) {
            super.onPostExecute(nowPlayingData);


            Log.d(TAG + "Status code", " " + FragmentNowPlayingList.this.mHttpHelper.getStatusCode());

            Log.d(TAG, "" + FragmentNowPlayingList.this.mHttpHelper.getStatusCode());
            Log.d(TAG, "" + FragmentNowPlayingList.this.mHttpHelper.getStatusMessage());

            mMovieListAdapter.notifyDataSetChanged();
            mLoadingProgress.setVisibility(View.INVISIBLE);

            if (nowPlayingData != null) {


                Log.d(TAG + "Result List size", String.valueOf(nowPlayingData.size()));

                for (MovieListingData temp : nowPlayingData) {
                    Log.d(TAG + "ID", temp.getId());
                    Log.d(TAG + "Movie name", temp.getTitle());
                    Log.d(TAG + "~~~~", "------------------");


                }
            }
        }
    }

}
