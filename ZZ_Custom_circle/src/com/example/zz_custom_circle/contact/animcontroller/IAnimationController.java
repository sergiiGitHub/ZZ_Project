package com.example.zz_custom_circle.contact.animcontroller;

import android.view.animation.Animation.AnimationListener;

import com.example.zz_custom_circle.contact.IAddContactView;

public interface IAnimationController  {
	void cancel();
	void start();
	
	IAddContactView getAddContactView();
	void setAddContactView( IAddContactView aAddContactView );

	AnimationListener getExternalAnimationListener();
	void setExternalAnimationListener(AnimationListener externalAnimationListener);
}
