package com.ladddd.canvasdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.ladddd.canvasdemo.parallax.ParallaxRecyclerView;

/**
 * Created by 陈伟达 on 2017/11/24.
 */

public class ParallaxActivity extends AppCompatActivity {

    private ParallaxRecyclerView recycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_parallax);

        recycler = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(new ParallaxLibAdapter(this));
    }
}
