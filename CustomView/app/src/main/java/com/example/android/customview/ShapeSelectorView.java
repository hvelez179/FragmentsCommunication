package com.example.android.customview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ShapeSelectorView extends View {

    private int shapeColor;
    private boolean dispalyShapeName;

    private int shapeWidth = 300;
    private int shapeHeight = 300;

    private int textXOffset = 0;
    private int textYOffset = 50;

    private Paint paintShape;

    private String[] shapeValues = {"square", "circle", "triangle"};
    private int currentShapeIndex = 0;

    public ShapeSelectorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupAttributes(attrs);
        setupPaint();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String shapeSelected = shapeValues[currentShapeIndex];
        switch (shapeSelected) {
            case "square":
                canvas.drawRect(0, 0, shapeWidth, shapeHeight, paintShape);
                textXOffset = 0;
                break;
            case "circle":
                canvas.drawCircle(shapeWidth/2, shapeHeight/2, shapeWidth/2, paintShape);
                textXOffset = 12;
                break;
            case "triangle":
                canvas.drawPath(getTrianglePath(), paintShape);
                textXOffset = 0;
                break;
        }
        if (dispalyShapeName) {
            canvas.drawText(shapeSelected, textXOffset, shapeHeight + textYOffset, paintShape);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int textPadding = 30;
        int contentWidth = shapeWidth;

        int minWidth = contentWidth + getPaddingLeft() + getPaddingRight();
        int width = resolveSizeAndState(minWidth, widthMeasureSpec, 0);

        int minHeight = shapeHeight + getPaddingBottom() + getPaddingTop();
        if (dispalyShapeName) {
            minHeight += textYOffset + textPadding;
        }
        int height = resolveSizeAndState(minHeight, heightMeasureSpec, 0);

        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            currentShapeIndex++;
            if (currentShapeIndex > shapeValues.length - 1) {
                currentShapeIndex = 0;
            }
            postInvalidate();
            return true;
        }

        return result;
    }

    public void setupAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ShapeSelectorView, 0, 0);
        try {
            shapeColor = typedArray.getColor(R.styleable.ShapeSelectorView_shapeColor, Color.BLACK);
            dispalyShapeName = typedArray.getBoolean(R.styleable.ShapeSelectorView_displayShapeName, false);
        }finally {
            typedArray.recycle();
        }
    }

    private void setupPaint() {
        paintShape = new Paint();
        paintShape.setStyle(Paint.Style.FILL);
        paintShape.setColor(shapeColor);
        paintShape.setTextSize(paintShape.getTextSize()*5);
    }

    protected Path getTrianglePath() {
        Point p1 = new Point(0, shapeHeight);
        Point p2 = new Point(p1.x + shapeWidth, p1.y);
        Point p3 = new Point(p1.x + (shapeWidth/2),p1.y - shapeHeight);

        Path path = new Path();
        path.moveTo(p1.x,p1.y);
        path.lineTo(p2.x,p2.y);
        path.lineTo(p3.x,p3.y);

        return path;
    }

    public String getSelectedSahpe() {
        return shapeValues[currentShapeIndex];
    }

    public boolean isDisplayingShapeName() {
        return dispalyShapeName;
    }

    public void setDisplayingShapeName(boolean state) {
        this.dispalyShapeName = state;
        invalidate();
        requestLayout();
    }

    public int getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(int color) {
        this.shapeColor = color;
        invalidate();
        requestLayout();
    }
}