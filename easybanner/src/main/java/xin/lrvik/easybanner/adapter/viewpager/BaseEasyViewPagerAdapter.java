package xin.lrvik.easybanner.adapter.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/29.
 */
public abstract class BaseEasyViewPagerAdapter<T> extends PagerAdapter {

    private List<T> mData;
    private int itemNum;
    private int cols;

    public BaseEasyViewPagerAdapter(List<T> data, int itemNum, int cols) {
        this.mData = data == null ? new ArrayList<T>() : data;
        this.itemNum = itemNum;
        this.cols = cols;
    }

    @Override
    public int getCount() {
        //需要+2因为首尾需要做循环
        return (int) Math.ceil(mData.size() / (float) itemNum) + 2;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        //根据页数分发每页要用到的数据
        //总页数
        int totalPage = getCount();
        //当前页 position  第0和最后一页是用来循环的

        View view;
        if (position == 0) {
            //当前页是第0页的时候，获取最后一页数据(最后一页=总页数-首位页2-下标1)
            view = createView(container, getPageDatas(totalPage - 2 - 1));
        } else if (position == totalPage - 1) {
            //当前页是最后页的时候，获取第一页数据
            view = createView(container, getPageDatas(0));
        } else {
            view = createView(container, getPageDatas(position - 1));
        }
        container.addView(view);
        return view;
    }

    private List<T> getPageDatas(int position) {
        List<T> pageDatas = mData.subList(position * itemNum,
                (position * itemNum + itemNum) > mData.size() ? mData.size() : (position * itemNum + itemNum));
        return pageDatas;
    }

    protected abstract View createView(ViewGroup container, List<T> data);

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    public List<T> getData() {
        return mData;
    }

    public void setData(List<T> mData) {
        this.mData = mData;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
}
