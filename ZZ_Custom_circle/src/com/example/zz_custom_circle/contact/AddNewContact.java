package com.example.zz_custom_circle.contact;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class AddNewContact implements OnClickListener {

	private static final String TAG = AddNewContact.class.getSimpleName(); 
	
	public static final int ON_CLICK_BEHAVIOR_NOTHING = 0;
	public static final int ON_CLICK_BEHAVIOR_HIDE = 1;	
	
	private AddNewContactView view;
	private AnimationController controller;
	
	private OnClickListener externalOnClickListener;
	
	public int onClickReaction = ON_CLICK_BEHAVIOR_NOTHING;
	
	public AddNewContact(Activity aActivity) {
		initView( aActivity );
		initAnimationController( aActivity );
	}

	private void initView(Activity aActivity) {
		view = new AddNewContactView(aActivity);
		view.setOnClicListener( this );
	}
	
	private void initAnimationController(Activity aActivity) {
		controller = new AnimationController(aActivity);
		controller.setAddContactView(view);
	}

	private void internalReaction() {
		switch (onClickReaction) {
		case ON_CLICK_BEHAVIOR_HIDE:
			controller.startHideAnimation();
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


	
}
