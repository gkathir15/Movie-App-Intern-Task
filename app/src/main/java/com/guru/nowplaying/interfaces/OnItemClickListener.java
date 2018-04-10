package com.guru.nowplaying.interfaces;

import android.view.View;

/**
 * Created by Guru on 05-04-2018.
 */


public interface OnItemClickListener {
    /**
     * method for passing click events with position.
     * @param View
     * @param Position
     */
    void onClick(View View, int Position);
}
