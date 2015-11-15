package com.example.sergii.myapplication.module.animation;

import android.view.View;

/**
 * Created by sergii on 14.11.15.
 */
public interface IDownloadController {

    void setFirstRingView( IViewFiniteAnimationListener aView );
    void setSecondRingView( IViewProgressAnimationListener aView );
    void setForegroundView( View aView );

    void cancel();
    void start();

}
