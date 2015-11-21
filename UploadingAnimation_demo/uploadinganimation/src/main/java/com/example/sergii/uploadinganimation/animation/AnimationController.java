package com.example.sergii.uploadinganimation.animation;

import android.util.Log;

import com.example.sergii.uploadinganimation.animation.animationlistener.UploadAnimationListener;
import com.example.sergii.uploadinganimation.animation.viewanimationlistener.IViewFiniteAnimationListener;
import com.example.sergii.uploadinganimation.animation.viewanimationlistener.IViewProgressAnimationListener;


/**
 * Created by sergii on 14.11.15.
 */
public class AnimationController implements IUploadingAnimationController, UploadAnimationListener {

    private static final String TAG = AnimationController.class.getSimpleName();

    private FiniteRingAnimation firstRingAnimation;
    private ProgressRingAnimation secondRingAnimation;

    private UploadAnimationListener externalUploadAnimationListener;


    public AnimationController(){
        initAnimation();
    }

    private void initAnimation() {

        firstRingAnimation = new FiniteRingAnimation();

        secondRingAnimation = new ProgressRingAnimation();
        secondRingAnimation.setExternalListener(this);

    }

    public void setFirstRingView(IViewFiniteAnimationListener aView) {
        firstRingAnimation.setView(aView);
    }

    public void setSecondRingView(IViewProgressAnimationListener aView) {
        secondRingAnimation.setView(aView);
    }

    @Override
    public void cancel() {
        firstRingAnimation.cancel();
        secondRingAnimation.cancel();
    }

    @Override
    public void start() {
        cancelAllRunningAnimation();

        firstRingAnimation.start();
        secondRingAnimation.start();
    }

    @Override
    public void reset() {
        firstRingAnimation.resetView();
        secondRingAnimation.resetView();
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

        if ( !firstRingAnimation.isAnimationFinish() ){
            firstRingAnimation.cancel();
        }

        if ( !secondRingAnimation.isAnimationFinish() ){
            secondRingAnimation.cancel();
        }

    }

    @Override
    public void onProgressAnimationFinish() {
        Log.d(TAG, "onProgressAnimationFinish() called with: ");

        if ( getExternalUploadAnimationListener() != null ){
            getExternalUploadAnimationListener().onProgressAnimationFinish();
        }
    }

    public UploadAnimationListener getExternalUploadAnimationListener() {
        return externalUploadAnimationListener;
    }

    public void setExternalUploadAnimationListener(UploadAnimationListener externalUploadAnimationListener) {
        this.externalUploadAnimationListener = externalUploadAnimationListener;
    }
}
