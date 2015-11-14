package com.example.sergii.myapplication.module.animation;

import android.content.Context;
import android.view.View;

/**
 * Created by sergii on 14.11.15.
 */
public class AnimationController implements IDownloadController {

    private final FlipInAnimation flipInAnimation;

    public AnimationController( Context aContext ){
        flipInAnimation = new FlipInAnimation(aContext);
    }

    @Override
    public void setBackgroundView(View aView) {

    }

    @Override
    public void setForegroundView(View aView) {
        flipInAnimation.setView(aView);
    }

    @Override
    public void cancel() {
        flipInAnimation.cancel();
    }

    @Override
    public void start() {
        flipInAnimation.start();

    }
}
