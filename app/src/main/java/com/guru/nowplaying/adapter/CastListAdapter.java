package com.guru.nowplaying.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guru.nowplaying.R;
import com.guru.nowplaying.constants.Constants;
import com.guru.nowplaying.pojos.Cast;
import com.guru.nowplaying.interfaces.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Guru on 09-04-2018.
 */

public class CastListAdapter extends RecyclerView.Adapter<CastListAdapter.ViewHolder> {


    ArrayList<Cast> mCastArrayList;
    OnItemClickListener mOnItemClickListener;
    int mListItemView;
    public static String TAG = "CastListAdapter";


    public CastListAdapter(int pListItem, ArrayList<Cast> pCastDataList) {
        mCastArrayList = pCastDataList;
        mListItemView = pListItem;
        Log.d(TAG + " constructor", "Recieved Size" + pCastDataList.size());
//        Log.d(TAG,mNowPlayArrayList.get(0).getTitle());

    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context lContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(lContext);
        View lCastListView = layoutInflater.inflate(mListItemView, parent, false);

        ViewHolder viewHolder = new ViewHolder(lCastListView);
        Log.d(TAG, "Oncreate viewholder");

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CastListAdapter.ViewHolder holder, int position) {

        Cast lCast = mCastArrayList.get(position);
        TextView lTitle = holder.lTitle;
        ImageView lPoster = holder.lPoster;
        lTitle.setText(lCast.getCharacter());
        Picasso.get().load(Constants.IMAGE_PREFIX_W300 + lCast.getProfilePath()).placeholder(R.mipmap.placeholder1).into(lPoster);
        //Log.d(TAG,"OnBindview"+mNowPlayArrayList.size());

    }

    @Override
    public int getItemCount() {

        Log.d(TAG, "GetItemCount" + mCastArrayList.size());
        return mCastArrayList.size();
        //return 0;
    }

    public void setClickListener(OnItemClickListener pOnItemClickListener) {
        this.mOnItemClickListener = pOnItemClickListener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnItemClickListener, View.OnClickListener {

        TextView lTitle;
        ImageView lPoster;

        public ViewHolder(View itemView) {
            super(itemView);
            lTitle = itemView.findViewById(R.id.movie_title);
            lPoster = itemView.findViewById(R.id.poster);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View View, int Position) {
            mOnItemClickListener.onClick(View, Position);

        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
