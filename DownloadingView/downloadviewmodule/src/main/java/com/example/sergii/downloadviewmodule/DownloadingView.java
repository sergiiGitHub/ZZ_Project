package com.example.sergii.downloadviewmodule;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sergii on 14.11.15.
 */
public class DownloadingView extends View {
    public DownloadingView(Context context) {
        super(context);
    }

    public DownloadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DownloadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(new Rect(0,0,300, 300)
        , new Paint(Color.RED));
    }
}
