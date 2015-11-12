package com.example.sergii.rotationcircleview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by sergii on 12.11.15.
 */
public class RotationCircleViewBackground extends View{

    private float centerX;
    private float centerY;
    private float radius;
    private float radiusCircle;
    private Paint drawPaintRing;
    private Paint drawPaintCircle;

    public RotationCircleViewBackground(Context context, int aColor,
                                        int aRingColor, int aRingThickness ) {
        super(context);
        initRing(aRingColor, aRingThickness );
        initCircle(aColor);
    }

    private void initCircle(int aColor) {
        drawPaintCircle = new Paint();
        drawPaintCircle.setColor(aColor);
        drawPaintCircle.setAntiAlias(true);
    }

    private void initRing(int aRingColor, int aRingThickness) {
        drawPaintRing = new Paint(Paint.ANTI_ALIAS_FLAG);
        drawPaintRing.setStyle(Paint.Style.STROKE);
        drawPaintRing.setStrokeWidth(aRingThickness);
        drawPaintRing.setColor(aRingColor);
        drawPaintRing.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(centerX, centerY, radius, drawPaintRing);
        canvas.drawCircle(centerX, centerY, radiusCircle, drawPaintCircle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updateCenter();
    }

    private void updateCenter() {
        centerX = getWidth()/2;
        centerY = getHeight()/2;
        float delta = drawPaintRing.getStrokeWidth()/2;
        radius = Math.min(centerX, centerX) - delta;
        radiusCircle = radius - delta + 1;
    }
}


