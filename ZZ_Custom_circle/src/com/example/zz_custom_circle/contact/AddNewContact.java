package com.example.zz_custom_circle.contact;

import java.io.InputStream;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class AddNewContact implements OnClickListener {

	private static final String TAG = AddNewContact.class.getSimpleName(); 
	
	public static final int ON_CLICK_BEHAVIOR_NOTHING = 0;
	public static final int ON_CLICK_BEHAVIOR_HIDE = 1;	
	
	private final Resources resource;

	private AddNewContactView view;
	private AnimationHideController animationHideController;
	
	private OnClickListener externalOnClickListener;
	
	public int onClickReaction = ON_CLICK_BEHAVIOR_NOTHING;
	
	public AddNewContact(Activity aActivity) {
		resource = aActivity.getResources();
		initView( aActivity );
		initAnimationController( aActivity );
	}

	private void initView(Activity aActivity) {
		view = new AddNewContactView( aActivity );
		view.getImageBackground().setOnClickListener( this );
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
	
	public void setContactImageBackground(String aPath) {
		Log.d( TAG, "path = " + aPath );
		setContactImageBackground(BitmapFactory.decodeFile(aPath));
	}
	
	public void setContactImageBackground(Bitmap aNewBitmap) {
	    ImageView viewBg = view.getImageBackground();

	    int width = viewBg.getWidth();
	    int height = viewBg.getHeight();
	    Log.d( TAG, "setContactImageBackground :: width = " + width + "; Height = " + height ); 

	    Bitmap scaledBitmap = Bitmap.createScaledBitmap(aNewBitmap, width, height, true);
	    viewBg.setImageBitmap(scaledBitmap);
	}
	
	public void setContactImageBackground(int aResourceId) {
	    final InputStream is = resource.openRawResource(aResourceId);
	    final Bitmap originalBitmap = BitmapFactory.decodeStream(is);
	    setContactImageBackground( originalBitmap ); 	
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
	
	public void setTextVisibility(int aVisible) {
		view.getText().setVisibility(aVisible);
		view.getText().bringToFront();
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

	public void setIconVisibility(int aVisible) {
		view.getIcon().setVisibility(aVisible);
	}

	public void reset() {
		Log.d(TAG, "reset");
		setForegroundVisibility( View.VISIBLE );
		setContactNameColor( Color.BLACK );
		view.getImageBackground().setImageDrawable(null);
		view.getImageBackground().setImageResource(0);
		view.getImageBackground().invalidate();
	}

}

