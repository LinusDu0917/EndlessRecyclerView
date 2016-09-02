package com.example.du.endlessrecyclerview.loaders;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import com.example.du.endlessrecyclerview.MainActivity;

import java.util.ArrayList;

/**
 * Created by linus.du on 8/31/16.
 */
public class SimpleLoader extends AsyncTaskLoader<ArrayList<String>> {

    private ArrayList<String> mData;
    private int loadTimes;

    public SimpleLoader(Context context, Bundle bundle) {
        super(context);
        loadTimes = bundle.getInt(MainActivity.CURRENT_LOAD_TIMES);
    }

    @Override
    public ArrayList<String> loadInBackground() {
        mData = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            mData.add("" + (i + loadTimes * 20));
        }
        return mData;
    }

    @Override
    public void deliverResult(ArrayList<String> data) {
        if (isReset()) return;
        ArrayList<String> oldData = mData;
        mData = data;
        if (isStarted()) super.deliverResult(data);
        if (oldData != null && oldData.size() > 0) oldData.clear();
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) deliverResult(mData);
        if (takeContentChanged() || mData == null) forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        stopLoading();
    }
}
