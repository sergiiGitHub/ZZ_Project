package com.example.sergii.myapplication.module;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.sergii.myapplication.R;
import com.example.sergii.myapplication.module.animation.AnimationController;
import com.example.sergii.myapplication.module.animation.IDownloadController;

/**
 * Created by sergii on 14.11.15.
 */
public class DownloadingView extends RelativeLayout {
    private static final String TAG = DownloadingView.class.getSimpleName();

    private ImageView iconImageView;
    private AnimationController animationController;

    public DownloadingView(Context context) {
        super(context);
    }

    public DownloadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(attrs, 0);
    }

    public DownloadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(attrs, defStyleAttr);
    }

    private void initAttributes(AttributeSet attrs, int defStyle) {
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.DownloadingView, defStyle, 0);

        if ( attrs == null ){
            Log.e(TAG, "initAttributes() :: attrs == null");
        }

        animationController = new AnimationController(getContext());

        //bg
//        background = createBackground(typedArray);
//        addView(background);

        //image view
        iconImageView = createIconView(typedArray);
        iconImageView.setVisibility(INVISIBLE);
        addView(iconImageView);
        animationController.setForegroundView(iconImageView);

        typedArray.recycle();
    }

    private ImageView createIconView(TypedArray typedArray) {

        final ImageView imageView = new ImageView(getContext());

        Drawable drawable = typedArray.getDrawable(R.styleable.DownloadingView_dw_icon_src);
        if ( drawable == null ){
            drawable = getResources().getDrawable(R.drawable.dw_icon_src_default);
        }

        final int size = (int) typedArray.getDimension(R.styleable.DownloadingView_dw_icon_size,
                getResources().getDimension(R.dimen.dw_icon_size_default));
        imageView.setImageDrawable(drawable);

        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(size, size);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(params);
        return imageView;
    }

    public IDownloadController getAnimationController() {
        return animationController;
    }
}
