package com.example.sergii.rotationcircleview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sergii.rotationcircleview.R;
import com.example.sergii.rotationcircleview.animation.AnimationController;
import com.example.sergii.rotationcircleview.animation.IRotationCircleViewController;
import com.example.sergii.rotationcircleview.view.background.Background;
import com.example.sergii.rotationcircleview.view.background.IBackground;

/**
 * Created by sergii on 11.11.15.
 */
public class RotationCircleView extends RelativeLayout {

    private static final String TAG = RotationCircleView.class.getSimpleName();

    private AnimationController animationController;

    private Background background;
    private ImageView iconImageView;
    private TextView textView;

    public RotationCircleView(Context context) {
        super(context);
        init(null, 0);
    }

    public RotationCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public RotationCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        initAttributes(attrs, defStyleAttr);
    }

    private void initAttributes(AttributeSet attrs, int defStyle) {
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.RotationCircleView, defStyle, 0);

        if ( attrs == null ){
            Log.e(TAG, "initAttributes() :: attrs == null" );
        }

        animationController = createAnimationController(typedArray);
        //bg
        background = createBackground(typedArray);
        addView(background);

        //image view
        iconImageView = createIconView(typedArray);
        addView(iconImageView);
        animationController.setPrimaryView(iconImageView);

        textView = createTextView(typedArray);
        textView.setVisibility(INVISIBLE);
        addView(textView);
        animationController.setSecondaryView(textView);

        typedArray.recycle();
    }

    private AnimationController createAnimationController(TypedArray typedArray) {
        final AnimationController controller = new AnimationController(getContext());
        controller.setAnimDuration(typedArray.getInteger(
                R.styleable.RotationCircleView_rcv_anim_duration,
                getResources().getInteger(R.integer.rcv_default_anim_duration)));

        controller.setDelayForBackwardAnimation(typedArray.getInteger(
                R.styleable.RotationCircleView_rcv_anim_back_animation_delay,
                getResources().getInteger(R.integer.rcv_default_anim_back_animation_delay)));

        return controller;
    }

    private Background createBackground( TypedArray typedArray ) {

        final int bgCircleColor = typedArray.getColor(R.styleable.RotationCircleView_rcv_bg_color,
                getResources().getColor(R.color.rcv_default_bg_color));

        final int bgRingColor = typedArray.getColor(R.styleable.RotationCircleView_rcv_bg_ring_color,
                getResources().getColor(R.color.rcv_default_bg_ring_color));

        final float bgThickness = typedArray.getDimension(R.styleable.RotationCircleView_rcv_bg_ring_thickness,
                getResources().getDimension(R.dimen.rcv_default_bg_ring_thickness));

        Background background = new Background( getContext() );
        background.setCircleColor(bgCircleColor);
        background.setRingColor(bgRingColor);
        background.setRingThickness(bgThickness);
        background.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        return background;
    }

    private ImageView createIconView(TypedArray typedArray) {

        final ImageView imageView = new ImageView(getContext());

        Drawable drawable = typedArray.getDrawable(R.styleable.RotationCircleView_rcv_icon_src);
        if ( drawable == null ){
            drawable = getResources().getDrawable(R.drawable.rcv_default_icon_src);
        }

        final int size = (int) typedArray.getDimension(R.styleable.RotationCircleView_rcv_fg_size,
                getResources().getDimension(R.dimen.rcv_default_fg_size));
        imageView.setImageDrawable(drawable);

        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(size, size);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(params);
        return imageView;
    }

    private TextView createTextView(TypedArray typedArray) {
        final int textColor = typedArray.getColor(R.styleable.RotationCircleView_rcv_text_color,
                getResources().getColor(R.color.rcv_default_text_color));

        final float textSize = typedArray.getDimension(R.styleable.RotationCircleView_rcv_text_size,
                getResources().getDimension(R.dimen.rcv_default_text_size));

        String str = typedArray.getString(R.styleable.RotationCircleView_rcv_text );
        if ( str == null ){
            str = getResources().getString(R.string.rcv_default_text);
        }

        final int size = (int) typedArray.getDimension(R.styleable.RotationCircleView_rcv_fg_size,
                getResources().getDimension(R.dimen.rcv_default_fg_size));

        TextView textView = new TextView( getContext() );
        textView.setText(str);
        Typeface face = Typeface.createFromAsset(getContext().getAssets(),"Roboto-Medium.ttf");
        textView.setTypeface(face);
//        textView.setBackgroundColor(Color.argb(120, 0, 0, 150));
        textView.setTextColor(textColor);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        textView.setTextSize(textSize);
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(size, size);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        textView.setLayoutParams(params);

        return textView;
    }

    public ImageView getIconView() {
        return iconImageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public IBackground getCustomBackground( ){
        return background;
    }

    public IRotationCircleViewController getAnimationController(){
        return animationController;
    }
}
