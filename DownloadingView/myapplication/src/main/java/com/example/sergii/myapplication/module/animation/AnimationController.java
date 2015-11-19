package com.example.sergii.myapplication.module.animation;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.sergii.myapplication.module.animation.listener.IDownloadAnimationListener;

/**
 * Created by sergii on 14.11.15.
 */
public class AnimationController implements IDownloadController, IDownloadAnimationListener {

    private static final String TAG = AnimationController.class.getSimpleName();

    private FlipInAnimator flipInAnimation;
    private FiniteRingAnimation firstRingAnimation;
    private ProgressRingAnimation secondRingAnimation;
    private MoveDownAnimation moveDownAnimation;
    private ColorChangeAnimation colorChangeAnimation;
    private IDownloadAnimationListener externalDownloadAnimationListener;

    public AnimationController( Context aContext ){
        initAnimation(aContext);
    }

    private void initAnimation(Context aContext) {
//        flipInAnimation = new FlipInAnimation(aContext);
        flipInAnimation = new FlipInAnimator();
        flipInAnimation.setExternalListener( this );

        firstRingAnimation = new FiniteRingAnimation();

        secondRingAnimation = new ProgressRingAnimation();
        secondRingAnimation.setExternalListener(this);

        moveDownAnimation = new MoveDownAnimation();
        moveDownAnimation.setExternalListener(this);

        colorChangeAnimation = new ColorChangeAnimation();
        colorChangeAnimation.setExternalListener(this);
    }

    public void setFirstRingView(IViewFiniteAnimationListener aView) {
        firstRingAnimation.setView(aView);
    }

    public void setSecondRingView(IViewProgressAnimationListener aView) {
        secondRingAnimation.setView(aView);
    }

    public void setForegroundViewFlipIn(IViewAnimationListener aView) {
        flipInAnimation.setView(aView);
    }

    public void setForegroundViewMoveDown(IViewMoveDownAnimationListener aView) {
        moveDownAnimation.setView(aView);
    }

    public void setViewChangeColor( IViewColorChangeAnimationListener view ){
        colorChangeAnimation.setView(view);
    }

    @Override
    public void cancel() {
        flipInAnimation.cancel();
        firstRingAnimation.cancel();
        secondRingAnimation.cancel();
        moveDownAnimation.cancel();
        colorChangeAnimation.cancel();
    }

    @Override
    public void start() {
        cancelAllRunningAnimation();
        colorChangeAnimation.getView().setDrawRing(true);

        flipInAnimation.start();
        firstRingAnimation.start();
        secondRingAnimation.start();
    }

    @Override
    public void reset() {
        flipInAnimation.resetView();
        firstRingAnimation.resetView();
        secondRingAnimation.resetView();
        moveDownAnimation.resetView();
        colorChangeAnimation.resetView();
    }

    @Override
    public void updateProgress(float aValue) {
        secondRingAnimation.setProgress(aValue);
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
        if ( !colorChangeAnimation.isAnimationFinish() ) {
            colorChangeAnimation.cancel();
        } else {
            colorChangeAnimation.resetView();
        }
    }

    @Override
    public void onArrowHideAnimationFinish() {
        Log.d(TAG, "onArrowHideAnimationFinish() called with: ");
        flipInAnimation.getViewAnimationListener().setVisibility(View.INVISIBLE);
        colorChangeAnimation.getView().setDrawRing(false);
        if ( getExternalDownloadAnimationListener() != null ){
            getExternalDownloadAnimationListener().onArrowHideAnimationFinish();
        }
    }

    @Override
    public void onArrowShowAnimationFinish() {
        Log.d(TAG, "onArrowShowAnimationFinish() called with: ");
        if ( getExternalDownloadAnimationListener() != null ){
            getExternalDownloadAnimationListener().onArrowShowAnimationFinish();
        }
    }

    @Override
    public void onColorChangeAnimationFinish() {
        Log.d(TAG, "onColorChangeAnimationFinish() called with: ");
        if ( getExternalDownloadAnimationListener() != null ){
            getExternalDownloadAnimationListener().onColorChangeAnimationFinish();
        }
    }

    @Override
    public void onProgressAnimationFinish() {
        Log.d(TAG, "onProgressAnimationFinish() called with: ");

        moveDownAnimation.start();
        colorChangeAnimation.start();
        if ( getExternalDownloadAnimationListener() != null ){
            getExternalDownloadAnimationListener().onProgressAnimationFinish();
        }
    }

    public IDownloadAnimationListener getExternalDownloadAnimationListener() {
        return externalDownloadAnimationListener;
    }

    public void setExternalDownloadAnimationListener(IDownloadAnimationListener externalDownloadAnimationListener) {
        this.externalDownloadAnimationListener = externalDownloadAnimationListener;
    }
}
