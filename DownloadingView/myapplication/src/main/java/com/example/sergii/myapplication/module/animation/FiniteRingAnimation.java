package com.example.sergii.myapplication.module.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by sergii on 15.11.15.
 */
public class FiniteRingAnimation implements Animator.AnimatorListener {

    private static final String TAG = FiniteRingAnimation.class.getSimpleName();
    private static final long ANIMATION_DURATION = 1000;
    private static final float START_SWEEP_ANGLE = 0;
    private static final float FINAL_SWEEP_ANGLE = 360;

    private ValueAnimator startAngleRotate;
    private IViewFiniteAnimationListener viewFiniteListener;
    private boolean isAnimationFinish = true;

    private void createAnimation() {
        startAngleRotate = ValueAnimator.ofFloat(START_SWEEP_ANGLE, FINAL_SWEEP_ANGLE);
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

    private IViewFiniteAnimationListener getViewFiniteListener(){
        return viewFiniteListener;
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
            updateView(START_SWEEP_ANGLE);
        }
    }

    private void updateView(float aCurrentAngle) {
        Log.d(TAG, "updateView() called with:aCurrentAngle = " + aCurrentAngle );
        getViewFiniteListener().setActualAngleFiniteAnimation(aCurrentAngle);
        getViewFiniteListener().invalidate();
    }

    public void setView(IViewFiniteAnimationListener aViewFiniteListener) {
        resetAnimation();
        this.viewFiniteListener = aViewFiniteListener;
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
        setIsAnimationFinish( true );
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
