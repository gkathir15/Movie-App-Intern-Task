package com.guru.nowplaying.helpers;

import java.net.URL;

/**
 * Created by Guru on 04-04-2018.
 */

public class HttpHelper {

    private  String mStatusMessage;
    private  String mRawResponseData;
    private  int mStatusCode;
    private  String mRequestType;
    private String mHeaderData;
    private String mHeaderType;
    private URL mURl;
    private String mPayload;
    private String mURL;

    public String getmPayload() {
        return mPayload;
    }

    public void setPayload(String mPayload) {
        this.mPayload = mPayload;
    }

    public String getURL() {
        return mURL;
    }

    public void setURL(String mURL) {
        this.mURL = mURL;
    }

    public URL getURl() {
        return mURl;
    }

    public void setURl(URL mURl) {
        this.mURl = mURl;
    }

    public int getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(int mStatusCode) {
        this.mStatusCode = mStatusCode;
    }

    public String getRequestType() {
        return mRequestType;
    }

    public void setRequestType(String mRequestType) {
        this.mRequestType = mRequestType;
    }

    public String getHeaderData() {
        return mHeaderData;
    }

    public void setHeaderData(String mHeaderData) {
        this.mHeaderData = mHeaderData;
    }

    public String getHeaderType() {
        return mHeaderType;
    }

    public void setHeaderType(String mHeaderType) {
        this.mHeaderType = mHeaderType;
    }

    public String getStatusMessage() {
        return mStatusMessage;
    }

    public void setStatusMessage(String mStatusMessage) {
        this.mStatusMessage = mStatusMessage;
    }

    public String getRawResponseData() {
        return mRawResponseData;
    }

    public void setRawResponseData(String mRawResponseData) {
        this.mRawResponseData = mRawResponseData;
    }



}
