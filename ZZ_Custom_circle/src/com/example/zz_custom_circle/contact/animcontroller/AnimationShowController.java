package com.example.zz_custom_circle.contact.animcontroller;

import com.example.zz_custom_circle.R;
import com.example.zz_custom_circle.contact.IAddContactView;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation.AnimationListener;

public class AnimationShowController implements AnimatorListener, IAnimationController {
	
	private static final String TAG = AnimationShowController.class.getSimpleName();
	private static final Integer DELAY_FOR_FLIP_START = 1000;
	
	private final Handler handler;
	private IAddContactView addContactView;
	
	private AnimationListener externalAnimationListener;

	private boolean isCancel;
	private boolean isForward = true;

	private Animator flipOut;
	private Animator flipIn;
	private Runnable runnableFLipInFlipOut;
	private int count;

	public AnimationShowController(Activity aActivity){
		handler = new Handler();
		initAnimation(aActivity);
		
		initRunnable();
	}
	
	private void initRunnable() {
		runnableFLipInFlipOut = new Runnable() {
			@Override
			public void run() {
				if( !isCancel() ){
					startAnimationInternal();
				}
			}
		};
	}

	private void initAnimation(Activity aActivity) {
		flipIn = AnimatorInflater.loadAnimator(aActivity, R.animator.add_new_contact_flip_left_in);
		flipOut = AnimatorInflater.loadAnimator(aActivity, R.animator.add_new_contact_flip_left_out);
	}

	@Override
	public void start() {
		if ( getAddContactView() == null ){
			return;
		}
		
		if ( isCancel() ){
			reset();
		}
		//TODO use camera here		
		getAddContactView().getIcon().setImageResource(R.drawable.add_new_contact_stemp);
		setAnimationListener( this );
//		handler.postDelayed(runnableFLipInFlipOut, DELAY_FOR_FLIP_START);
		startAnimationInternal();
	}

	private void reset() {
		// TODO Auto-generated method stub
		count = 0;
		setIsCancel(false);
	}

	private void setAnimationListener(
			AnimationShowController animationListener) {
		flipOut.addListener(animationListener);
	}

	private void startAnimationInternal() {
		
		startFlipOutAnimation( isForward() ? getAddContactView().getIcon() : getAddContactView().getTextHoldOn() );
		startFlipInAnimation( isForward() ? getAddContactView().getTextHoldOn() : getAddContactView().getIcon() );
		swapState( );
	}

	private void startFlipInAnimation(View aView) {
		aView.setAlpha(1.f);
		aView.setVisibility(View.VISIBLE);
		flipIn.setTarget(aView);
		flipIn.start();
	}

	private void startFlipOutAnimation( View aView) {
		aView.setAlpha(1.f);
		aView.setVisibility(View.VISIBLE);
		flipOut.setTarget(aView);
		flipOut.start();
	}
	
	private void swapState() {
		isForward = !isForward;
	}

	private boolean isForward() {
		return isForward ;
	}

	public IAddContactView getAddContactView() {
		return addContactView;
	}

	public void setAddContactView(IAddContactView addContactView) {
		this.addContactView = addContactView;
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
		Log.d(TAG, "Cancel");
		setIsCancel( true );
		handler.removeCallbacks(runnableFLipInFlipOut);
		if ( getAddContactView() == null ){
			return;
		}

		getAddContactView().getIcon().clearAnimation();
		getAddContactView().getTextHoldOn().clearAnimation();
	}

	private void setIsCancel(boolean aIsCancel) {
		isCancel = aIsCancel; 
	}
	
	private boolean isCancel() {
		return isCancel; 
	}

	@Override
	public void onAnimationCancel(Animator animation) {
		Log.d(TAG, "onAnimationCancel" );
	}

	@Override
	public void onAnimationEnd(Animator animation) {
		Log.d(TAG, "onAnimationEnd" );
		if ( !isCancel() && count  == 0 ){
			count++;
			handler.postDelayed(runnableFLipInFlipOut, DELAY_FOR_FLIP_START);
		} else if ( isForward() ){
			startAnimationInternal();
		}
	}

	@Override
	public void onAnimationRepeat(Animator animation) {
		Log.d(TAG, "onAnimationRepeat" );
	}

	@Override
	public void onAnimationStart(Animator animation) {
		Log.d(TAG, "onAnimationStart" );
	}

}
