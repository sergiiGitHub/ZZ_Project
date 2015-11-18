package com.example.sergii.myapplication.module.animation;

/**
 * Created by sergii on 15.11.15.
 */
public interface IViewProgressAnimationListener extends IViewFiniteAnimationListener {

    void setActualAngleProgressAnimation(float actualAngle);
    void setSweepAngleProgressValue(float sweepAngle);

    float getProgressCurrentAngle();
}
