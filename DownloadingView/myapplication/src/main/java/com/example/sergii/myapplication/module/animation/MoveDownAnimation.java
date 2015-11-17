package com.example.sergii.myapplication.module.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by sergii on 15.11.15.
 */
public class MoveDownAnimation implements Animator.AnimatorListener {

    private static final String TAG = MoveDownAnimation.class.getSimpleName();
    private static final long ANIMATION_DURATION = 1000;

    private static final int START_SHIFT = 0;

    private ValueAnimator startAngleRotate;
    private IViewMoveDownAnimationListener viewMoveDownAnimationListener;
    private boolean isAnimationFinish = true;

    private void createAnimation() {
        startAngleRotate = ValueAnimator.ofFloat(START_SHIFT, getViewFiniteListener().getFinalValue());
        startAngleRotate.setDuration(ANIMATION_DURATION);
        startAngleRotate.setInterpolator(new DecelerateInterpolator(2));
        startAngleRotate.addListener(this);
        startAngleRotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (getViewFiniteListener() != null) {
                    updateView((Float) animation.getAnimatedValue());
                }
            }
        });
    }

    private IViewMoveDownAnimationListener getViewFiniteListener(){
        return viewMoveDownAnimationListener;
    }

    public void start() {
        if ( getViewFiniteListener() == null ){
            Log.e(TAG, "start() called with: view == null ");
        }

        getAnimation().start();
        setIsAnimationFinish(false);
    }

    public boolean isAnimationFinish() {
        return isAnimationFinish;
    }

    private ValueAnimator getAnimation() {
        if ( startAngleRotate == null ){
            createAnimation();
        }
        return startAngleRotate;
    }

    public void cancel() {
        if ( startAngleRotate != null ){
            startAngleRotate.cancel();
        }

        if ( getViewFiniteListener() != null) {
            updateView(START_SHIFT);
        }
    }

    private void updateView(float aShift) {
        Log.d(TAG, "updateView() called with:aCurrentAngle = " + aShift);
        getViewFiniteListener().setShift((int) aShift);
        getViewFiniteListener().invalidate();
    }

    public void setView(IViewMoveDownAnimationListener aViewFiniteListener) {
        resetAnimation();
        this.viewMoveDownAnimationListener = aViewFiniteListener;
    }

    private void resetAnimation() {
        if ( startAngleRotate != null ) {
            startAngleRotate.cancel();
            startAngleRotate = null;
        }
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        Log.d(TAG, "onAnimationEnd() called with: " + "animation = [" + animation + "]");
        setIsAnimationFinish(true);
        getViewFiniteListener().setShift(START_SHIFT);
    }

    private void setIsAnimationFinish(boolean aIsAnimationFinish) {
        this.isAnimationFinish = aIsAnimationFinish;
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
