package com.example.sergii.rotationcircleview.view.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.sergii.rotationcircleview.R;

/**
 * Created by sergii on 13.11.15.
 */
public class FlipFlopAnimation implements Animator.AnimatorListener {

    private static final String TAG = FlipFlopAnimation.class.getSimpleName();
    private Animator.AnimatorListener externalAnimationListener;


    public void setExternalListener(Animator.AnimatorListener externalAnimationListener) {
        this.externalAnimationListener = externalAnimationListener;
    }

    public Animator.AnimatorListener getExternalListener() {
        return externalAnimationListener;
    }

    interface IController {
        View getHideView();
        View getShowView();
    }

    private Animator flipIn;
    private Animator flipOut;

    private boolean isFirstTime;
    private IController controller;

    public FlipFlopAnimation(Context aContext) {
        initAnimation(aContext);
    }

    private void initAnimation(Context aContext) {
        flipOut = AnimatorInflater.loadAnimator(aContext,
                R.animator.rcv_flip_left_out);
        flipOut.addListener(this);

        flipIn = AnimatorInflater.loadAnimator(aContext,
                R.animator.rcv_flip_left_in);
        flipIn.addListener(this);
    }

    public void start(){
        if ( getController() == null
                || getController().getHideView() == null
                || getController().getShowView() == null ){
            return;
        }

        isFirstTime = true;
        flipIn.setTarget(getController().getShowView());
        flipOut.setTarget(getController().getHideView());
        flipOut.start();
    }

    public void cancel() {
        flipIn.cancel();
        flipOut.cancel();

    }

    public IController getController() {
        return controller;
    }

    public void setController(IController controller) {
        this.controller = controller;
    }

    @Override
    public void onAnimationStart(Animator animation) {
        Log.d(TAG, "onAnimationStart() called with: " + "animation = [" + animation + "]");
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        Log.d(TAG, "onAnimationEnd() called with: " + "animation = [" + animation + "]");
        if ( isFirstTime ){
            isFirstTime = false;
            getController().getHideView().setVisibility(View.INVISIBLE);
            getController().getShowView().setVisibility(View.VISIBLE);
            flipIn.start();
        } else {
            if( getExternalListener()!= null ){
                getExternalListener().onAnimationEnd(animation);
            }
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        Log.d(TAG, "onAnimationCancel() called with: " + "animation = [" + animation + "]");
        if( getExternalListener()!= null ){
            getExternalListener().onAnimationEnd(animation);
        }
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
        Log.d(TAG, "onAnimationRepeat() called with: " + "animation = [" + animation + "]");
    }
}
