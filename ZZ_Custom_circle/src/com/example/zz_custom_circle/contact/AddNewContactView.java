package com.example.zz_custom_circle.contact;

import com.example.zz_custom_circle.R;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class AddNewContactView {
	private TextView textView;
	private ImageView imageIcon;
	private ImageView imageBackground;
	
	//TODO move to animationController
	private Animation iconAnimationHide;
	private Animation opacityAnimationHide;
	
	public AddNewContactView( Activity aActivity ){
		initViewComponent(aActivity);
		initAnimation(aActivity);
	}

	private void initAnimation(Activity aActivity) {
		iconAnimationHide = AnimationUtils.loadAnimation(aActivity, R.anim.add_new_contact_icon_hide_scale );
		opacityAnimationHide = AnimationUtils.loadAnimation(aActivity, R.anim.add_new_contact_text_hide_opacity );
		iconAnimationHide.setAnimationListener(new AnimaionListenerImpl());
	}

	private void initViewComponent(Activity aActivity) {
		imageIcon = (ImageView) aActivity.findViewById(R.id.add_new_contact_icon);
		imageBackground = (ImageView) aActivity.findViewById(R.id.add_new_contact_bg);
		textView = (TextView) aActivity.findViewById(R.id.add_new_contact_text);
	}

	public void setOnClicListener(OnClickListener aClickListener) {
		imageBackground.setOnClickListener(aClickListener);
	}
	
	public void startHideAnimation() {
		textView.startAnimation( opacityAnimationHide );
		imageIcon.startAnimation( iconAnimationHide );
	}
	
	
}
