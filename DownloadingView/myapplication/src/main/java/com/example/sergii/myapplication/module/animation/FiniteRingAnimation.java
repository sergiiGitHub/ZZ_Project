package com.example.sergii.myapplication.module.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by sergii on 15.11.15.
 */
public class FiniteRingAnimation implements IFiniteRingAnimation, Animator.AnimatorListener {

    private static final String TAG = FiniteRingAnimation.class.getSimpleName();
    private static final long ANIMATION_DURATION = 1000;
    private static final float FINAL_ANGLE = 360;

    private ValueAnimator startAngleRotate;
    private IViewFiniteAnimationListener viewFiniteListener;
    private boolean isAnimationFinish = true;

    private void createAnimation() {
        final float startAngle = getViewFiniteListener().getInitialAngle();
        startAngleRotate = ValueAnimator.ofFloat(startAngle, FINAL_ANGLE);
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

    @Override
    public void start() {
        if ( getViewFiniteListener() == null ){
            Log.e(TAG, "start() called with: view == null ");
        }

        if ( isAnimationFinish() ){
            getAnimation().start();
            setIsAnimationFinish(false);
        } else {
            cancel();
        }

    }

    private boolean isAnimationFinish() {
        return isAnimationFinish;
    }

    private ValueAnimator getAnimation() {
        if ( startAngleRotate == null ){
            createAnimation();
        }
        return startAngleRotate;
    }

    @Override
    public void cancel() {
        startAngleRotate.cancel();
        if ( getViewFiniteListener() != null ){
            updateView(0);
        }
    }

    private void updateView(float aCurrentAngle) {
        Log.d(TAG, "updateView() called with:aCurrentAngle = " + aCurrentAngle );
        getViewFiniteListener().setActualAngleFiniteAnimation(aCurrentAngle);
        getViewFiniteListener().invalidate();
    }

    @Override
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
