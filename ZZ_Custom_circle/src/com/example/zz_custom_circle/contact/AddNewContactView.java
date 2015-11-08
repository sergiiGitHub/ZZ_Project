package com.example.zz_custom_circle.contact;

import com.example.zz_custom_circle.R;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AddNewContactView implements IAddContactView {

	private TextView textView;
	private ImageView imageIcon;
	private ImageView imageBackground;
	
	public AddNewContactView( Activity aActivity ) {
		imageIcon = (ImageView) aActivity.findViewById(R.id.add_new_contact_icon);
		imageBackground = (ImageView) aActivity.findViewById(R.id.add_new_contact_bg);
		textView = (TextView) aActivity.findViewById(R.id.add_new_contact_text);
	}

	@Override
	public TextView getText() {
		return textView;
	}

	@Override
	public ImageView getIcon() {
		return imageIcon;
	}

	public ImageView getImageBackground() {
		return imageBackground;
	}
	
	@Override
	public void setForegroundVisibility(int aVisibilty) {
		getText().setVisibility(aVisibilty);
		getIcon().setVisibility(aVisibilty);
	}
	
}
