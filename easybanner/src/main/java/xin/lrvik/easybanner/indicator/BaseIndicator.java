package xin.lrvik.easybanner.indicator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 指示器动画基类
 * <p>
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/31.
 */
public abstract class BaseIndicator extends View {


    public BaseIndicator(Context context) {
        this(context, null);
    }

    public BaseIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public abstract void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

    public abstract void createIndicator(int size);
}
