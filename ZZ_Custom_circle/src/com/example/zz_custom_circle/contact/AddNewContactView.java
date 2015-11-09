package com.example.zz_custom_circle.contact;

import com.example.zz_custom_circle.R;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

public class AddNewContactView implements IAddContactView {

	private TextView textView;
	private TextView textViewHoldOn;
	private ImageView imageIcon;
	private ImageView imageBackground;
	
	public AddNewContactView( Activity aActivity ) {
		initView(aActivity);
	}

	private void initView(Activity aActivity) {
		imageIcon = (ImageView) aActivity.findViewById(R.id.add_new_contact_icon);
		imageBackground = (ImageView) aActivity.findViewById(R.id.add_new_contact_bg);
		textView = (TextView) aActivity.findViewById(R.id.add_new_contact_text);
		textViewHoldOn = (TextView) aActivity.findViewById(R.id.add_new_contact_hold_to_records);
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
	
	//TODO setAddContactVisibility
	//TODO setAddedContactVisibility
	@Override
	public void setForegroundVisibility(int aVisibilty) {
		getText().setVisibility(aVisibilty);
		getIcon().setVisibility(aVisibilty);
	}

	@Override
	public TextView getTextHoldOn() {
		return textViewHoldOn;
	}
	
}
