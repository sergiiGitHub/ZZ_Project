package com.example.sergii.uploadinganimation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.sergii.uploadinganimation.animation.AnimationController;
import com.example.sergii.uploadinganimation.animation.IUploadingAnimationController;
import com.example.sergii.uploadinganimation.view.background.Background;
import com.example.sergii.uploadinganimation.view.background.IBackground;

/**
 * Created by sergii on 21.11.15.
 */
public class UploadingView extends RelativeLayout{
    private static final String TAG = UploadingView.class.getSimpleName();
    private static final float IMAGE_SIZE_COEF = 0.6f;
    private AnimationController animationController;
    private Background background;
    private ImageView uploadImageView;

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

        background = createBackground(typedArray);
        addView(background);
        animationController.setFirstRingView(background);
        animationController.setSecondRingView(background);

        uploadImageView = createIconView( typedArray );
        uploadImageView.setVisibility(INVISIBLE);
        addView(uploadImageView);
        animationController.setSpiralView( uploadImageView );

        typedArray.recycle();
    }

    private ImageView createIconView(TypedArray typedArray) {

        Drawable drawable = typedArray.getDrawable(R.styleable.UploadingView_upload_icon_src);
        if ( drawable == null ){
            drawable = getResources().getDrawable(R.drawable.upload_icon_src_default);
        }

        final ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawable);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(params);
        return imageView;
    }

    private Background createBackground(TypedArray typedArray) {
        final int bgCircleColor = typedArray.getColor(R.styleable.UploadingView_upload_bg_color,
                getResources().getColor(R.color.upload_bg_color_default));

        final int bgRingColorFirst = typedArray.getColor(R.styleable.UploadingView_upload_bg_ring_color_first,
                getResources().getColor(R.color.upload_bg_ring_color_first_default));

        final int bgRingColorSecond = typedArray.getColor(R.styleable.UploadingView_upload_bg_ring_color_second,
                getResources().getColor(R.color.upload_bg_ring_color_second_default));

        final float bgThickness = typedArray.getDimension(R.styleable.UploadingView_upload_ring_thickness,
                getResources().getDimension(R.dimen.upload_ring_thickness_default));

        Background background = new Background( getContext() );
        background.setCircleColor(bgCircleColor);
        background.setFirstRingColor(bgRingColorFirst);
        background.setSecondRingColor(bgRingColorSecond);
        background.setRingThickness(bgThickness);
        background.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        return background;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updateUploadImageViewSize();
    }

    private void updateUploadImageViewSize() {
        ViewGroup.LayoutParams value = uploadImageView.getLayoutParams();
        value.width = (int) (getWidth()*IMAGE_SIZE_COEF);
        value.height = (int) (getHeight()*IMAGE_SIZE_COEF);
    }

    public IBackground getUploadBackground() {
        return background;
    }

    public IUploadingAnimationController getAnimationController() {
        return animationController;
    }
}
