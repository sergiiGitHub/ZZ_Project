package com.example.sergii.rotationcircleview.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import com.example.sergii.rotationcircleview.R;

/**
 * Created by sergii on 11.11.15.
 */
public class RotationCircleView extends RelativeLayout {

    private static final String TAG = RotationCircleView.class.getSimpleName();
    private static final Object BG_COLOR = 0;

    private AnimationController animationController;
    private Bitmap bitmap;
    private RotationCircleViewBackground background;

    public RotationCircleView(Context context) {
        super(context);
        init(null, 0);
    }

    public RotationCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init( attrs, 0);
    }

    public RotationCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init( attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        setAnimationController(new AnimationController());
        initAttributes(attrs, defStyleAttr);
        addView(background);
    }

    private void createBackground( int aColor, int aRingColor, int aRingThickness ) {
        background = new RotationCircleViewBackground( getContext(), aColor, aRingColor, aRingThickness );
        background.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    }

    private void initAttributes(AttributeSet attrs, int defStyle) {
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.RotationCircleView, defStyle, 0);

        Resources resources = getResources();

        getAnimationController().setAnimDuration(typedArray.getInteger(
                R.styleable.RotationCircleView_rcv_animDuration,
                resources.getInteger(R.integer.rcv_default_animDuration)));

        setBitmap(BitmapFactory.decodeResource(
                getContext().getResources(), R.styleable.RotationCircleView_rcv_icon));

        //bg
        int bgColor = typedArray.getColor( R.styleable.RotationCircleView_rcv_bg_color,
                resources.getColor(R.color.rcv_default_bg_color));

        int bgRingColor = typedArray.getColor(R.styleable.RotationCircleView_rcv_bg_ring_color,
                resources.getColor(R.color.rcv_default_bg_ring_color));

        int bgThickness = (int) typedArray.getDimension(R.styleable.RotationCircleView_rcv_bg_ring_thickness,
                resources.getDimension(R.dimen.rcv_default_bg_ring_thickness));

        createBackground( bgColor, bgRingColor, bgThickness );

        // TODO: 13.11.15 icon

        // TODO: 13.11.15 text

        typedArray.recycle();
    }

    public AnimationController getAnimationController() {
        return animationController;
    }

    public void setAnimationController(AnimationController animationController) {
        this.animationController = animationController;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}
