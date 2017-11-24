package com.ladddd.canvasdemo;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity implements TestAdapter.RecyclerInfoDelegate{

    private RecyclerView mRecyclerView;
    private TestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new TestAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public Rect getLocation() {
        return new Rect(mRecyclerView.getLeft(), mRecyclerView.getTop(),
                mRecyclerView.getRight(), mRecyclerView.getBottom());
    }

    @Override
    public Context getContext() {
        return this;
    }
}
