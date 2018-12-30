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

    private final ImageView.ScaleType scaleType;

    public EasyImageAdapter(ImageView.ScaleType scaleType) {
        super(1, 1);
        this.scaleType = scaleType;
    }

    public EasyImageAdapter() {
        this(ImageView.ScaleType.FIT_XY);
    }

    @Override
    protected View createView(ViewGroup container, final List<T> data) {
        View view = View.inflate(container.getContext(), R.layout.item_img, null);
        ImageView imageView = view.findViewById(R.id.imageview);
        imageView.setScaleType(scaleType);
        convert(imageView, data.get(0));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(view, data.get(0));
            }
        });
        return view;
    }


    protected abstract void convert(ImageView view, T data);

}
