package xin.lrvik.easybanner.adapter.viewpager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xin.lrvik.easybanner.R;
import xin.lrvik.easybanner.adapter.recyclerview.BaseRecyclerViewAdapter;
import xin.lrvik.easybanner.adapter.recyclerview.BaseViewHolder;
import xin.lrvik.easybanner.adapter.recyclerview.EasyGridLayoutManager;


/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/30.
 */
public abstract class BaseTypeItemAdapter<T> extends BaseEasyViewPagerAdapter<T> {

    private int layoutId;

    public BaseTypeItemAdapter(int itemNum, int cols, int layoutId) {
        super(itemNum, cols);
        this.layoutId = layoutId;
    }

    @Override
    protected View createView(ViewGroup container, List<T> data) {
        View view = View.inflate(container.getContext(), R.layout.item_recyclerview, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        EasyGridLayoutManager layoutManager = new EasyGridLayoutManager(container.getContext(), getCols());
        layoutManager.setScrollEnabled(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.setAdapter(recycleAdapter);
        recyclerView.setAdapter(new BaseRecyclerViewAdapter<T>(container.getContext(), data, layoutId) {
            @Override
            protected void bindData(BaseViewHolder holder, final T data, int position) {
                convert(holder, data);
                holder.getRootView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onItemClickListner != null) {
                            onItemClickListner.onItemClickListner(view, data);
                        }
                    }
                });
            }
        });
        return view;
    }


    protected abstract void convert(BaseViewHolder holder, T data);
}
