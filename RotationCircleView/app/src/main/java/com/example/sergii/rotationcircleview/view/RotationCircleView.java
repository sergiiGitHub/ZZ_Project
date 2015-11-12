package com.example.sergii.rotationcircleview.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.sergii.rotationcircleview.R;

/**
 * Created by sergii on 11.11.15.
 */
public class RotationCircleView extends View {

    private static final String TAG = RotationCircleView.class.getSimpleName();

    private AnimationController animationController;
    private Bitmap bitmap;

    public RotationCircleView(Context context) {
        super(context);
    }

    public RotationCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAnimationController();
        initAttributes(attrs, 0);
    }

    public RotationCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(attrs, 0);
    }

    private void initAnimationController() {
        setAnimationController(new AnimationController());
    }

    private void initAttributes(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.RotationCircleView, defStyle, 0);

        Resources resources = getResources();

        getAnimationController().setAnimDuration(a.getInteger(
                R.styleable.RotationCircleView_rcv_animDuration,
                resources.getInteger(R.integer.rcv_animDuration)));

        setBitmap(BitmapFactory.decodeResource(
                getContext().getResources(), R.styleable.RotationCircleView_rcv_icon));


        //setBackground(a.getDrawable(R.styleable.RotationCircleView_rcv_icon));
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if( getBitmap() == null ){
            Log.d(TAG, "onDraw() :: bitmap != null");
//            canvas.drawBitmap(getBitmap(),
//                    new Rect(0,0,100,100),
//                    new Rect(0,0,100,100),
//                    null
//                    );
        } else {
            Log.d(TAG, "onDraw() :: !!! bitmap == null !!!");
        }
        super.onDraw(canvas);
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
