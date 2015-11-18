package com.example.sergii.myapplication.module.animation;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;

import com.example.sergii.myapplication.module.animation.listener.IColorChangeAnimationListener;

/**
 * Created by sergii on 18.11.15.
 */
public class ColorChangeAnimation extends DownloadAnimation {

    private static final long ANIMATION_DURATION = 500;
    private IViewColorChangeAnimationListener viewColorChangeAnimationListener;
    private int initialColor = -1;
    private IColorChangeAnimationListener colorChangeAnimationListener;

    @Override
    protected ValueAnimator createAnimation() {

        initialColor = viewColorChangeAnimationListener.getFromColor();
        ValueAnimator anim = new ValueAnimator();
        anim.setIntValues(initialColor, viewColorChangeAnimationListener.getToColor());
        anim.setEvaluator(new ArgbEvaluator());
        anim.setDuration(ANIMATION_DURATION);
        // TODO: 18.11.15 implement in abstract
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                updateView((Integer) valueAnimator.getAnimatedValue());
            }
        });

        return anim;
    }

    private void updateView(Integer animatedValue) {
        viewColorChangeAnimationListener.setBackgroundColor(animatedValue);
        viewColorChangeAnimationListener.invalidate();
    }

    public void setView(IViewColorChangeAnimationListener aView) {

        super.setView(aView);
        viewColorChangeAnimationListener = aView;
        initialColor = -1;
    }

    public IViewColorChangeAnimationListener getView(){
        return viewColorChangeAnimationListener;
    }


    @Override
    public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        if( !isCancel() &&  colorChangeAnimationListener != null){
            colorChangeAnimationListener.onColorChangeAnimationFinish();
            resetView();
        }
    }

    @Override
    public void resetView() {
        if ( initialColor != -1 ){
            viewColorChangeAnimationListener.setBackgroundColor(initialColor);
        }
    }

    public void setExternalListener(IColorChangeAnimationListener colorChangeAnimationListener) {
        this.colorChangeAnimationListener = colorChangeAnimationListener;
    }
}
