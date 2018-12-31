package xin.lrvik.easybanner;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.List;

import xin.lrvik.easybanner.adapter.viewpager.BaseEasyViewPagerAdapter;
import xin.lrvik.easybanner.indicator.BaseIndicator;


/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/27.
 */
public class EasyViewPager extends ViewPager {

    private Context mContext;

    private int delayTime;
    private boolean isAutoPlay;
    private boolean isLoop;

    private BaseEasyViewPagerAdapter adapter;

    private BaseIndicator indicator;

    private WeakHandler handler = new WeakHandler();
    private OnPageChangeListener onPageChangeListener;

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
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (indicator != null) {
                    if (adapter.isLoop()) {
                        int pos = position;
                        if (position == 0) {
                            pos = getAdapter().getCount() - 2;
                        } else if (position == getAdapter().getCount() - 1) {
                            pos = 1;
                        }

                        indicator.onPageScrolled(pos - 1, positionOffset, positionOffsetPixels);

                        if (onPageChangeListener != null) {
                            onPageChangeListener.onPageScrolled(pos - 1, positionOffset, positionOffsetPixels);
                        }
                    } else {
                        indicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
                        if (onPageChangeListener != null) {
                            onPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (adapter.isLoop()) {
                    switch (state) {
                        case ViewPager.SCROLL_STATE_IDLE://No operation
                            if (getCurrentItem() == 0) {//如果当前页是第一页
                                setCurrentItem(getAdapter().getCount() - 2, false);
                            } else if (getCurrentItem() == getAdapter().getCount() - 1) {//如果当前页是最后一页
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

    public EasyViewPager setBannerAnimation(Class<? extends PageTransformer> transformer) {
        try {
            setPageTransformer(true, transformer.newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Please set the PageTransformer class");
        }
        return this;
    }


    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.easy_view_pager);
        delayTime = typedArray.getInt(R.styleable.easy_view_pager_delay_time, 3000);
        isAutoPlay = typedArray.getBoolean(R.styleable.easy_view_pager_auto_play, false);
        isLoop = typedArray.getBoolean(R.styleable.easy_view_pager_is_loop, false);
        typedArray.recycle();
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
                //Log.d("TEST", "当前页" + getCurrentItem() + " 准备跳转页" + currentItem);
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
        return this;
    }

    public EasyViewPager setOnItemClickListner(BaseEasyViewPagerAdapter.OnItemClickListner onItemClickListner) {
        if (this.adapter == null) {
            throw new RuntimeException("适配器不能为空，请配置适配器！");
        }
        this.adapter.setOnItemClickListner(onItemClickListner);
        return this;
    }

    public interface OnPageChangeListener {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
    }

    public EasyViewPager setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
        return this;
    }

    public BaseIndicator getIndicator() {
        return indicator;
    }

    public EasyViewPager setIndicator(BaseIndicator indicator) {
        this.indicator = indicator;
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
        if (adapter.isLoop()) {
            setCurrentItem(1);
        }
        if (indicator != null) {
            if (adapter.isLoop()) {
                indicator.createIndicator(adapter.getCount() - 2);
            } else {
                indicator.createIndicator(adapter.getCount());
            }
        }
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
