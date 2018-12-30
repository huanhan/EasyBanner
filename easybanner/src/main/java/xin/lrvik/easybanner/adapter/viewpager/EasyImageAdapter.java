package xin.lrvik.easybanner.adapter.viewpager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import xin.lrvik.easybanner.R;

/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/30.
 */
public abstract class EasyImageAdapter<T> extends BaseEasyViewPagerAdapter<T> {

    public EasyImageAdapter(List data) {
        super(data, 1, 1);
    }

    @Override
    protected View createView(ViewGroup container, List<T> data) {
        View view = View.inflate(container.getContext(), R.layout.item_img, null);
        ImageView imageView = view.findViewById(R.id.imageview);
        convert(imageView, data.get(0));
        return view;
    }

    protected abstract void convert(ImageView view, T data);

}
