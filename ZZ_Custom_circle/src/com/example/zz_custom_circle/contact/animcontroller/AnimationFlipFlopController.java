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

public class AnimationFlipFlopController implements AnimatorListener, IAnimationController {
	
	private static final String TAG = AnimationFlipFlopController.class.getSimpleName();
	private static final Integer DELAY_FOR_FLIP_START = 500;
	
	private final Handler handler;
	private IAddContactView addContactView;
	
	private AnimationListener externalAnimationListener;

	private boolean isCancel;
	private boolean isForward = true;

	private Animator flipOut;
	private Animator flipIn;

	private Animator flipInCancel;

	private Runnable runnableFLipInFlipOut;
	private boolean isFirstTime;
	private boolean isAnimationFinish = true;



	public AnimationFlipFlopController(Activity aActivity){
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
		flipInCancel = AnimatorInflater.loadAnimator(aActivity, R.animator.add_new_contact_flip_left_in_cencel);
		flipIn.addListener(this);
		flipOut = AnimatorInflater.loadAnimator(aActivity, R.animator.add_new_contact_flip_left_out);
	}

	@Override
	public void start() {
		if ( getAddContactView() == null ){
			return;
		}
		
		if( !isAnimationFinish  ){
			cancel();
			return;
		}

		isAnimationFinish = false;
	
		isFirstTime = true;
		//TODO use camera here
		getAddContactView().getIcon().setImageResource(R.drawable.add_new_contact_stemp);
//		handler.postDelayed(runnableFLipInFlipOut, DELAY_FOR_FLIP_START);
		startAnimationInternal();
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

		isForward = true;
		isAnimationFinish = true;
		flipIn.cancel();
		flipOut.cancel();
		resetInitialPosition();
		getAddContactView().getTextHoldOn().setVisibility(View.INVISIBLE);
	}

	private void resetInitialPosition() {
		getAddContactView().getIcon().setAlpha(1.0f);
		flipInCancel.setTarget(getAddContactView().getIcon());
		flipInCancel.start();
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
		Log.d(TAG, "onAnimationEnd " );
		if ( isFirstTime ){
			isFirstTime = false;

			Log.d(TAG, "onAnimationEnd :: start agen" );
			if ( isCancel() ){
				setIsCancel(false);
			} else {
				handler.postDelayed(runnableFLipInFlipOut, DELAY_FOR_FLIP_START);
//				startAnimationInternal();
//				flipIn.removeListener(this);
//				flipOut.addListener(this);
			}
		} else {
			isFirstTime = true;
//			flipIn.addListener(this);
//			flipOut.removeAllListeners();
			isAnimationFinish = true;
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
