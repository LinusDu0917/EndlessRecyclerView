package com.example.du.endlessrecyclerview;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.du.endlessrecyclerview.adapters.RecyclerAdapter;
import com.example.du.endlessrecyclerview.custom.classes.EndlessRecyclerViewScrollListener;
import com.example.du.endlessrecyclerview.dividers.SimpleDivider;
import com.example.du.endlessrecyclerview.loaders.SimpleLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<String>> {

    public static final String CURRENT_LOAD_TIMES = "current_load_times";
    private static final int LOADER_ID = 1110;

    @BindView(R.id.recycler) RecyclerView mRecycler;

    private RecyclerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private int loadTimes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerAdapter(this);
        mRecycler.setAdapter(mAdapter);
        mRecycler.addItemDecoration(new SimpleDivider(this, SimpleDivider.VERTICAL_LIST));
        mRecycler.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemCount) {
                if (loadTimes > 5 ) return;
                Bundle bundle = new Bundle();
                bundle.putInt(CURRENT_LOAD_TIMES, loadTimes);
                getSupportLoaderManager().restartLoader(LOADER_ID, bundle, MainActivity.this);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putInt(CURRENT_LOAD_TIMES, loadTimes);
        getSupportLoaderManager().initLoader(LOADER_ID, bundle, this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public Loader<ArrayList<String>> onCreateLoader(int id, Bundle args) {
        return new SimpleLoader(this, args);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<String>> loader, ArrayList<String> data) {
        loadTimes ++;
        mAdapter.swapData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<String>> loader) {

    }
}
