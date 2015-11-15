package com.example.sergii.myapplication.module.animation;

import android.animation.ValueAnimator;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by sergii on 15.11.15.
 */
public class FiniteRingAnimation implements IFiniteRingAnimation{

    private static final long ANIMATION_DURATION = 1000;
    private static final String TAG = FiniteRingAnimation.class.getSimpleName();
    private ValueAnimator startAngleRotate;
    private IViewFiniteAnimationListener viewFiniteListener;

    private void createAnimation() {
        final float startAngle = getViewFiniteListener().getInitialAngle();
        startAngleRotate = ValueAnimator.ofFloat(startAngle, 360);
        startAngleRotate.setDuration(ANIMATION_DURATION);
        startAngleRotate.setInterpolator(new DecelerateInterpolator(2));
        startAngleRotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentAngle = (Float) animation.getAnimatedValue();
                getViewFiniteListener().setActualAngleFiniteAnimation(currentAngle);
                getViewFiniteListener().invalidate();

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
        getAnimation().start();
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

}
