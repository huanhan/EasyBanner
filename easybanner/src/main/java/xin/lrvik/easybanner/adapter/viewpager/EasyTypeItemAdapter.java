package xin.lrvik.easybanner.adapter.viewpager;


import android.widget.ImageView;
import android.widget.TextView;

import xin.lrvik.easybanner.R;
import xin.lrvik.easybanner.adapter.recyclerview.BaseViewHolder;
import xin.lrvik.easybanner.dto.TypeItem;

/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/30.
 */
public abstract class EasyTypeItemAdapter extends BaseTypeItemAdapter<TypeItem> {

    public EasyTypeItemAdapter(int itemNum, int cols) {
        super(itemNum, cols, R.layout.item_type);
    }

    @Override
    protected void convert(BaseViewHolder holder, TypeItem data) {
        ImageView imageView = holder.getView(R.id.iv);
        TextView textView = holder.getView(R.id.tv);
        convert(imageView,textView,data);
    }

    protected abstract void convert(ImageView imageView, TextView textView, TypeItem data);
}
