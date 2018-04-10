package com.guru.nowplaying.ui;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.guru.nowplaying.helpers.db.MovieTable;
import com.guru.nowplaying.interfaces.OnItemClickListener;
import com.guru.nowplaying.pojos.MovieListingData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavouriteList extends Fragment implements OnItemClickListener {

    RecyclerView mFavouritesRecyclerView;
    ProgressBar mLoadingProgress;
    MovieListingData mMovieListingData = new MovieListingData();
    public static String TAG = "FragmentFavouriteList";
    MovieListAdapter mFavMovieListAdapter;
    ArrayList<MovieListingData> mFavMovieListingDataArrayList = new ArrayList<>();
    MovieTable mMovieTable;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View lMovieListView = inflater.inflate(R.layout.fragment_now_playing_list, container, false);

        mLoadingProgress = lMovieListView.findViewById(R.id.movie_list_progress);
        mFavouritesRecyclerView = lMovieListView.findViewById(R.id.now_play_Recycler);
        mMovieTable = new MovieTable(getContext());
        //hav network calls and db retrievals
        new FetchMovieList().execute();
        mFavouritesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mFavMovieListAdapter = new MovieListAdapter(R.layout.movie_card_view, mFavMovieListingDataArrayList);
        mFavouritesRecyclerView.setAdapter(mFavMovieListAdapter);
        mFavMovieListAdapter.setClickListener(this);
        return lMovieListView;
    }


    @Override
    public void onClick(View View, int pPosition) {

        Log.d(TAG, "" + "\n" + pPosition + "\n" + mFavMovieListingDataArrayList.get(pPosition).getId() + "\n" + mFavMovieListingDataArrayList.get(pPosition).getTitle());
        //Starting new activity to show movie description
        Intent lIntent = new Intent(getContext(), MovieDetailActivity.class);
        lIntent.putExtra("MOVIE_ID", mFavMovieListingDataArrayList.get(pPosition).getId());
        startActivity(lIntent);

    }

    public class FetchMovieList extends AsyncTask<Void, Void, List<MovieListingData>> {


        @Override
        protected List<MovieListingData> doInBackground(Void... voids) {

            mFavMovieListingDataArrayList.addAll(mMovieTable.getFavouriteMovies());
            Log.d(TAG, "Retrived from db Size" + mFavMovieListingDataArrayList.size());
            return mFavMovieListingDataArrayList;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingProgress.setVisibility(View.VISIBLE);
        }





        @Override
        protected void onPostExecute(List<MovieListingData> nowPlayingData) {
            super.onPostExecute(nowPlayingData);




            mFavMovieListAdapter.notifyDataSetChanged();
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
