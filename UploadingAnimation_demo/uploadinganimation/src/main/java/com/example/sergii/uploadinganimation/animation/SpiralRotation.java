package com.example.sergii.uploadinganimation.animation;

import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
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

    private static final long ANIMATION_DURATION = 1000;
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
                        Animation.RELATIVE_TO_PARENT, -.7f, Animation.RELATIVE_TO_PARENT, 0.f,
                        Animation.RELATIVE_TO_PARENT, 0.f, Animation.RELATIVE_TO_PARENT, 0.f
                );
        translateAnimation.setInterpolator(accelerate);
        translateAnimation.setDuration(ANIMATION_DURATION);
//        animSet.addAnimation(translateAnimation);

        ScaleAnimation scale = new ScaleAnimation(0.2f, 1.f, 0.2f, 1.f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(ANIMATION_DURATION);
        scale.setDuration(ANIMATION_DURATION);
//        scale.setInterpolator(new AccelerateInterpolator(3));
        animSet.addAnimation(scale);

        rotateAnimation = new RotateAnimation(-320, 0f,
                Animation.RELATIVE_TO_SELF, .55f,
                Animation.RELATIVE_TO_SELF, .8f);
        rotateAnimation.setDuration(ANIMATION_DURATION);
        rotateAnimation.setDuration(ANIMATION_DURATION);
//        rotateAnimation.setInterpolator(new AccelerateInterpolator(3));
        animSet.addAnimation(rotateAnimation);


        animSet.setFillAfter(true);

    }

    public void setView(ImageView aView) {
        this.view = aView;
    }

    public void cancel() {
        
    }

    public void start() {
        if ( view == null ){
            return;
        }
        view.startAnimation(animSet);
    }

    public void resetView() {
        
    }
}
