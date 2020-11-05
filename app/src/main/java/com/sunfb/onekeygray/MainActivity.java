package com.sunfb.onekeygray;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sunfb.onekeygray.base.BaseActivity;
import com.sunfb.onekeygray.impl.GrayObservable;
import com.sunfb.onekeygray.impl.GrayObserver;

public class MainActivity extends BaseActivity {
    private Button btn_left,btn_right,btn_normal;
    private GrayObservable grayObservable;
    private GrayObserver grayObserver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grayObservable = new GrayObservable();
        grayObserver = new GrayObserver();
        grayObservable.add(grayObserver);
        btn_left = findViewById(R.id.btn_one_key_gray);
        btn_right = findViewById(R.id.btn_wv);
        btn_normal=findViewById(R.id.btn_normal);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grayObservable.open();
            }
        });

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,WebviewActivity.class));
            }
        });
        btn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grayObservable.close();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}