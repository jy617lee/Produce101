package com.produce101ledlight;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by jeeyunlee on 24/06/2017.
 */

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {
    private int[] mDataset;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public ViewHolder(ImageView v){
            super(v);
            mImageView =v;
        }
    }

    public ColorAdapter(Context context, int[] colorDataSet){
        mDataset =colorDataSet;
        mContext = context;
    }

    @Override
    public ColorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.color, parent, false);
        ViewHolder vh = new ViewHolder((ImageView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mImageView.getBackground().setColorFilter(ContextCompat.getColor(mContext, mDataset[position]), PorterDuff.Mode.SRC_IN);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}