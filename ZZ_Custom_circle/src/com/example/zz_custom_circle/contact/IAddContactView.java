package com.example.zz_custom_circle.contact;

import android.widget.ImageView;
import android.widget.TextView;

public interface IAddContactView {
	TextView getText();
	TextView getTextHoldOn();
	ImageView getIcon();

	void setForegroundVisibility( int aVisibilty );
	
}
