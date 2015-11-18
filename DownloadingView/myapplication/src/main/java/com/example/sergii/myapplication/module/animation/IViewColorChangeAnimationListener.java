package com.example.sergii.myapplication.module.animation;

/**
 * Created by sergii on 18.11.15.
 */
public interface IViewColorChangeAnimationListener extends IViewAnimationListener {
    int getFromColor();
    int getToColor();

    void setBackgroundColor(Integer aColor);

    void setDrawRing(boolean aIsDrawRings);
}
