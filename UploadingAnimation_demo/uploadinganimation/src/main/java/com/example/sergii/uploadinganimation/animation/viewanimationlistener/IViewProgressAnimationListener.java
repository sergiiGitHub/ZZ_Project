package com.example.sergii.uploadinganimation.animation.viewanimationlistener;

/**
 * Created by sergii on 15.11.15.
 */
public interface IViewProgressAnimationListener extends IViewAnimationListener {

    void setActualAngleProgressAnimation(float actualAngle);
    void setSweepAngleProgressValue(float sweepAngle);

    float getProgressCurrentAngle();

    void setDrawRing(boolean isDrawRing);
}
