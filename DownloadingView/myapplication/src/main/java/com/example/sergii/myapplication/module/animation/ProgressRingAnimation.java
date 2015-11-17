package com.example.sergii.myapplication.module.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.animation.LinearInterpolator;

/**
 * Created by sergii on 15.11.15.
 */
public class ProgressRingAnimation implements Animator.AnimatorListener {

    private static final String TAG = ProgressRingAnimation.class.getSimpleName();
    private static final long ANIMATION_DURATION = 1300;
    private static final float FINAL_ACTUAL_ANGLE = 360;
    private static final float FINAL_PROGRESS = 100;
    private static final float PERCENTAGE_TO_ANGLE_COEF = FINAL_ACTUAL_ANGLE / FINAL_PROGRESS;

    private ValueAnimator startAngleRotate;
    private IViewProgressAnimationListener viewProgressListener;
    private boolean isAnimationFinish = true;
    private boolean isCancel = false;
    private float currentAngle;
    private float progress;

    private Animator.AnimatorListener externalListener;

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
//        Log.d(TAG, "updateView() called with:aCurrentAngle = " + aCurrentAngle );
        getViewProgressListener().setActualAngleProgressAnimation(aCurrentAngle);
        getViewProgressListener().invalidate();
    }

    private void resetAnimation() {
        if ( startAngleRotate != null ) {
            startAngleRotate.cancel();
            startAngleRotate = null;
        }
    }

    public void start() {
        if ( getViewProgressListener() == null ){
            Log.e(TAG, "start() called with: view == null ");
        }
        isCancel = false;
        getAnimation().start();
        setIsAnimationFinish(false);
    }

    public void cancel() {
        isCancel = true;
        if ( startAngleRotate != null ) {
            startAngleRotate.cancel();
        }
        if ( getViewProgressListener() != null ){
            getViewProgressListener().setSweepAngleProgressValue(0);
            updateView(currentAngle);
        }
    }

    public void setView(IViewProgressAnimationListener aViewFiniteListener) {
        resetAnimation();
        this.viewProgressListener = aViewFiniteListener;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float aProgress) {

        if ( getViewProgressListener() != null) {
            this.progress = Math.min( aProgress, FINAL_PROGRESS );
            float sweepAngle = aProgress * PERCENTAGE_TO_ANGLE_COEF;

            if ( isDownloadFinish(sweepAngle ) ){
                Log.d(TAG, "setProgress :: aValue = " + aProgress + "; sweepAngle = " + sweepAngle  );
                startAngleRotate.cancel();
            }
            getViewProgressListener().setSweepAngleProgressValue( sweepAngle );
            getViewProgressListener().invalidate();
        }
    }

    private boolean isDownloadFinish(float sweepAngle) {
        return sweepAngle - FINAL_ACTUAL_ANGLE >= 0.f;
    }

    @Override
    public void onAnimationStart(Animator animation) {
        Log.d(TAG, "onAnimationStart() called with: " + "animation = [" + animation + "]");
        if (getExternalListener() != null){
            getExternalListener().onAnimationStart(animation);
        }
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        Log.d(TAG, "onAnimationEnd() called with: " + "animation = [" + animation + "]");
        setIsAnimationFinish( true );
        if ( !isCancel ){
            if (getExternalListener() != null){
                getExternalListener().onAnimationEnd(animation);
            }
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        Log.d(TAG, "onAnimationCancel() called with: " + "animation = [" + animation + "]");
        if (getExternalListener() != null){
            getExternalListener().onAnimationCancel(animation);
        }
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
        Log.d(TAG, "onAnimationRepeat() called with: " + "animation = [" + animation + "]");
        if (getExternalListener() != null){
            getExternalListener().onAnimationRepeat(animation);
        }
    }


    public Animator.AnimatorListener getExternalListener() {
        return externalListener;
    }

    public void setExternalListener(Animator.AnimatorListener externalListener) {
        this.externalListener = externalListener;
    }
}
