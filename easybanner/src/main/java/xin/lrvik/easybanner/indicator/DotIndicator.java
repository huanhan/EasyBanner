package xin.lrvik.easybanner.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import xin.lrvik.easybanner.R;

/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/31.
 */
public class DotIndicator extends View implements BaseIndicator {

    private Paint mPaint;
    private Paint mAniPaint;
    private Path indicatorPath;
    private Path selIndicatorpath;

    private int size = 0;
    private int indicatorHeight = 10;
    private int indicatorWidth = 40;
    private int indicatorMargin = 50;

    private int selIndicatorHeight = 60;
    private int selIndicatorWidth = 100;

    private int defColor = Color.parseColor("#DDDDDD");
    private int selColor = Color.parseColor("#7785D0F7");
    private boolean defIsFull = true;
    private boolean selIsFull = true;

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
        indicatorPath = new Path();
        selIndicatorpath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAniPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(defColor);
        mAniPaint.setColor(selColor);

        handleTypedArray(context, attrs);
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
       /* TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.dot_indicator);
        mIndicatorWidth = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_width, indicatorSize);
        mIndicatorHeight = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_height, indicatorSize);
        mIndicatorMargin = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_margin, BannerConfig.PADDING_SIZE);
        mIndicatorSelectedResId = typedArray.getResourceId(R.styleable.Banner_indicator_drawable_selected, R.drawable.gray_radius);
        mIndicatorUnselectedResId = typedArray.getResourceId(R.styleable.Banner_indicator_drawable_unselected, R.drawable.white_radius);
        scaleType = typedArray.getInt(R.styleable.Banner_image_scale_type, scaleType);
        delayTime = typedArray.getInt(R.styleable.Banner_delay_time, BannerConfig.TIME);
        scrollTime = typedArray.getInt(R.styleable.Banner_scroll_time, BannerConfig.DURATION);
        isAutoPlay = typedArray.getBoolean(R.styleable.Banner_is_auto_play, BannerConfig.IS_AUTO_PLAY);
        titleBackground = typedArray.getColor(R.styleable.Banner_title_background, BannerConfig.TITLE_BACKGROUND);
        titleHeight = typedArray.getDimensionPixelSize(R.styleable.Banner_title_height, BannerConfig.TITLE_HEIGHT);
        titleTextColor = typedArray.getColor(R.styleable.Banner_title_textcolor, BannerConfig.TITLE_TEXT_COLOR);
        titleTextSize = typedArray.getDimensionPixelSize(R.styleable.Banner_title_textsize, BannerConfig.TITLE_TEXT_SIZE);
        mLayoutResId = typedArray.getResourceId(R.styleable.Banner_banner_layout, mLayoutResId);
        bannerBackgroundImage = typedArray.getResourceId(R.styleable.Banner_banner_default_image, R.drawable.no_banner);
        typedArray.recycle();*/
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
        selIndicatorpath.reset();
        selIndicatorpath.addRect(getSelIndicator(this.cPosition, this.cPositionOffset), Path.Direction.CW);
        canvas.drawPath(selIndicatorpath, mAniPaint);
    }

    private void drawIndicators(Canvas canvas) {
//        canvas.drawColor(Color.parseColor("#AA0000"));
        mPaint.setStyle(Paint.Style.FILL);
        indicatorPath.reset();
        for (int i = 0; i < this.size; i++) {
            indicatorPath.addRect(getDefIndicator(i, 0), Path.Direction.CW);
        }
        canvas.drawPath(indicatorPath, mPaint);
    }

    /**
     * 默认的指示器样式
     *
     * @param position
     * @param positionOffset
     * @return
     */
    private RectF getDefIndicator(int position, float positionOffset) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        int width = measuredWidth / this.size;


        //根据绘制的宽度高度决定绘制的起点
        //中心x
        int centerX = position * width + (width - indicatorMargin) / 2;
        int centerY = measuredHeight / 2;

        Log.d("test", "总高度: " + measuredHeight + "总宽度: " + measuredWidth + "---中心点x" + centerX + "---中心点y" + centerY);

        int left = centerX - indicatorWidth / 2;

//        int left = position * (indicatorWidth + indicatorMargin);

        int top = centerY - indicatorHeight / 2;

        int right = left + indicatorWidth;
        int bottom = top + indicatorHeight;
        return new RectF(left, top, right, bottom);
    }

    /**
     * 选中指示器样式
     *
     * @param position
     * @param positionOffset
     * @return
     */
    private RectF getSelIndicator(int position, float positionOffset) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        int width = (measuredWidth) / this.size;

        //根据绘制的宽度高度决定绘制的起点
        //中心x
        int centerX = position * width + (width - indicatorMargin) / 2;
        int centerY = measuredHeight / 2;

        int left = centerX - selIndicatorWidth / 2;
//        int left = position * (indicatorWidth + indicatorMargin);
        int top = centerY - selIndicatorHeight / 2;

        if (position != this.size - 1) {
            left += (int) ((selIndicatorWidth + indicatorMargin) * positionOffset);
        }

        int right = left + selIndicatorWidth;
        int bottom = top + selIndicatorHeight;
        return new RectF(left, top, right, bottom);
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

        // 再用 setMeasuredDimension(measuredWidth, height) 来保存最终的宽度和高度
        setMeasuredDimension(measuredWidth, measuredHeight);
    }


}
