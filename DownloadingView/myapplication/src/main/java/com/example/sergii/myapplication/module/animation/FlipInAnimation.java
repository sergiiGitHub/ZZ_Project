package com.example.sergii.myapplication.module.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;

import com.example.sergii.myapplication.R;


/**
 * Created by sergii on 11.11.15.
 */
public class FlipInAnimation implements Animator.AnimatorListener {

    private static final String TAG = FlipInAnimation.class.getSimpleName();

    private AnimatorSet flipIn;
    private View view;
    private Animator flipInCancel;

    private boolean isAnimationFinish = true;

    public FlipInAnimation(Context aContext){
        initAnimation(aContext);
    }

    private void initAnimation(Context aContext) {
        flipIn = (AnimatorSet) AnimatorInflater.loadAnimator(aContext,
                R.animator.rcv_flip_left_in);

        flipIn.addListener(this);

        flipInCancel = AnimatorInflater.loadAnimator(aContext,
                R.animator.rcv_flip_in_cencel);
    }

    public void setView(View aView) {
        view = aView;
        flipInCancel.setTarget(view);
        flipIn.setTarget(view);
    }

    public void start() {
        if (isAnimationFinish){
            isAnimationFinish = false;
            view.setVisibility(View.VISIBLE);

            flipIn.start();
        } else {
            cancel();
        }
    }

    public void cancel() {
        flipIn.cancel();
        flipInCancel.start();
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        isAnimationFinish = true;
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}

