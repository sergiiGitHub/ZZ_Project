package com.example.sergii.rotationcircleview.view.animation;

import android.util.Log;

/**
 * Created by sergii on 11.11.15.
 */
public class AnimationController {

    private static final String TAG = AnimationController.class.getSimpleName();
    private int animDuration;

    public int getAnimDuration() {
        return animDuration;
    }

    public void setAnimDuration(int animDuration) {
        Log.d(TAG, "setAnimDuration() called with: " + "animDuration = [" + animDuration + "]");
        this.animDuration = animDuration;
    }
}
