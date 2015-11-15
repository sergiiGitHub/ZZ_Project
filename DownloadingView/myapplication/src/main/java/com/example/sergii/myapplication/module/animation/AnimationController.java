package com.example.sergii.myapplication.module.animation;

import android.content.Context;
import android.view.View;

/**
 * Created by sergii on 14.11.15.
 */
public class AnimationController implements IDownloadController {

    private final FlipInAnimation flipInAnimation;
    private final FiniteRingAnimation firstRingAnimation;

    public AnimationController( Context aContext ){
        flipInAnimation = new FlipInAnimation(aContext);
        firstRingAnimation = new FiniteRingAnimation();
    }

    @Override
    public void setFirstRingView(IViewFiniteAnimationListener aView) {
        firstRingAnimation.setView(aView);
    }

    @Override
    public void setSecondRingView(View aView) {
        // TODO: 15.11.15 impl
    }

    @Override
    public void setForegroundView(View aView) {
        flipInAnimation.setView(aView);
    }

    @Override
    public void cancel() {
        flipInAnimation.cancel();
        firstRingAnimation.cancel();
    }

    @Override
    public void start() {
        flipInAnimation.start();
        firstRingAnimation.start();
    }
}
