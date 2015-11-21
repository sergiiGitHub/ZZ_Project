package com.example.sergii.uploadinganimation_demo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sergii.uploadinganimation.UploadingView;

public class UploadingDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private UploadingView uploadView;
    private HandlerWrapper handlerWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploading_demo);

        uploadView = (UploadingView) findViewById(R.id.upload_view);
        createHandlerWraper(uploadView);

        findViewById(R.id.start_animation).setOnClickListener(this);
        findViewById(R.id.cancel_animation).setOnClickListener(this);
    }

    private void createHandlerWraper(UploadingView downloadingView) {
        handlerWrapper = new HandlerWrapper(downloadingView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_animation:
                uploadView.getAnimationController().start();
                handlerWrapper.reset();
                handlerWrapper.beginUpdate();
                break;
            case R.id.cancel_animation:
                uploadView.getAnimationController().cancel();
                handlerWrapper.interrupt();
                break;
        }
    }

    class HandlerWrapper{
        private final UploadingView view;
        private final Handler handler;
        private final Runnable runnebleUpdate;

        int value = 0;
        int delta = 3;
        private boolean isInterrupted = false;

        public HandlerWrapper( UploadingView aView ) {
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
                handler.postDelayed( runnebleUpdate, 100 );
            }
        }

        public void interrupt() {
            isInterrupted = true;
            handler.removeCallbacks(runnebleUpdate);
        }

        public void reset() {
            isInterrupted = false;
            handler.removeCallbacks(runnebleUpdate);
            value = 0;
        }
    }
}
