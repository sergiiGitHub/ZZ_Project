package com.example.zz_custom_circle.contact;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class AddNewConatact implements OnClickListener {

	private static final String TAG = AddNewConatact.class.getSimpleName(); 
	
	private final AddNewContactView view;
	
	private OnClickListener externalOnClickListener;
	
	public AddNewConatact(Activity aActivity) {
		view = new AddNewContactView(aActivity);
		view.setOnClicListener( this );
	}

	@Override
	public void onClick(View aView) {
		Log.d( TAG, "onClick" );
		view.startHideAnimation();
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
	
}

