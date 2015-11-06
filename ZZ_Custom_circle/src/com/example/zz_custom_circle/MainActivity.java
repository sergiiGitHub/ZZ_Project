package com.example.zz_custom_circle;

import com.example.zz_custom_circle.contact.AddNewConatact;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new_contact_layout);
		
		AddNewConatact addNewContact = new AddNewConatact(this);
	}

}
