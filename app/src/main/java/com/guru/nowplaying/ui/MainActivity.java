package com.guru.nowplaying.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.guru.nowplaying.R;

public class MainActivity extends AppCompatActivity {

    FragmentNowPlayingList mFragmentNowPlayingList = new FragmentNowPlayingList();
    FragmentFavouriteList mFragmentFavouriteList = new FragmentFavouriteList();
    FragmentUpcomingMovies mFragmentUpcomingMovies = new FragmentUpcomingMovies();




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment lSelectedFragment = null;
            switch (item.getItemId()) {
                case R.id.now_playing:
                    lSelectedFragment = mFragmentNowPlayingList;
                    break;
                case R.id.favourites:

                    lSelectedFragment = mFragmentFavouriteList;
                    break;
                case R.id.upcoming:
                    lSelectedFragment = mFragmentUpcomingMovies;
                    break;
            }
                    FragmentTransaction lFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    lFragmentTransaction.replace(R.id.nav_frame,lSelectedFragment);
                    lFragmentTransaction.commit();

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BottomNavigationView navigation =findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //FragmentManager lFragmentManager
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
       mFragmentTransaction.replace(R.id.nav_frame,mFragmentNowPlayingList).commit();
    }

}
