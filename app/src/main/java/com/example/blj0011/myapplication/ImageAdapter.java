package com.example.blj0011.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.UUID;

public class ImageAdapter extends BaseAdapter {
    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.cardspadesa, R.drawable.cardspades2, R.drawable.cardspades3, R.drawable.cardspades4, R.drawable.cardspades5, R.drawable.cardspades6,
            R.drawable.cardspades7, R.drawable.cardspades8, R.drawable.cardspades9, R.drawable.cardspades10, R.drawable.cardspadesj, R.drawable.cardspadesq, R.drawable.cardspadesk
    };

    private Context mContext;
    private long[] uniqueIds = new long[mThumbIds.length];

    public ImageAdapter(Context c) {
        mContext = c;

        for(int i = 0; i < uniqueIds.length; i++)
        {
            uniqueIds[i] = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        }
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return uniqueIds[position];
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }


}