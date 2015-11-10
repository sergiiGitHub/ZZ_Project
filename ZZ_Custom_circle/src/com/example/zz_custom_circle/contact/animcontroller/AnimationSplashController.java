package com.example.zz_custom_circle.contact.animcontroller;

import com.example.zz_custom_circle.R;
import com.example.zz_custom_circle.contact.IAddContactView;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;

public class AnimationSplashController implements AnimationListener, IAnimationController {

	private static final String TAG = AnimationSplashController.class.getSimpleName();

	private final Animation animationSplash;
	
	private IAddContactView addContactView;
	
	public AnimationSplashController(Activity aActivity) {
		animationSplash = AnimationUtils.loadAnimation(aActivity,
				R.anim.anim_add_new_contact_splash );
		animationSplash.setAnimationListener(this);
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
	}

	@Override
	public void start() {
		if ( getAddContactView() == null ){
			return;
		}
		
		getAddContactView().getSplashView().setVisibility(View.VISIBLE);
		getAddContactView().getSplashView().startAnimation( animationSplash );
	}

	@Override
	public IAddContactView getAddContactView() {
		return addContactView;
	}
	
	@Override
	public void setAddContactView(IAddContactView aAddContactView) {
		addContactView= aAddContactView;
	}

	@Override
	public void setExternalAnimationListener(
			AnimationListener externalAnimationListener) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public AnimationListener getExternalAnimationListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		Log.d( TAG, "onAnimationEnd" );
		if ( getExternalAnimationListener() != null ){
			getExternalAnimationListener().onAnimationEnd(arg0);
		}
		getAddContactView().getSplashView().setVisibility(View.INVISIBLE);
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		Log.d( TAG, "onAnimationRepeat" );
		if ( getExternalAnimationListener() != null ){
			getExternalAnimationListener().onAnimationRepeat(arg0);
		}
	}

	@Override
	public void onAnimationStart(Animation arg0) {
		Log.d( TAG, "onAnimationEnd" );
		if ( getExternalAnimationListener() != null ){
			getExternalAnimationListener().onAnimationStart(arg0);
		}
	}




}
