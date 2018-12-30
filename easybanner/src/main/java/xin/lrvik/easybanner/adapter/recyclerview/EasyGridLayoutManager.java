package xin.lrvik.easybanner.adapter.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/30.
 */
public class EasyGridLayoutManager extends GridLayoutManager {
    private boolean isScrollEnabled = true;

    public EasyGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public EasyGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public EasyGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}
