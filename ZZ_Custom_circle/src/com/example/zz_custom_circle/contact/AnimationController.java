package com.example.zz_custom_circle.contact;

import com.example.zz_custom_circle.R;

import android.app.Activity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

// TODO controll only hide animation
public class AnimationController implements AnimationListener {
	
	private static final String TAG = AnimationController.class.getSimpleName();

	public interface IAddContactView{
		TextView getText();
		ImageView getIcon();
	}
	
	private Animation iconAnimationHide;
	private Animation opacityAnimationHide;
	
	private IAddContactView addContactView;
	
	public AnimationController(Activity aActivity){
		initAnimation(aActivity);
	}
	
	private void initAnimation(Activity aActivity) {
		iconAnimationHide = AnimationUtils.loadAnimation(aActivity, R.anim.add_new_contact_icon_hide_scale );
		opacityAnimationHide = AnimationUtils.loadAnimation(aActivity, R.anim.add_new_contact_text_hide_opacity );
		iconAnimationHide.setAnimationListener(this);
	}

	public void startHideAnimation() {
		
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
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		Log.d( TAG, "onAnimationEnd" );
	}

	@Override
	public void onAnimationStart(Animation arg0) {
		Log.d( TAG, "onAnimationEnd" );
	}
}
