package com.example.zz_custom_circle.contact;

import com.example.zz_custom_circle.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation.AnimationListener;

public class AddNewContact implements OnClickListener {

	private static final String TAG = AddNewContact.class.getSimpleName(); 
	
	public static final int ON_CLICK_BEHAVIOR_NOTHING = 0;
	public static final int ON_CLICK_BEHAVIOR_HIDE = 1;	
	
	private AddNewContactView view;
	private AnimationHideController animationHideController;
	
	private OnClickListener externalOnClickListener;
	
	public int onClickReaction = ON_CLICK_BEHAVIOR_NOTHING;
	
	public AddNewContact(Activity aActivity) {
		initView( aActivity );
		initAnimationController( aActivity );
	}

	private void initView(Activity aActivity) {
		view = (AddNewContactView) aActivity.findViewById(R.id.add_new_contact_view);
		view.initAnotherComponent( aActivity );
		view.setOnClickListener( this );
	}
	
	private void initAnimationController(Activity aActivity) {
		animationHideController = new AnimationHideController(aActivity);
		animationHideController.setAddContactView(view);
	}

	private void internalReaction() {
		switch (onClickReaction) {
		case ON_CLICK_BEHAVIOR_HIDE:
			setInternalReactionOnClick(ON_CLICK_BEHAVIOR_NOTHING);
			animationHideController.startHideAnimation();
			break;
		default:
			break;
		}
	}

	@Override
	public void onClick(View aView) {
		Log.d( TAG, "onClick" );
		internalReaction();
		
		if ( getExternalOnClickListener() != null ){
			getExternalOnClickListener().onClick(aView);
		}
	}
	
	public OnClickListener getExternalOnClickListener() {
		return externalOnClickListener;
	}

	public void setExternalOnClickListener(OnClickListener externalOnClickListener) {
		this.externalOnClickListener = externalOnClickListener;
	}
	
	public void setContactImage(String aPath) {
		setContactImage(BitmapFactory.decodeFile(aPath));
	}
	
	public void setContactImage(Bitmap aNewBitmap) {
		view.getIcon().setImageBitmap(aNewBitmap);
	}
	
	public void setContactImage(int aResourceId) {
		view.getIcon().setImageResource(aResourceId);
	}

	public void setContactName(String aNewName) {
		view.getText().setText(aNewName);
	}
	
	public void setContactName( int aResourceId ) {
		view.getText().setText(aResourceId);
	}

	public void setContactNameColor(int aColor) {
		view.getText().setTextColor(aColor);
	}

	public void setContactNameColorByResource(int aResourceId) {
		view.getText().setTextColor(aResourceId);
	}
	
	public void setInternalReactionOnClick( int aReaction ){
		onClickReaction = aReaction;
	}

	public void setExternalAnimationListener(AnimationListener externalAnimationListener) {
		animationHideController.setExternalAnimationListener(externalAnimationListener);
	}
	
	public void cancelAnimation(){
		setInternalReactionOnClick(ON_CLICK_BEHAVIOR_HIDE);
		animationHideController.cancel( );
	}

	public void setForegroundVisibility(int aVisible) {
		view.setForegroundVisibility(aVisible);
	}

}

