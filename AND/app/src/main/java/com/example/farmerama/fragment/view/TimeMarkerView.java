package com.example.farmerama.fragment.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.farmerama.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.utils.MPPointF;

public class TimeMarkerView extends MarkerView {
    private TextView date;

    public TimeMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        date = findViewById(R.id.markerDate_text);
    }

    @Override
    public void setOffset(MPPointF offset) {
        super.setOffset(new MPPointF(offset.x / 2, -offset.y));
    }

    public void refreshContent(String date) {
        this.date.setText(date);
    }

    @Override
    public void draw(Canvas canvas, float posX, float posY) {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int w = getWidth();
        if ((width - posX - w) < w) {
            posX -= w;
        }
        canvas.translate(posX, posY);
        draw(canvas);
        canvas.translate(-posX, -posY);
    }
}
