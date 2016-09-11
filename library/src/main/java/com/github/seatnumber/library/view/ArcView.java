package com.github.seatnumber.library.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.github.seatnumber.library.R;
import com.github.seatnumber.library.util.SimpleUtils;

/**
 * Created by seatNumber on 16/8/1.
 */

public class ArcView extends View {

    private float[] counts = new float[]{1,1,1,1};
    private float[] percents = new float[]{25,25,25,25};
    private int[] colorIds = new int[]{R.color.palePeach, R.color.apricot,R.color.mango,R.color.orangeTwo};

    Paint paint;
    private float dp36;
    private float dp18;
    private float dp8;
    private float gap;
    private float sumAngle = 180;

    public ArcView(Context context) {
        super(context);
        init();
    }

    public ArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        dp36 = SimpleUtils.dip2px(getContext(),36);
        dp18 = dp36 /2;
        dp8 = SimpleUtils.dip2px(getContext(),8);
        gap = SimpleUtils.dip2px(getContext(),2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radio = getWidth()/2;
        RectF rectF = new RectF(dp18, dp18,getWidth()- dp18,getWidth()- dp18);
        float padding2 = dp36-dp8/2;
        RectF rectF2 = new RectF(padding2,padding2,getWidth()-padding2,getWidth()-padding2);
        float[] startAngles = new float[4];
        float[] sweepAngles = new float[4];
        for(int i = 0;i<percents.length;i++) {
            startAngles[i] = 180;
            for(int j =0;j<i;j++) {
                startAngles[i] += percents[j]/100*sumAngle;
            }
            sweepAngles[i] = percents[i] / 100 * sumAngle;
            paint.setColor(getContext().getResources().getColor(colorIds[i]));
            paint.setStrokeWidth(dp36);
            canvas.drawArc(rectF, startAngles[i], sweepAngles[i], false, paint);
        }
        paint.setColor(Color.argb(44,251,76,7));
        paint.setStrokeWidth(dp8);
        canvas.drawArc(rectF2,180,sumAngle,false,paint);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(gap);
        float centerX = radio;
        float centerY = radio;
        for(int i = 1;i<percents.length;i++) {
            float angle = (float) (startAngles[i] * Math.PI / 180);
            float startX = centerX + (float) (radio * Math.cos(angle));
            float startY = centerY + (float) (radio * Math.sin(angle));
            float endX = centerX + (float) ((radio - dp36 ) * Math.cos(angle));
            float endY = centerY + (float) ((radio - dp36 ) * Math.sin(angle));
            canvas.drawLine(startX,startY,endX,endY,paint);
        }
    }

    public void startAnim(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this,"sumAngle",0,180);
        objectAnimator.setDuration(600);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.start();
    }

    public void setSumAngle(float sumAngle) {
        this.sumAngle = sumAngle;
        invalidate();
    }

    public void setCounts(float[] counts) {
        this.counts = counts;
        float sum = 0;
        for(int i = 0 ;i<counts.length;i++){
            sum+=counts[i];
        }

        percents[counts.length-1] = 100;
        for(int i = 0;i<counts.length-1;i++){
            percents[i] = counts[i]/sum*100;
            percents[counts.length-1] -= percents[i];
        }
        invalidate();
    }
}
