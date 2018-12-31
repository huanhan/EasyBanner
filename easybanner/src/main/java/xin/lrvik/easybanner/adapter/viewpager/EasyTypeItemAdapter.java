package xin.lrvik.easybanner.adapter.viewpager;


import xin.lrvik.easybanner.R;
import xin.lrvik.easybanner.dto.TypeItem;

/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/30.
 */
public abstract class EasyTypeItemAdapter extends BaseTypeItemAdapter<TypeItem> {

    public EasyTypeItemAdapter(int itemNum, int cols) {
        super(itemNum, cols, R.layout.item_type);
    }

}
