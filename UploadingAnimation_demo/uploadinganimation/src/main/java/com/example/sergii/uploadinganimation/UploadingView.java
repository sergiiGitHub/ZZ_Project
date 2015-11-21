package com.example.sergii.uploadinganimation;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.sergii.uploadinganimation.animation.AnimationController;
import com.example.sergii.uploadinganimation.animation.IUploadingAnimationController;
import com.example.sergii.uploadinganimation.view.background.Background;

/**
 * Created by sergii on 21.11.15.
 */
public class UploadingView extends RelativeLayout{
    private static final String TAG = UploadingView.class.getSimpleName();
    private AnimationController animationController;
    private Background background;

    public UploadingView(Context context) {
        super(context);
    }

    public UploadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(attrs, 0);
    }

    public UploadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(attrs, defStyleAttr);
    }


    private void initAttributes(AttributeSet attrs, int defStyle) {
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.UploadingView, defStyle, 0);

        if ( attrs == null ){
            Log.e(TAG, "initAttributes() :: attrs == null");
        }

        animationController = new AnimationController();

        //bg
        background = createBackground(typedArray);
        addView(background);
        animationController.setFirstRingView(background);
        animationController.setSecondRingView(background);

        typedArray.recycle();
    }

    private Background createBackground(TypedArray typedArray) {
        final int bgCircleColor = typedArray.getColor(R.styleable.UploadingView_upload_bg_color,
                getResources().getColor(R.color.upload_bg_color_default));

        final int bgRingColorFirst = typedArray.getColor(R.styleable.UploadingView_upload_bg_ring_color_first,
                getResources().getColor(R.color.upload_bg_ring_color_first_default));

        final int bgRingColorSecond = typedArray.getColor(R.styleable.UploadingView_upload_bg_ring_color_second,
                getResources().getColor(R.color.upload_bg_ring_color_second_default));

        final float bgThickness = typedArray.getDimension(R.styleable.UploadingView_upload_dg_ring_thickness,
                getResources().getDimension(R.dimen.upload_default_dg_ring_thickness_default));

        Background background = new Background( getContext() );
        background.setCircleColor(bgCircleColor);
        background.setFirstRingColor(bgRingColorFirst);
        background.setSecondRingColor(bgRingColorSecond);
        background.setRingThickness(bgThickness);
        background.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        return background;
    }

    public IUploadingAnimationController getAnimationController() {
        return animationController;
    }
}
