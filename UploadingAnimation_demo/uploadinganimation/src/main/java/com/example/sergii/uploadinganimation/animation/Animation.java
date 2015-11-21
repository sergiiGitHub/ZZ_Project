package com.example.sergii.uploadinganimation.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;

import com.example.sergii.uploadinganimation.animation.viewanimationlistener.IViewAnimationListener;

/**
 * Created by sergii on 18.11.15.
 */
public abstract class Animation implements Animator.AnimatorListener {

    private static final String TAG = Animation.class.getSimpleName();

    private ValueAnimator valueAnimator;
    private IViewAnimationListener viewFiniteListener;
    private boolean isAnimationFinish;
    private boolean isCancel = false;

    protected abstract ValueAnimator createAnimation();

    public abstract void resetView();

    public IViewAnimationListener getViewAnimationListener() {
        return viewFiniteListener;
    }

    private void setIsAnimationCancel(boolean aIsCancel) {
        isCancel = aIsCancel;
    }

    protected ValueAnimator getAnimation() {
        if (valueAnimator == null) {
            valueAnimator = createAnimation();
        }
        return valueAnimator;
    }

    private void resetAnimation() {
        if (valueAnimator != null) {
            valueAnimator.cancel();
            valueAnimator.removeAllListeners();
            valueAnimator = null;
        }
    }

    private void setIsAnimationFinish(boolean aIsAnimationFinish) {
        this.isAnimationFinish = aIsAnimationFinish;
    }

    public void start() {
        if (getViewAnimationListener() == null) {
            Log.e(TAG, "start() called with: view == null ");
            return;
        }

        getAnimation().start();
        setIsAnimationFinish(false);
        setIsAnimationCancel(false);
    }

    public void cancel() {
        setIsAnimationCancel(true);
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }

        if (getViewAnimationListener() != null) {
            resetView();
        }
    }

    public void setView(IViewAnimationListener aView) {
        resetAnimation();
        this.viewFiniteListener = aView;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public boolean isAnimationFinish() {
        return isAnimationFinish;
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        Log.d(TAG, "onAnimationEnd() called with: " + "animation = [" + animation + "]");
        setIsAnimationFinish(true);
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

}