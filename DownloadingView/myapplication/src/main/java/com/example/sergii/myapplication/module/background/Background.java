package com.example.sergii.myapplication.module.background;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import com.example.sergii.myapplication.module.animation.IViewFiniteAnimationListener;
import com.example.sergii.myapplication.module.animation.IViewProgressAnimationListener;

/**
 * Created by sergii on 14.11.15.
 */
public class Background extends View implements IBackground,
        IViewFiniteAnimationListener,
        IViewProgressAnimationListener {

    private static final float INITIAL_ANGLE = -90;
    private static final float BORDER_COEF = 1.2f;
    private float centerX;
    private float centerY;

    private float radiusCircle;
    private Paint drawPaintFirstRing;
    private Paint drawPaintSecondRing;
    private Paint drawPaintCircle;
    private RectF rectRingBound;
    private float finiteSweepAngle = 0;
    private float progressSweepAngle = 0;
    private float progressActualAngle = INITIAL_ANGLE ;

    public Background(Context context) {
        super(context);

        initCircle();
        initFirstRingPaint();
        initSecondRingPaint();
    }

    private void initCircle() {
        drawPaintCircle = new Paint();
        drawPaintCircle.setColor(Color.RED);
        drawPaintCircle.setAntiAlias(true);
    }

    private void initFirstRingPaint() {
        drawPaintSecondRing = new Paint(Paint.ANTI_ALIAS_FLAG);
        drawPaintSecondRing.setStyle(Paint.Style.STROKE);
        drawPaintSecondRing.setStrokeWidth(4);
        drawPaintSecondRing.setColor(Color.GREEN);
        drawPaintSecondRing.setAntiAlias(true);
        drawPaintSecondRing.setStrokeCap(Paint.Cap.BUTT);
    }

    private void initSecondRingPaint() {
        drawPaintFirstRing = new Paint(Paint.ANTI_ALIAS_FLAG);
        drawPaintFirstRing.setStyle(Paint.Style.STROKE);
        drawPaintFirstRing.setStrokeWidth(2);
        drawPaintFirstRing.setColor(Color.GREEN);
        drawPaintFirstRing.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(centerX, centerY, radiusCircle, drawPaintCircle);
        canvas.drawArc(rectRingBound, INITIAL_ANGLE, finiteSweepAngle, false, drawPaintFirstRing);
        // TODO: 15.11.15 implement animation
        canvas.drawArc(rectRingBound, progressActualAngle, progressSweepAngle, false, drawPaintSecondRing);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updateCenter();
        updatePaints();
    }

    private void updateCenter() {
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
    }

    private void updatePaints() {
        float delta = drawPaintFirstRing.getStrokeWidth() * BORDER_COEF;
        radiusCircle = Math.min(centerX, centerX);
        float sizeSecondRing = Math.min(getWidth(), getHeight());
        rectRingBound = new RectF(delta, delta, sizeSecondRing - delta, sizeSecondRing - delta);
    }

    @Override
    public int getCircleColor() {
        return drawPaintCircle.getColor();
    }

    @Override
    public void setCircleColor(int aColor) {
        drawPaintCircle.setColor(aColor);
    }

    @Override
    public int getFirstRingColor() {
        return drawPaintFirstRing.getColor();
    }

    @Override
    public void setFirstRingColor(int aColor) {
        drawPaintFirstRing.setColor(aColor);
    }

    @Override
    public int getSecondRingColor() {
        return drawPaintSecondRing.getColor();
    }

    @Override
    public void setSecondRingColor(int bgRingColorSecond) {
        drawPaintSecondRing.setColor(bgRingColorSecond);
    }

    @Override
    public float getRingThickness() {
        return drawPaintFirstRing.getStrokeWidth();
    }

    @Override
    public void setRingThickness(float aThickness) {
        drawPaintFirstRing.setStrokeWidth(aThickness);
        drawPaintSecondRing.setStrokeWidth(aThickness);
        updatePaints();
    }

    @Override
    public void setActualAngleFiniteAnimation(float actualAngle) {
        finiteSweepAngle = actualAngle;
    }

    @Override
    public void setActualAngleProgressAnimation(float actualAngle) {
        this.progressActualAngle = actualAngle;
    }

    @Override
    public void setSweepAngleProgressValue(float sweepAngle) {
        this.progressSweepAngle = sweepAngle;
    }

    @Override
    public float getProgressCurrentAngle() {
        return INITIAL_ANGLE;
    }
}


