package com.example.zz_custom_circle.contact;

import com.example.zz_custom_circle.R;
import com.example.zz_custom_circle.contact.AnimationController.IAddContactView;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class AddNewContactView implements IAddContactView {
	private TextView textView;
	private ImageView imageIcon;
	private ImageView imageBackground;
	
	public AddNewContactView( Activity aActivity ){
		initViewComponent(aActivity);	
	}

	private void initViewComponent(Activity aActivity) {
		imageIcon = (ImageView) aActivity.findViewById(R.id.add_new_contact_icon);
		imageBackground = (ImageView) aActivity.findViewById(R.id.add_new_contact_bg);
		textView = (TextView) aActivity.findViewById(R.id.add_new_contact_text);
	}

	public void setOnClicListener(OnClickListener aClickListener) {
		imageBackground.setOnClickListener(aClickListener);
	}

	@Override
	public TextView getText() {
		return textView;
	}

	@Override
	public ImageView getIcon() {
		return imageIcon;
	}

}
