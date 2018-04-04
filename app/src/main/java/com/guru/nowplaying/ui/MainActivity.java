package com.guru.nowplaying.ui;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.guru.nowplaying.R;
import com.guru.nowplaying.constants.Constants;
import com.guru.nowplaying.datamodel.NowPlayingList;
import com.guru.nowplaying.helpers.http.HttpHelper;
import com.guru.nowplaying.helpers.http.HttpUrlConnHelper;
import com.guru.nowplaying.helpers.JsonParserHelper;
import com.guru.nowplaying.helpers.JsonUtilHelper;

import java.io.IOException;
import java.util.List;

/**
 *
 */

public class MainActivity extends AppCompatActivity {

    EditText mQueryTv;
    Button mQueryButton;
    String mQueryText;
    TextView mResponseTv;
    TextView mResponseMessageTv;
    HttpHelper mHttpHelper = new HttpHelper();
    NowPlayingList mNowPlayingList;
    public static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueryTv = findViewById(R.id.query);
        mQueryButton = findViewById(R.id.makeRequest);
        mResponseTv = findViewById(R.id.response);
        mResponseMessageTv = findViewById(R.id.responsecode);

        mQueryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNowPlayingList = new NowPlayingList();
                mQueryText = String.valueOf(mQueryTv.getText());
                mHttpHelper.setURL(mNowPlayingList.constructURL(Constants.API_KEY_V3,Constants.ROOT_URL,Constants.PAGE+1,Constants.NOW_PLAYING));
                mHttpHelper.setRequestType("GET");
                new FetchMovieList().execute(mHttpHelper);



            }
        });





    }
    public class FetchMovieList extends AsyncTask<HttpHelper,Void,List<NowPlayingList>>
    {

        @Override
        protected List<NowPlayingList> doInBackground(HttpHelper... httpHelpers) {



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
                return lJsonParserHelper.ParseNowPlayingList(lJsonUtilHelper.jsonObjectToArray("results",httpHelpers[0].getRawResponseData()));
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<NowPlayingList> nowPlayingLists) {
            super.onPostExecute(nowPlayingLists);



            Log.d(TAG+"Status code"," "+mHttpHelper.getStatusCode());

            mResponseMessageTv.setText(""+mHttpHelper.getStatusCode());
            mResponseMessageTv.append(""+mHttpHelper.getStatusMessage());

           if(nowPlayingLists != null) {


               Log.d(TAG + "Result List size", String.valueOf(nowPlayingLists.size()));

               for (NowPlayingList temp : nowPlayingLists) {
                   Log.d(TAG + "ID", temp.getId());
                   Log.d(TAG + "Movie name", temp.getTitle());
                   Log.d(TAG + "~~~~", "------------------");


               }
           }
        }
    }



}

