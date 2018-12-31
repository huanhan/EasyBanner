package xin.lrvik.easybanner.indicator;

/**
 * 指示器动画基类
 * <p>
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/31.
 */
public interface BaseIndicator {

    void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

    void createIndicator(int size);


}
