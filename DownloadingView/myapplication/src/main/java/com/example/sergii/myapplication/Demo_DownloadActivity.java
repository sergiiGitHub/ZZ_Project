package com.example.sergii.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sergii.myapplication.module.DownloadingView;


public class Demo_DownloadActivity extends AppCompatActivity implements View.OnClickListener {

    private DownloadingView downloadingView;
    private HandlerWrapper handlerWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_download);

        downloadingView = (DownloadingView) findViewById(R.id.downloading_view);
        createHandlerWraper(downloadingView);

        findViewById(R.id.start_animation).setOnClickListener(this);
        findViewById(R.id.cancel_animation).setOnClickListener(this);
    }

    private void createHandlerWraper(DownloadingView downloadingView) {
        handlerWrapper = new HandlerWrapper(downloadingView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_animation:
                downloadingView.getAnimationController().start();
                handlerWrapper.reset();
                handlerWrapper.beginUpdate();
                break;
            case R.id.cancel_animation:
                downloadingView.getAnimationController().cancel();
                handlerWrapper.interrupt();
                break;
        }
    }

    class HandlerWrapper{
        private final DownloadingView view;
        private final Handler handler;
        private final Runnable runnebleUpdate;

        int value = 0;
        int delta = 2;
        private boolean isInterrupted = false;

        public HandlerWrapper( DownloadingView aView ) {
            this.view = aView;
            handler = new Handler();
            runnebleUpdate = createRunneble();
        }

        private Runnable createRunneble() {
            return new Runnable() {
                @Override
                public void run() {
                    beginUpdate();
                }
            };
        }

        public void beginUpdate(){
            if ( isInterrupted ){
                return;
            }
            if ( !view.getAnimationController().isProgressFinish() ) {
                value += delta;
                view.getAnimationController().updateProgress(value);
                handler.postDelayed( runnebleUpdate, 50 );
            }
        }

        public void interrupt() {
            isInterrupted = true;
            handler.removeCallbacks(runnebleUpdate);
        }

        public void reset() {
            isInterrupted = false;
        }
    }

}
