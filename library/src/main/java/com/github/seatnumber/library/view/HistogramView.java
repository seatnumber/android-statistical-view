package com.github.seatnumber.library.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.github.seatnumber.library.R;
import com.github.seatnumber.library.util.SimpleUtils;

/**
 * Created by seatNumber on 16/8/2.
 */

public class HistogramView extends View {

    private int colors[] = new int[]{R.color.pumpkinOrange,R.color.mangoTwo,R.color.lightGold,R.color.yellowTan,R.color.palePeachTwo};
    private float counts[] = new float[]{100,80,60,25,25};
    private float percents[] = new float[5];
    private Paint paint;
    private float columnWidth;
    private float histogramHeight;

    public HistogramView(Context context) {
        super(context);
        init();
    }

    public HistogramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HistogramView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        columnWidth = SimpleUtils.dip2px(getContext(),15);
        setCounts(counts);
        histogramHeight = 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float gapWidth = (getWidth()-5* columnWidth)/4;
        paint.setStrokeWidth(columnWidth);
        for (int i = 0;i<counts.length;i++){
            float startX = (columnWidth+gapWidth)*i+columnWidth/2;
            float startY = getHeight();
            float endX = startX;
            float endY = getHeight() - histogramHeight*getHeight()*percents[i];
            paint.setColor(getContext().getResources().getColor(R.color.whiteTwo));
            canvas.drawLine(startX,startY,endX,0,paint);
            paint.setColor(getContext().getResources().getColor(colors[i]));
            canvas.drawLine(startX,startY,endX,endY,paint);
        }
    }

    public void setCounts(float[] counts) {
        this.counts = counts;
        float maxCount = counts[0];
        for(int i = 1;i<counts.length;i++){
            if(counts[i]>maxCount){
                maxCount = counts[i];
            }
        }
        for(int i = 0;i<counts.length;i++){
            percents[i] = counts[i]/maxCount;
        }
        invalidate();
    }

    public void startAnim(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this,"histogramHeight",0,1);
        objectAnimator.setDuration(600);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.start();
    }

    public void setHistogramHeight(float histogramHeight) {
        this.histogramHeight = histogramHeight;
        invalidate();
    }
}
