package com.example.sergii.myapplication.module.animation;

/**
 * Created by sergii on 15.11.15.
 */
public interface IProgressRingAnimation {

    void start();
    void cancel();

    void setView(IViewProgressAnimationListener aViewFiniteListener);
}
