package xin.lrvik.easybanner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.List;

import xin.lrvik.easybanner.adapter.viewpager.BaseEasyViewPagerAdapter;


/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/27.
 */
public class EasyViewPager extends ViewPager {

    private Context mContext;

    private int delayTime = 3000;
    private boolean isAutoPlay = false;
    private boolean isLoop = false;

    private BaseEasyViewPagerAdapter adapter;

    private WeakHandler handler = new WeakHandler();

    public EasyViewPager(Context context) {
        this(context, null);
    }

    public EasyViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        this.mContext = context;
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        handleTypedArray(context, attrs);
        //初始化view

        addOnPageChangeListener(new SimpleOnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int state) {
                if (adapter.isLoop()) {
                    switch (state) {
                        case ViewPager.SCROLL_STATE_IDLE://No operation
                            if (getCurrentItem() == 0) {
                                setCurrentItem(getAdapter().getCount() - 2, false);
                            } else if (getCurrentItem() == getAdapter().getCount() - 1) {
                                setCurrentItem(1, false);
                            }
                            break;
                        case ViewPager.SCROLL_STATE_DRAGGING://start Sliding
                            if (getCurrentItem() == getAdapter().getCount() - 1) {
                                setCurrentItem(1, false);
                            } else if (getCurrentItem() == 0) {
                                setCurrentItem(getAdapter().getCount() - 2, false);
                            }
                            break;
                        case ViewPager.SCROLL_STATE_SETTLING://end Sliding
                            break;
                    }
                }
            }
        });
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        /*TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Banner);
        mIndicatorMargin = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_margin, BannerConfig.PADDING_SIZE);
        mIndicatorSelectedResId = typedArray.getResourceId(R.styleable.Banner_indicator_drawable_selected, R.drawable.gray_radius);
        scaleType = typedArray.getInt(R.styleable.Banner_image_scale_type, scaleType);
        isAutoPlay = typedArray.getBoolean(R.styleable.Banner_is_auto_play, BannerConfig.IS_AUTO_PLAY);
        titleBackground = typedArray.getColor(R.styleable.Banner_title_background, BannerConfig.TITLE_BACKGROUND);
        typedArray.recycle();*///获取xml配置属性

    }


    public void startAutoPlay() {
        handler.removeCallbacks(task);
        handler.postDelayed(task, delayTime);
    }

    public void stopAutoPlay() {
        handler.removeCallbacks(task);
    }

    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            if (getAdapter() != null && getAdapter().getCount() - 2 > 1 && isAutoPlay && isLoop) {
                int currentItem = getCurrentItem() % (getAdapter().getCount() - 1) + 1;
                if (currentItem == 1) {
                    setCurrentItem(currentItem, false);
                    handler.post(task);
                } else {
                    setCurrentItem(currentItem);
                    handler.postDelayed(task, delayTime);
                }
            }
        }
    };

    public int getDelayTime() {
        return delayTime;
    }

    public EasyViewPager setDelayTime(int delayTime) {
        this.delayTime = delayTime;
        return this;
    }

    public boolean isAutoPlay() {
        return isAutoPlay;
    }

    public EasyViewPager setAutoPlay(boolean autoPlay) {
        isAutoPlay = autoPlay;
        return this;
    }

    public EasyViewPager setAdapter(BaseEasyViewPagerAdapter adapter) {
        this.adapter = adapter;
        super.setAdapter(adapter);
        setCurrentItem(1);
        return this;
    }

    public boolean isLoop() {
        return isLoop;
    }

    public EasyViewPager setLoop(boolean loop) {
        isLoop = loop;
        return this;
    }

    public List getData() {
        return adapter.getData();
    }

    public void setData(List data) {
        if (adapter == null) {
            throw new RuntimeException("适配器不能为空，请配置适配器！");
        }
        adapter.setLoop(isLoop);
        adapter.setData(data);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.i(tag, ev.getAction() + "--" + isAutoPlay);
        if (isAutoPlay) {
            int action = ev.getAction();
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL
                    || action == MotionEvent.ACTION_OUTSIDE) {
                startAutoPlay();
            } else if (action == MotionEvent.ACTION_DOWN) {
                stopAutoPlay();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
