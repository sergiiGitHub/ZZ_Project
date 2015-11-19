package com.example.sergii.myapplication.module;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.sergii.myapplication.R;
import com.example.sergii.myapplication.module.animation.AnimationController;
import com.example.sergii.myapplication.module.animation.IDownloadController;
import com.example.sergii.myapplication.module.background.Background;
import com.example.sergii.myapplication.module.foregroud.Foreground;

/**
 * Created by sergii on 14.11.15.
 */
public class DownloadingView extends RelativeLayout {
    private static final String TAG = DownloadingView.class.getSimpleName();

    private AnimationController animationController;
    private Background background;
    private Foreground foregroundView;

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
        animationController.setViewChangeColor(background);

        foregroundView = createForegroundView(typedArray);
        animationController.setForegroundViewFlipIn(foregroundView);
        animationController.setForegroundViewMoveDown(foregroundView);
        addView(foregroundView);
        foregroundView.setVisibility(INVISIBLE);

        typedArray.recycle();
    }

    private Foreground createForegroundView(TypedArray typedArray) {

        Drawable drawable = typedArray.getDrawable(R.styleable.DownloadingView_dw_icon_src);
        if ( drawable == null ){
            drawable = getResources().getDrawable(R.drawable.dw_icon_src_default);
        }
        Bitmap icon = ((BitmapDrawable) drawable).getBitmap();

        final int size_x = (int) typedArray.getDimension(R.styleable.DownloadingView_dw_icon_size_x,
                getResources().getDimension(R.dimen.dw_icon_size_x_default));
        final int size_y = (int) typedArray.getDimension(R.styleable.DownloadingView_dw_icon_size_y,
                getResources().getDimension(R.dimen.dw_icon_size_y_default));

        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(size_x, size_y);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        final Foreground view = new Foreground(getContext());
        view.setBitmap(icon);
        view.setLayoutParams(params);
        view.setLineColor( background.getSecondRingColor() );
        return view;
    }

    private Background createBackground( TypedArray typedArray ) {

        final int bgCircleColor = typedArray.getColor(R.styleable.DownloadingView_dw_bg_color,
                getResources().getColor(R.color.dw_bg_color_default));

        final int bgCircleColorFinal = typedArray.getColor(R.styleable.DownloadingView_dw_bg_color_final,
                getResources().getColor(R.color.dw_bg_color_final_default));

        final int bgRingColorFirst = typedArray.getColor(R.styleable.DownloadingView_dw_bg_ring_color_first,
                getResources().getColor(R.color.dw_bg_ring_color_first_default));

        final int bgRingColorSecond = typedArray.getColor(R.styleable.DownloadingView_dw_bg_ring_color_second,
                getResources().getColor(R.color.dw_bg_ring_color_second_default));

        final float bgThickness = typedArray.getDimension(R.styleable.DownloadingView_dw_dg_ring_thickness,
                getResources().getDimension(R.dimen.dw_default_dg_ring_thickness_default));

        Background background = new Background( getContext() );
        background.setCircleColor(bgCircleColor);
        background.setCircleFinalColor(bgCircleColorFinal);
        background.setFirstRingColor(bgRingColorFirst);
        background.setSecondRingColor(bgRingColorSecond);
        background.setRingThickness(bgThickness);
        background.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        return background;
    }

    public IDownloadController getAnimationController() {
        return animationController;
    }
}
