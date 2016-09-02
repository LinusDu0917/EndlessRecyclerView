package com.example.du.endlessrecyclerview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.du.endlessrecyclerview.R;
import com.example.du.endlessrecyclerview.recyclerview.viewholder.SimpleViewHolder;

import java.util.ArrayList;

/**
 * Created by linus.du on 8/31/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<SimpleViewHolder> {

    private Context mContext;
    private ArrayList<String> mData = new ArrayList<>();


    public RecyclerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycler_child_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        String title = mData.get(position);
        holder.mTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public void swapData(ArrayList<String> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
