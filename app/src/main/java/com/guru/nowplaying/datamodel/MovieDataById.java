package com.guru.nowplaying.datamodel;

import android.util.Log;

/**
 * Created by Guru on 04-04-2018.
 */

public class MovieDataById {



        private String mBackdrop,mMovieID,mTitle,mOverview,mPopularity,mPoster,mReleaseDate,mTagline,mHomepage,mVote,mVoteCount;
        private String mVideoId,mVideoKey,Site;

        public String getmBackdrop() {
            return mBackdrop;
        }

        public void setmBackdrop(String mBackdrop) {
            this.mBackdrop = mBackdrop;
        }

        public String getmMovieID() {
            return mMovieID;
        }

        public void setmMovieID(String mMovieID) {
            this.mMovieID = mMovieID;
        }

        public String getmTitle() {
            return mTitle;
        }

        public void setmTitle(String mTitle) {
            this.mTitle = mTitle;
        }

        public String getmOverview() {
            return mOverview;
        }

        public void setmOverview(String mOverview) {
            this.mOverview = mOverview;
        }

        public String getmPopularity() {
            return mPopularity;
        }

        public void setmPopularity(String mPopularity) {
            this.mPopularity = mPopularity;
        }

        public String getmPoster() {
            return mPoster;
        }

        public void setmPoster(String mPoster) {
            this.mPoster = mPoster;
        }

        public String getmReleaseDate() {
            return mReleaseDate;
        }

        public void setmReleaseDate(String mReleaseDate) {
            this.mReleaseDate = mReleaseDate;
        }

        public String getmTagline() {
            return mTagline;
        }

        public void setmTagline(String mTagline) {
            this.mTagline = mTagline;
        }

        public String getmHomepage() {
            return mHomepage;
        }

        public void setmHomepage(String mHomepage) {
            this.mHomepage = mHomepage;
        }

        public String getmVote() {
            return mVote;
        }

        public void setmVote(String mVote) {
            this.mVote = mVote;
        }

        public String getmVoteCount() {
            return mVoteCount;
        }

        public void setmVoteCount(String mVoteCount) {
            this.mVoteCount = mVoteCount;
        }

        public String getmVideoId() {
            return mVideoId;
        }

        public void setmVideoId(String mVideoId) {
            this.mVideoId = mVideoId;
        }

        public String getmVideoKey() {
            return mVideoKey;
        }

        public void setmVideoKey(String mVideoKey) {
            this.mVideoKey = mVideoKey;
        }

        public String getSite() {
            return Site;
        }

        public void setSite(String site) {
            Site = site;
        }

        /**
         *Constructs the url string as per api docs for getting movie by id and appended additional info
         * @param pLanguage
         * @param pApiKey
         * @param pBasePath
         * @param pCategory
         * @param pId
         * @return
         */

        public String constructURL(String pLanguage, String pApiKey, String pBasePath, String pCategory, String pId)
        {

            //example https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US


            Log.d("ConstructedURL",pBasePath+pCategory+pId+pApiKey+"&"+pLanguage);
            return pBasePath+pCategory+pId+pApiKey+"&"+pLanguage;

        }
    }

