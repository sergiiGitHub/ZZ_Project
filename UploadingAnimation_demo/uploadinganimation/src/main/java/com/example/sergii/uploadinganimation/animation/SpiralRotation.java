package com.example.sergii.uploadinganimation.animation;

import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;


/**
 * Created by sergii on 21.11.15.
 */
public class SpiralRotation {

    private static final long ANIMATION_DURATION = 800;
    private static final String TAG = SpiralRotation.class.getSimpleName();
    private AnimationSet animSet;
    private ImageView view;
    private TranslateAnimation translateAnimation;
    private RotateAnimation rotateAnimation;

    public SpiralRotation(  ){
        createAnimation();
    }

    private void createAnimation() {

        animSet = new AnimationSet(true);
        AccelerateInterpolator accelerate = new AccelerateInterpolator();
        translateAnimation = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, -0.9f, Animation.RELATIVE_TO_PARENT, .0f,
                        Animation.RELATIVE_TO_PARENT, -0.5f, Animation.RELATIVE_TO_PARENT, .0f
                );
        translateAnimation.setInterpolator(accelerate);
        translateAnimation.setDuration(ANIMATION_DURATION);
        animSet.addAnimation(translateAnimation);

        ScaleAnimation scale = new ScaleAnimation(0.2f, 1f, 0.2f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        float durationCoef = .6f;
        float delayCoef = 1 - durationCoef;
        scale.setDuration((long) (ANIMATION_DURATION * durationCoef));
        scale.setStartOffset((long) (ANIMATION_DURATION * delayCoef));
        scale.setInterpolator(new AccelerateInterpolator(8));
        animSet.addAnimation(scale);

        rotateAnimation = new RotateAnimation(-270, 0f,
                Animation.RELATIVE_TO_SELF, .65f,
                Animation.RELATIVE_TO_SELF, .65f);
        rotateAnimation.setDuration(ANIMATION_DURATION);
        rotateAnimation.setInterpolator(new DecelerateInterpolator(2));
        animSet.addAnimation(rotateAnimation);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.f, 1.0f);
        alphaAnimation.setDuration(ANIMATION_DURATION);
        animSet.addAnimation(alphaAnimation);

        animSet.setFillAfter(true);

    }

    public void setView(ImageView aView) {
        this.view = aView;
    }

    public void cancel() {
        Log.d(TAG, "cancel() called with: " + "");
        if ( view != null ){
            view.setVisibility(View.INVISIBLE);
        }
        animSet.cancel();

    }

    public void start() {
        if ( view == null ){
            return;
        }
        view.setVisibility(View.VISIBLE);
        view.startAnimation(animSet);
    }

    public void resetView() {
        
    }
}
