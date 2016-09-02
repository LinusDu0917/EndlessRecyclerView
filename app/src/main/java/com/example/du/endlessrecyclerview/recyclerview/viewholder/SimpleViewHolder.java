package com.example.du.endlessrecyclerview.recyclerview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.du.endlessrecyclerview.R;

/**
 * Created by linus.du on 8/31/16.
 */
public class SimpleViewHolder extends RecyclerView.ViewHolder {

    public TextView mTitle;

    public SimpleViewHolder(View itemView) {
        super(itemView);
        mTitle = (TextView) itemView.findViewById(R.id.title);
    }
}
