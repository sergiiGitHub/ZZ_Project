package com.example.sergii.myapplication.module.animation;

/**
 * Created by sergii on 15.11.15.
 */
public interface IViewMoveDownAnimationListener {

    void setShift(int shift);
    void invalidate();

    int getFinalValue();
}
