package com.example.sergii.myapplication.module.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by sergii on 15.11.15.
 */
public class ProgressRingAnimation implements IProgressRingAnimation, Animator.AnimatorListener {

    private static final String TAG = ProgressRingAnimation.class.getSimpleName();
    private static final long ANIMATION_DURATION = 3000;
    private static final float FINAL_ACTUAL_ANGLE = 360;

    private ValueAnimator startAngleRotate;
    private IViewProgressAnimationListener viewProgressListener;
    private boolean isAnimationFinish = true;
    private float currentAngle;

    private void createAnimation() {
        currentAngle = getViewProgressListener().getProgressCurrentAngle();
        startAngleRotate = ValueAnimator.ofFloat(currentAngle, currentAngle + FINAL_ACTUAL_ANGLE);
        startAngleRotate.setDuration(ANIMATION_DURATION);
        //startAngleRotate.setInterpolator(new DecelerateInterpolator(2));
        startAngleRotate.addListener(this);
        startAngleRotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (getViewProgressListener() != null) {
                    updateView((Float) animation.getAnimatedValue());
                }
            }
        });
    }

    private void setIsAnimationFinish(boolean aIsAnimationFinish) {
        this.isAnimationFinish = aIsAnimationFinish;
    }

    private IViewProgressAnimationListener getViewProgressListener(){
        return viewProgressListener;
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

    private void updateView(float aCurrentAngle) {
        Log.d(TAG, "updateView() called with:aCurrentAngle = " + aCurrentAngle );
        getViewProgressListener().setActualAngleProgressAnimation(aCurrentAngle);
        getViewProgressListener().invalidate();
    }

    private void resetAnimation() {
        if ( startAngleRotate != null ) {
            startAngleRotate.cancel();
            startAngleRotate = null;
        }
    }

    @Override
    public void start() {
        if ( getViewProgressListener() == null ){
            Log.e(TAG, "start() called with: view == null ");
        }

        getAnimation().start();
        setIsAnimationFinish(false);
    }

    @Override
    public void cancel() {
        startAngleRotate.cancel();
        if ( getViewProgressListener() != null ){
            updateView(currentAngle);
        }
    }

    @Override
    public void setView(IViewProgressAnimationListener aViewFiniteListener) {
        resetAnimation();
        this.viewProgressListener = aViewFiniteListener;
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        Log.d(TAG, "onAnimationEnd() called with: " + "animation = [" + animation + "]");
        setIsAnimationFinish( true );
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
