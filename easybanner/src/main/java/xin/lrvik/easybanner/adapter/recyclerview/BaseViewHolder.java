package xin.lrvik.easybanner.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/30.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;

    public BaseViewHolder(View view) {
        super(view);
        this.views = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public View getRootView() {
        return itemView;
    }
}
