package com.example.sergii.myapplication.module.animation;

import android.content.Context;
import android.view.View;

/**
 * Created by sergii on 14.11.15.
 */
public class AnimationController implements IDownloadController {

    private final FlipInAnimation flipInAnimation;
    private final FiniteRingAnimation firstRingAnimation;
    private final ProgressRingAnimation secondRingAnimation;
    public AnimationController( Context aContext ){
        flipInAnimation = new FlipInAnimation(aContext);
        firstRingAnimation = new FiniteRingAnimation();
        secondRingAnimation = new ProgressRingAnimation();
    }

    @Override
    public void setFirstRingView(IViewFiniteAnimationListener aView) {
        firstRingAnimation.setView(aView);
    }

    @Override
    public void setSecondRingView(IViewProgressAnimationListener aView) {
        secondRingAnimation.setView(aView);
    }

    @Override
    public void setForegroundView(View aView) {
        flipInAnimation.setView(aView);
    }

    @Override
    public void cancel() {
        flipInAnimation.cancel();
        firstRingAnimation.cancel();
        secondRingAnimation.cancel();
    }

    @Override
    public void start() {
        cancelAllRunningAnimation();

        flipInAnimation.start();
        firstRingAnimation.start();
        secondRingAnimation.start();
    }

    private void cancelAllRunningAnimation() {
        if ( !flipInAnimation.isAnimationFinish() ){
            flipInAnimation.cancel();
        }

        if ( !firstRingAnimation.isAnimationFinish() ){
            firstRingAnimation.cancel();
        }

        if ( !secondRingAnimation.isAnimationFinish() ){
            secondRingAnimation.cancel();
        }
    }

}
