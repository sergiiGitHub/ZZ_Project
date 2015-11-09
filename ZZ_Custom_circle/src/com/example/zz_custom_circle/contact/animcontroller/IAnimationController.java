package com.example.zz_custom_circle.contact.animcontroller;

import android.view.animation.Animation.AnimationListener;

import com.example.zz_custom_circle.contact.IAddContactView;

public interface IAnimationController  {
	void cancel();
	void start();
	
	void setAddContactView( IAddContactView aAddContactView );
	void setExternalAnimationListener(AnimationListener externalAnimationListener);
}
