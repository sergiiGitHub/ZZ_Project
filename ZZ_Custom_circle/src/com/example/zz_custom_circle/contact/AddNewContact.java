package com.example.zz_custom_circle.contact;

import java.io.InputStream;

import com.example.zz_custom_circle.R;
import com.example.zz_custom_circle.contact.animcontroller.AnimationHideController;
import com.example.zz_custom_circle.contact.animcontroller.AnimationNoActionController;
import com.example.zz_custom_circle.contact.animcontroller.AnimationShowController;
import com.example.zz_custom_circle.contact.animcontroller.IAnimationController;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class AddNewContact implements OnClickListener {

	private static final String TAG = AddNewContact.class.getSimpleName(); 

	private static final int ON_CLICK_BEHAVIOR_SIZE = 3;
	public static final int ON_CLICK_BEHAVIOR_NOTHING = 0;
	public static final int ON_CLICK_BEHAVIOR_HIDE = 1;
	public static final int ON_CLICK_BEHAVIOR_SHOW_HOLD_ON_ANIMATION = 2;
	
	private IAnimationController arrayController[] = new IAnimationController[ON_CLICK_BEHAVIOR_SIZE]; 
	
	private final Context context;

	private AddNewContactView view;

	private OnClickListener externalOnClickListener;
	
	public int onClickReaction = ON_CLICK_BEHAVIOR_NOTHING;
	
	public AddNewContact(Activity aActivity) {
		context = aActivity;
		initView( aActivity );
		initAnimationController( aActivity );
	}

	private void initView(Activity aActivity) {
		view = new AddNewContactView( aActivity );
		view.getImageBackground().setOnClickListener( this );
	}
	
	private void initAnimationController(Activity aActivity) {
		arrayController[ON_CLICK_BEHAVIOR_NOTHING] = new AnimationNoActionController();
		arrayController[ON_CLICK_BEHAVIOR_HIDE] = new AnimationHideController(aActivity);
		arrayController[ON_CLICK_BEHAVIOR_SHOW_HOLD_ON_ANIMATION] = new AnimationShowController(aActivity);

		for ( IAnimationController item : arrayController ){
			item.setAddContactView(view);
		}
	}

	@Override
	public void onClick(View aView) {
		Log.d( TAG, "onClick" );
		arrayController[getInternalReactionOnClick()].start();
		
		if ( getExternalOnClickListener() != null ){
			getExternalOnClickListener().onClick(aView);
		}
	}
	
	private int getInternalReactionOnClick() {
		return onClickReaction;
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
	    final InputStream is = context.getResources().openRawResource(aResourceId);
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
	
	public void setContactNameColorByAttribute(int aResourceAttr, TypedValue aTypedValue) {
		if (context.getTheme().resolveAttribute(aResourceAttr, aTypedValue, true)) {
			view.getText().setTextColor(aTypedValue.data);
		}
	}
	
	public void setTextVisibility(int aVisible) {
		view.getText().setVisibility(aVisible);
		view.getText().bringToFront();
	}
	
	public void setInternalReactionOnClick( int aReaction ){
		onClickReaction = aReaction;
	}

	public void setExternalAnimationListener(AnimationListener externalAnimationListener) {
		for ( IAnimationController item : arrayController ){
			item.setExternalAnimationListener(externalAnimationListener);
		}
	}
	
	public void cancelAnimation(){
		arrayController[getInternalReactionOnClick()].cancel();
		setInternalReactionOnClick(ON_CLICK_BEHAVIOR_SHOW_HOLD_ON_ANIMATION);
	}

	public void setForegroundVisibility(int aVisible) {
		view.setForegroundVisibility(aVisible);
	}

	public void setIconVisibility(int aVisible) {
		view.getIcon().setVisibility(aVisible);
	}

	public void reset() {
		Log.d(TAG, "reset");

		setInternalReactionOnClick(ON_CLICK_BEHAVIOR_SHOW_HOLD_ON_ANIMATION);
		setForegroundVisibility( View.VISIBLE );
		setContactNameColor( Color.BLACK );
		//TODO improve move to view part
		float merginBotom = context.getResources().getDimension(
				R.dimen.add_new_contact_text_mergin_bottom );
		view.getText().setY( view.getImageBackground().getHeight() - 
				( view.getText().getHeight() + merginBotom ) );

		setContactName(R.string.add_new_contact);
		view.getImageBackground().setImageDrawable(null);
	}

	public void setNewContactAdd(String aString, int aColor, String aPicturePath) {
		view.getText().setText( aString );
		view.getText().setTextColor( aColor );
		view.getText().setY( view.getImageBackground().getHeight() -
				view.getText().getHeight() );
		
		setContactImageBackground( aPicturePath );
	}

}

