package com.example.zz_custom_circle;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;

import com.example.zz_custom_circle.contact.AddNewContact;

public class MainActivity extends Activity implements AnimationListener {

	private static final int RESULT_LOAD_IMAGE = 1;
	private static final String TAG = MainActivity.class.getSimpleName();

	private AddNewContact addNewContact; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initAddNewContact();		
		initButtonLoadImage();
		initButtonReset();
	}

	private void initAddNewContact() {
		addNewContact = new AddNewContact(this);
		addNewContact.setInternalReactionOnClick(AddNewContact.ON_CLICK_BEHAVIOR_HIDE);
		addNewContact.setExternalAnimationListener(this);
	}

	private void initButtonLoadImage() {

        Button buttonLoadImage = (Button) this.findViewById(R.id.button_load_picture);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
            	addNewContact.cancelAnimation();
            }
        });
	}
	
	private void initButtonReset() {
        Button buttonLoadImage = (Button) this.findViewById(R.id.reset);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
            	addNewContact.reset();
            }
        });
	}
	
	private void loadImage() {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
	}
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        addNewContact.setTextVisibility( View.VISIBLE );
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = null;
            String picturePath = null;
            try {
            	cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
			} catch (Exception e) {
				Log.d( TAG, "read directory exceprion" );
			} finally{
				if ( cursor != null ){
					cursor.close();
				}
			}          

            if ( picturePath != null ){
            	updateContactItemByValue( picturePath );
//            	updateContactItemByResource();
            }
        } else {
        	addNewContact.setForegroundVisibility(View.VISIBLE);
        }
    }

	private void updateContactItemByValue(String picturePath) {
		addNewContact.onNewContactAdd("New Name", picturePath);
	}
    
	private void updateContactItemByResource() {
		addNewContact.setContactName(R.string.new_name);
		addNewContact.setContactNameColorByResource( R.color.white );
		addNewContact.setContactImageBackground(R.drawable.ic_launcher);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		//TODO uncoment before commit.
		loadImage();
//		fixImpl();
//		addNewContact.setInternalReactionOnClick(AddNewContact.ON_CLICK_BEHAVIOR_HIDE);
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
	}    

}
