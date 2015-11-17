package com.example.sergii.myapplication.module.animation;

import android.animation.Animator;
import android.content.Context;
import android.view.View;

/**
 * Created by sergii on 14.11.15.
 */
public class AnimationController implements IDownloadController, Animator.AnimatorListener {

    private FlipInAnimation flipInAnimation;
    private FiniteRingAnimation firstRingAnimation;
    private ProgressRingAnimation secondRingAnimation;
    private MoveDownAnimation moveDownAnimation;

    public AnimationController( Context aContext ){
        initAnimation(aContext);
    }

    private void initAnimation(Context aContext) {
        flipInAnimation = new FlipInAnimation(aContext);
        firstRingAnimation = new FiniteRingAnimation();
        secondRingAnimation = new ProgressRingAnimation();
        secondRingAnimation.setExternalListener(this);
        moveDownAnimation = new MoveDownAnimation();
    }

    public void setFirstRingView(IViewFiniteAnimationListener aView) {
        firstRingAnimation.setView(aView);
    }

    public void setSecondRingView(IViewProgressAnimationListener aView) {
        secondRingAnimation.setView(aView);
    }

    public void setForegroundViewFlipIn(View aView) {
        flipInAnimation.setView(aView);
    }

    public void setForegroundViewMoveDown(IViewMoveDownAnimationListener aView) {
        moveDownAnimation.setView(aView);
    }

    @Override
    public void cancel() {
        flipInAnimation.cancel();
        firstRingAnimation.cancel();
        secondRingAnimation.cancel();
        moveDownAnimation.cancel();
    }

    @Override
    public void start() {
        cancelAllRunningAnimation();

        flipInAnimation.start();
        firstRingAnimation.start();
        secondRingAnimation.start();
    }

    @Override
    public void updateProgress(float aValue) {
        secondRingAnimation.setProgress( aValue );
    }

    @Override
    public boolean isProgressFinish() {
        return secondRingAnimation.isAnimationFinish();
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

        if ( !moveDownAnimation.isAnimationFinish() ){
            moveDownAnimation.cancel();
        }

    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        moveDownAnimation.start();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
