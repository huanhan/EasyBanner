package xin.lrvik.easybanner.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/31.
 */
public class DotIndicator extends BaseIndicator {

    private Paint mPaint;
    private Paint mAniPaint;
    private Path path;
    private Path path2;
    private int size = 4;
    private int indicatorHeight = 10;
    private int indicatorWidth = 40;
    private int indicatorMargin = 20;
    private int cPosition;
    private float cPositionOffset;

    public DotIndicator(Context context) {
        this(context, null);
    }

    public DotIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        path = new Path();
        path2 = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAniPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#DDDDDD"));
        mAniPaint.setColor(Color.parseColor("#7785D0F7"));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制所有指示器
        drawIndicators(canvas);
        //绘制指示器变换动画动画
        drawIndicatorsAnimation(canvas);
    }

    private void drawIndicatorsAnimation(Canvas canvas) {
        mAniPaint.setStyle(Paint.Style.FILL);
        path2.reset();
        path2.addRect(getRectIndicator(this.cPosition, this.cPositionOffset), Path.Direction.CW);
        canvas.drawPath(path2, mAniPaint);
    }

    private void drawIndicators(Canvas canvas) {
//        canvas.drawColor(Color.parseColor("#AA0000"));
        mPaint.setStyle(Paint.Style.FILL);
        path.reset();
        for (int i = 0; i < this.size; i++) {
            path.addRect(getRectIndicator(i, 0), Path.Direction.CW);
        }
        canvas.drawPath(path, mPaint);
    }

    private RectF getRectIndicator(int position, float positionOffset) {
        int left = position * (indicatorWidth + indicatorMargin)+100;
        if (position!=this.size-1) {
            left += (int) ((indicatorWidth + indicatorMargin) * positionOffset);
        }
        int top = 0;
        int right = left + 40;
        int bottom = indicatorHeight;
        return new RectF(left, top, right, bottom);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("test", "position: " + position + "positionOffset: " + positionOffset + "-positionOffsetPixels--" + positionOffsetPixels);
        cPosition = position;
        cPositionOffset = positionOffset;
        invalidate();
    }

    @Override
    public void createIndicator(int size) {
        this.size = size;
    }
}
