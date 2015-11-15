package com.example.sergii.myapplication.module.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.animation.LinearInterpolator;

/**
 * Created by sergii on 15.11.15.
 */
public class ProgressRingAnimation implements IProgressRingAnimation, Animator.AnimatorListener {

    private static final String TAG = ProgressRingAnimation.class.getSimpleName();
    private static final long ANIMATION_DURATION = 3000;
    private static final float FINAL_ACTUAL_ANGLE = 360;
    private static final float PERCENTAGE_TO_ANGLE_COEF = FINAL_ACTUAL_ANGLE / 100;

    private ValueAnimator startAngleRotate;
    private IViewProgressAnimationListener viewProgressListener;
    private boolean isAnimationFinish = true;
    private float currentAngle;

    private void createAnimation() {
        currentAngle = getViewProgressListener().getProgressCurrentAngle();
        startAngleRotate = ValueAnimator.ofFloat(currentAngle, currentAngle + FINAL_ACTUAL_ANGLE);
        startAngleRotate.setDuration(ANIMATION_DURATION);
        startAngleRotate.setRepeatCount(ValueAnimator.INFINITE);
        startAngleRotate.setInterpolator(new LinearInterpolator());
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
    public void setProgress(float aValue) {
        if ( getViewProgressListener() != null ){
            float sweepAngle = aValue * PERCENTAGE_TO_ANGLE_COEF;
            if ( isDownloadFinish(sweepAngle ) ){
                Log.d(TAG, "setProgress :: aValue = " + aValue + "; sweepAngle = " + sweepAngle  );
                cancel();
            }
            getViewProgressListener().setSweepAngleProgressValue( aValue );
            getViewProgressListener().invalidate();
        }
    }

    private boolean isDownloadFinish(float sweepAngle) {
        return sweepAngle - FINAL_ACTUAL_ANGLE > 0.f;
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
