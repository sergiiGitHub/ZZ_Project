package com.example.sergii.rotationcircleview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.sergii.rotationcircleview.view.RotationCircleView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RotationCircleView rotationCircleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rotationCircleView = (RotationCircleView)findViewById(R.id.progress_view);
        ((Button)findViewById(R.id.start_animation)).setOnClickListener(this);
        ((Button)findViewById(R.id.cancel_animation)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_animation:
                rotationCircleView.startFlipAnimation();
                break;
            case R.id.cancel_animation:
                rotationCircleView.cancelFlipAnimation();
                break;
        }
    }
}
