package com.example.sergii.myapplication.module.foregroud;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

/**
 * Created by sergii on 16.11.15.
 */
public class Foreground extends View {

    private static final String TAG = Foreground.class.getSimpleName();
    private Paint drawPaintBottom;
    private Rect rectBottom;

    public Foreground(Context context) {
        super(context);

        initBottomRect();
    }

    private void initBottomRect() {
        rectBottom = new Rect();
        initBottomRectPaint();
    }

    private void initBottomRectPaint() {
        drawPaintBottom = new Paint();
        drawPaintBottom.setColor(Color.argb(55, 0, 0, 150));
        drawPaintBottom.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rectBottom, drawPaintBottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updateRect(w, h);
    }

    private void updateRect(int w, int h) {
        Log.d(TAG, "updateRect() called with: " + "w = [" + w + "], h = [" + h + "]");
        Log.d(TAG,  "lb = " + getLeftBottom(w)
                + "; tb " + getTopBottom(h)
                + "; rb = " + getRightBottom(w)
                + "; bb = " + getBottomBottom(h) );
        rectBottom.set(getLeftBottom(w), getTopBottom(h), getRightBottom(w), getBottomBottom(h));
    }

    private int getRightBottom(int w) {
        return (int) (w * .70f);
    }

    private int getLeftBottom(int w) {
        return (int) (w * 0.30f);
    }

    private int getBottomBottom(int h) {
        return (int) (h * 0.74f);
    }

    private int getTopBottom(int h) {
        return (int) (h * 0.69f);
    }


}
