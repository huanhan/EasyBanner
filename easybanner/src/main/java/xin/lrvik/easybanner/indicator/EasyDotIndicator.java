package xin.lrvik.easybanner.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import xin.lrvik.easybanner.R;
import xin.lrvik.easybanner.indicator.shape.BaseEasyShape;
import xin.lrvik.easybanner.indicator.shape.CircleShape;
import xin.lrvik.easybanner.indicator.shape.RectShape;


/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/31.
 */
public class EasyDotIndicator extends View implements BaseIndicator {

    private Paint mPaint;
    private Paint mAniPaint;
    private Path indicatorPath;
    private Path selIndicatorpath;

    private int size = 0;
    private int indicatorHeight = 10;
    private int indicatorWidth = 40;
    private int indicatorMargin = 10;
    private int selIndicatorHeight = 10;
    private int selIndicatorWidth = 40;
    private int defColor = Color.parseColor("#DDDDDD");
    private int selColor = Color.parseColor("#7785D0F7");

    private boolean defIsFull;
    private boolean selIsFull;
    private int strokeWidth;

    private int shape;

    private int def_radius;
    private int sel_radius;

    private int cPosition;
    private float cPositionOffset;

    BaseEasyShape rectShape;

    public EasyDotIndicator(Context context) {
        this(context, null);
    }

    public EasyDotIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasyDotIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        indicatorPath = new Path();
        selIndicatorpath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAniPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        handleTypedArray(context, attrs);
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.dot_indicator);
        indicatorHeight = typedArray.getDimensionPixelSize(R.styleable.dot_indicator_indicator_height, indicatorHeight);
        indicatorWidth = typedArray.getDimensionPixelSize(R.styleable.dot_indicator_indicator_width, indicatorWidth);
        indicatorMargin = typedArray.getDimensionPixelSize(R.styleable.dot_indicator_indicator_margin, indicatorMargin);
        selIndicatorHeight = typedArray.getDimensionPixelSize(R.styleable.dot_indicator_sel_indicator_height, selIndicatorHeight);
        selIndicatorWidth = typedArray.getDimensionPixelSize(R.styleable.dot_indicator_sel_indicator_width, selIndicatorWidth);
        defColor = typedArray.getColor(R.styleable.dot_indicator_def_color, defColor);
        selColor = typedArray.getColor(R.styleable.dot_indicator_sel_color, selColor);
        defIsFull = typedArray.getBoolean(R.styleable.dot_indicator_def_is_full, true);
        selIsFull = typedArray.getBoolean(R.styleable.dot_indicator_sel_is_full, true);
        strokeWidth = typedArray.getDimensionPixelSize(R.styleable.dot_indicator_stroke_width, 2);
        setShape(typedArray.getInt(R.styleable.dot_indicator_shape, 0));
        def_radius = typedArray.getDimensionPixelSize(R.styleable.dot_indicator_def_radius, 0);
        sel_radius = typedArray.getDimensionPixelSize(R.styleable.dot_indicator_sel_radius, 0);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制所有指示器
        drawDefIndicators(canvas);
        //绘制指示器变换动画动画
        drawSelIndicators(canvas);
    }

    private void drawSelIndicators(Canvas canvas) {
        mAniPaint.setColor(selColor);
        mAniPaint.setStyle(Paint.Style.FILL);
        mAniPaint.setStrokeWidth(strokeWidth);
        if (!selIsFull) {
            mAniPaint.setStyle(Paint.Style.STROKE);
        }
        selIndicatorpath.reset();

        rectShape.setSelIndicatorsShape(selIndicatorpath, this);

        canvas.drawPath(selIndicatorpath, mAniPaint);
    }

    private void drawDefIndicators(Canvas canvas) {
        mPaint.setColor(defColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(strokeWidth);
        if (!defIsFull) {
            mPaint.setStyle(Paint.Style.STROKE);
        }
        indicatorPath.reset();

        rectShape.setIndicatorsShape(indicatorPath, this);

        canvas.drawPath(indicatorPath, mPaint);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        cPosition = position;
        cPositionOffset = positionOffset;
        invalidate();
    }

    @Override
    public void createIndicator(int size) {
        this.size = size;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int selWidth = (selIndicatorWidth + indicatorMargin) * this.size;
        int defWidth = (indicatorWidth + indicatorMargin) * this.size;
        int width = selWidth > defWidth ? selWidth : defWidth;

        int height = selIndicatorHeight > indicatorHeight ? selIndicatorHeight : indicatorHeight;

        int measuredWidth = resolveSize(width, widthMeasureSpec);
        int measuredHeight = resolveSize(height, heightMeasureSpec);

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    public int getIndicatorHeight() {
        return indicatorHeight;
    }

    public void setIndicatorHeight(int indicatorHeight) {
        this.indicatorHeight = indicatorHeight;
    }

    public int getIndicatorWidth() {
        return indicatorWidth;
    }

    public void setIndicatorWidth(int indicatorWidth) {
        this.indicatorWidth = indicatorWidth;
    }

    public int getIndicatorMargin() {
        return indicatorMargin;
    }

    public void setIndicatorMargin(int indicatorMargin) {
        this.indicatorMargin = indicatorMargin;
    }

    public int getSelIndicatorHeight() {
        return selIndicatorHeight;
    }

    public void setSelIndicatorHeight(int selIndicatorHeight) {
        this.selIndicatorHeight = selIndicatorHeight;
    }

    public int getSelIndicatorWidth() {
        return selIndicatorWidth;
    }

    public void setSelIndicatorWidth(int selIndicatorWidth) {
        this.selIndicatorWidth = selIndicatorWidth;
    }

    public int getDefColor() {
        return defColor;
    }

    public void setDefColor(int defColor) {
        this.defColor = defColor;
    }

    public int getSelColor() {
        return selColor;
    }

    public void setSelColor(int selColor) {
        this.selColor = selColor;
    }

    public boolean isDefIsFull() {
        return defIsFull;
    }

    public void setDefIsFull(boolean defIsFull) {
        this.defIsFull = defIsFull;
    }

    public boolean isSelIsFull() {
        return selIsFull;
    }

    public void setSelIsFull(boolean selIsFull) {
        this.selIsFull = selIsFull;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getDef_radius() {
        return def_radius;
    }

    public void setDef_radius(int def_radius) {
        this.def_radius = def_radius;
    }

    public int getSel_radius() {
        return sel_radius;
    }

    public void setSel_radius(int sel_radius) {
        this.sel_radius = sel_radius;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
        switch (shape) {
            case 0:
                rectShape = new RectShape();
                break;
            case 1:
                rectShape = new CircleShape();
                break;
        }
    }

    public int getSize() {
        return size;
    }

    public CenterPoint getCenterPoint(int position) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int width = measuredWidth / EasyDotIndicator.this.size;
        int centerX = position * width + (width - indicatorMargin) / 2;
        int centerY = measuredHeight / 2;
        return new CenterPoint(position, this.cPositionOffset, centerX, centerY);
    }

    public CenterPoint getCenterPoint() {
        return getCenterPoint(this.cPosition);
    }

    public class CenterPoint {
        private int position;
        private float positionOffset;
        private int centerX;
        private int centerY;

        public CenterPoint(int position, float positionOffset, int centerX, int centerY) {
            this.position = position;
            this.positionOffset = positionOffset;
            this.centerX = centerX;
            this.centerY = centerY;
        }

        public int getCenterX() {
            return centerX;
        }

        public int getCenterY() {
            return centerY;
        }

        public int getPosition() {
            return position;
        }

        public float getPositionOffset() {
            return positionOffset;
        }

    }
}
