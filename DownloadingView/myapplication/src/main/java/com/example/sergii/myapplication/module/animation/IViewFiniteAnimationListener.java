package com.example.sergii.myapplication.module.animation;

/**
 * Created by sergii on 15.11.15.
 */
public interface IViewFiniteAnimationListener {

    void setActualAngleFiniteAnimation(float actualAngle);
    float getInitialAngle();

    void invalidate();

}
