package com.example.sergii.rotationcircleview.view.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.sergii.rotationcircleview.R;

/**
 * Created by sergii on 11.11.15.
 */
public class AnimationController implements FlipFlopAnimation.IController,
        Animator.AnimatorListener{

    private static final String TAG = AnimationController.class.getSimpleName();

    private FlipFlopAnimation flipFlopAnimation;
    private Animator flipInCancel;
    private int animDuration;
    private View primaryView;
    private View secondaryView;

    boolean isForward = true;

    public AnimationController( Context aContext ){
        initAnimation(aContext);
    }

    private void initAnimation(Context aContext) {
        flipFlopAnimation = new FlipFlopAnimation( aContext );
        flipFlopAnimation.setController(this);
        flipFlopAnimation.setExternalListener(this);

        flipInCancel = AnimatorInflater.loadAnimator(aContext,
                R.animator.rcv_flip_in_cencel);
    }

    // TODO: 13.11.15
    public int getAnimDuration() {
        return animDuration;
    }

    public void setAnimDuration(int animDuration) {
        Log.d(TAG, "setAnimDuration() called with: " + "animDuration = [" + animDuration + "]");
        this.animDuration = animDuration;
    }

    @Override
    public View getHideView() {
        return isForward ? primaryView : secondaryView;
    }

    @Override
    public View getShowView() {
        return isForward ? secondaryView : primaryView;
    }

    public void setPrimaryView(View primaryView) {
        this.primaryView = primaryView;
        flipInCancel.setTarget(primaryView);
    }

    public void setSecondaryView(View secondaryView) {
        this.secondaryView = secondaryView;
    }

    public void start() {
        flipFlopAnimation.start();
    }

    public void cancel() {
        flipFlopAnimation.cancel();
        primaryView.setVisibility(View.VISIBLE);
        flipInCancel.start();
        secondaryView.setVisibility(View.INVISIBLE);
        isForward = true;
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        swapState();
    }

    private void swapState() {
        isForward = !isForward;
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
