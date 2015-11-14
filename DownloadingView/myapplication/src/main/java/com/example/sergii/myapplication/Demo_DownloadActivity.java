package com.example.sergii.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sergii.myapplication.module.DownloadingView;

public class Demo_DownloadActivity extends AppCompatActivity implements View.OnClickListener {

    private DownloadingView downloadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_download);

        downloadingView = (DownloadingView) findViewById(R.id.downloading_view);

        findViewById(R.id.start_animation).setOnClickListener(this);
        findViewById(R.id.cancel_animation).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_animation:
                downloadingView.getAnimationController().start();
                break;
            case R.id.cancel_animation:
                downloadingView.getAnimationController().cancel();
                break;
        }
    }
}
