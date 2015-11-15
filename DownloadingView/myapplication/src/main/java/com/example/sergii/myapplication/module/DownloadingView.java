package com.example.sergii.myapplication.module;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.sergii.myapplication.R;
import com.example.sergii.myapplication.module.animation.AnimationController;
import com.example.sergii.myapplication.module.animation.IDownloadController;
import com.example.sergii.myapplication.module.background.Background;

/**
 * Created by sergii on 14.11.15.
 */
public class DownloadingView extends RelativeLayout {
    private static final String TAG = DownloadingView.class.getSimpleName();

    private ImageView iconImageView;
    private AnimationController animationController;
    private Background background;

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
        background = createBackground(typedArray);
        addView(background);
        animationController.setFirstRingView(background);
        animationController.setSecondRingView(background);

        //image view
        iconImageView = createIconView(typedArray);
        iconImageView.setVisibility(INVISIBLE);
        addView(iconImageView);
        animationController.setForegroundView(iconImageView);

        typedArray.recycle();
    }

    private Background createBackground( TypedArray typedArray ) {

        final int bgCircleColor = typedArray.getColor(R.styleable.DownloadingView_dw_bg_color,
                getResources().getColor(R.color.dw_bg_color_default));

        final int bgRingColorFirst = typedArray.getColor(R.styleable.DownloadingView_dw_bg_ring_color_first,
                getResources().getColor(R.color.dw_bg_ring_color_first_default));

        final int bgRingColorSecond = typedArray.getColor(R.styleable.DownloadingView_dw_bg_ring_color_second,
                getResources().getColor(R.color.dw_bg_ring_color_second_default));

        final float bgThickness = typedArray.getDimension(R.styleable.DownloadingView_dw_dg_ring_thickness,
                getResources().getDimension(R.dimen.dw_default_dg_ring_thickness_default));

        Background background = new Background( getContext() );
        background.setCircleColor(bgCircleColor);
        background.setFirstRingColor(bgRingColorFirst);
        background.setSecondRingColor(bgRingColorSecond);
        background.setRingThickness(bgThickness);
        background.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        return background;
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
