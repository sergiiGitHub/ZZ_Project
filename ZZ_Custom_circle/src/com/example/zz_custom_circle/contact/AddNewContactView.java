package com.example.zz_custom_circle.contact;

import com.example.zz_custom_circle.R;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AddNewContactView extends View implements IAddContactView {

	private TextView textView;
	private ImageView imageIcon;
	
	public AddNewContactView(Context context) {
		super(context);
	}
	
	public AddNewContactView(Context context , AttributeSet aAttribute) {
		super(context, aAttribute);
	}

	public void initAnotherComponent(Activity aActivity) {
		imageIcon = (ImageView) aActivity.findViewById(R.id.add_new_contact_icon);
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

	@Override
	public void setForegroundVisibility(int aVisibilty) {
		getText().setVisibility(aVisibilty);
		getIcon().setVisibility(aVisibilty);
	}
}
