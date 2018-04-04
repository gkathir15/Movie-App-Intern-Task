package com.guru.nowplaying.helpers;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Guru on 04-04-2018.
 */

public class HttpUrlConnHelper {

    private HttpURLConnection mHttpURLConnection;
    public static String TAG = "HttpUrlConnHelper";


    /**
     *
     * @param pHttpHelper
     * @return
     * @throws IOException
     */
    public HttpHelper httpRequest(@NonNull HttpHelper pHttpHelper) throws IOException {
        String lResponse = "Not responded";

        try {
            mHttpURLConnection = (HttpURLConnection) new URL(pHttpHelper.getURL()).openConnection();
            Log.d(TAG+"HttpReq", String.valueOf(pHttpHelper.getURl()));
            mHttpURLConnection.setDoInput(true);

            if (pHttpHelper.getHeaderType() != null) {
                mHttpURLConnection.setRequestProperty("Content-Type", pHttpHelper.getHeaderType());

                if (pHttpHelper.getmPayload() != null)
                {
                    mHttpURLConnection.setDoInput(true);
                    mHttpURLConnection.setDoOutput(true);
                    OutputStreamWriter lOutputStreamWriter = new OutputStreamWriter(mHttpURLConnection.getOutputStream(),"UTF-8");
                    lOutputStreamWriter.write(pHttpHelper.getmPayload());
                    lOutputStreamWriter.close();
                    lOutputStreamWriter.flush();

                }

                if (pHttpHelper.getRequestType().equalsIgnoreCase("GET"))
                {
                    mHttpURLConnection.setDoOutput(true);
                }


            }

            mHttpURLConnection.setRequestMethod(mHttpURLConnection.getRequestMethod());

            if (mHttpURLConnection.getResponseCode() == 200||mHttpURLConnection.getResponseCode() == 201)
            {
                InputStream lInputStream = new BufferedInputStream(mHttpURLConnection.getInputStream());
                BufferedReader mBufferedReader = new BufferedReader(new InputStreamReader(lInputStream));
                lResponse=mBufferedReader.readLine();
                pHttpHelper.setRawResponseData(lResponse);
            }
            else {
                pHttpHelper.setStatusMessage("Something went wrong,Try again");
            }

            pHttpHelper.setStatusCode(mHttpURLConnection.getResponseCode());
            pHttpHelper.setStatusMessage(mHttpURLConnection.getResponseMessage());
            Log.d(TAG+" response"," "+pHttpHelper.getRawResponseData());


            Log.d(TAG+" Response",lResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            mHttpURLConnection.disconnect();
        }

        return pHttpHelper;
    }
}



