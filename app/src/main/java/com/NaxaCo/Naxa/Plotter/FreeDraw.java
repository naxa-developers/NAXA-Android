package com.NaxaCo.Naxa.Plotter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


/**
 * Created by cri_r on 1/30/18.
 */

public class FreeDraw extends View implements View.OnTouchListener {
    private final int paintColor = Color.RED;
    private Paint drawPaint;
    private Paint paint;
    float pointX;
    float pointY;
    int i = 0;
    Point point = new Point();
    Canvas canvas;
    SetCordinates setCordinates = new SetCordinates();
    Path path = new Path();

    public FreeDraw(Context context, AttributeSet attributeSet) {
        super(context);
        setupPaint();
        canvas=new Canvas();
        point = new Point();
        path.moveTo(350, 200);

    }

    public boolean onTouchEvent(MotionEvent event) {
        pointX = event.getX();
        pointY = event.getY();
        if (setCordinates.getLastX(pointX) && setCordinates.getLastY(pointY)) {
         //   path.setLastPoint(350, 200);
          //  path.close();
          //  invalidate();
            canvas.drawCircle(350, 200, 15, paint);
             canvas.drawLine(pointX,pointY,350,200,drawPaint);
            Toast.makeText(getContext(), "Polygon Drawn Sucessfully", Toast.LENGTH_SHORT).show();
            i = 1;

        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);
                canvas.drawCircle(pointX, pointY, 13, drawPaint);
                setCordinates.setX(event.getX());
                setCordinates.setY(event.getY());
                postInvalidate();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            default:
                return false;
        }
        return true;
    }

    private void setupPaint() {
        drawPaint = new Paint();
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setTextSize(40);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText("Start Here", 270, 150, drawPaint);
        canvas.drawCircle(350, 200, 15, paint);
        canvas.drawPath(path, drawPaint);
        if(i==1){
           // Toast.makeText(getContext(),"Hello",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
