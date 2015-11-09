package com.example.zz_custom_circle.contact.animcontroller;

import com.example.zz_custom_circle.R;
import com.example.zz_custom_circle.contact.IAddContactView;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

public class AnimationHideController implements AnimationListener
,IAnimationController{
	
	private static final String TAG = AnimationHideController.class.getSimpleName();

	private Animation iconAnimationHide;
	private Animation opacityAnimationHide;
	
	private IAddContactView addContactView;
	
	private AnimationListener externalAnimationListener;

	private boolean isCancel;
	
	public AnimationHideController(Activity aActivity){
		initAnimation(aActivity);
	}
	
	private void initAnimation(Activity aActivity) {
		iconAnimationHide = AnimationUtils.loadAnimation(aActivity, R.anim.add_new_contact_icon_hide_scale );
		opacityAnimationHide = AnimationUtils.loadAnimation(aActivity, R.anim.add_new_contact_text_hide_opacity );
		opacityAnimationHide.setAnimationListener(this);
	}

	@Override
	public void start() {
		
		if ( getAddContactView() == null ){
			return;
		}
		
		getAddContactView().getText().startAnimation( opacityAnimationHide );
		getAddContactView().getIcon().startAnimation( iconAnimationHide );
	}

	public IAddContactView getAddContactView() {
		return addContactView;
	}

	public void setAddContactView(IAddContactView addContactView) {
		this.addContactView = addContactView;
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		Log.d( TAG, "onAnimationEnd" );
		if ( isCancel() ){
			Log.d( TAG, "onAnimationEnd :: cancel " );
			setIsCancel(false);
			return;
		}
		Log.d( TAG, "onAnimationEnd :: NOT cancel " );		
		if ( getExternalAnimationListener() != null ){
			getExternalAnimationListener().onAnimationEnd(arg0);
		}
		internalReaction();
	}

	private void internalReaction() {
		if ( getAddContactView() != null ) {
			getAddContactView().setForegroundVisibility(View.GONE);
		}
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

	public AnimationListener getExternalAnimationListener() {
		return externalAnimationListener;
	}

	@Override
	public void setExternalAnimationListener(AnimationListener externalAnimationListener) {
		this.externalAnimationListener = externalAnimationListener;
	}

	@Override
	public void cancel() {
		
		setIsCancel( true );
		if ( getAddContactView() == null ){
			return;
		}
		
		getAddContactView().getIcon().clearAnimation();
		getAddContactView().getText().clearAnimation();
	}

	private void setIsCancel(boolean aIsCancel) {
		isCancel = aIsCancel; 
	}
	
	private boolean isCancel() {
		return isCancel; 
	}

}
