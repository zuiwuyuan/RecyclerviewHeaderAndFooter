package com.otb.lnyp.header;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public abstract class AdvancedAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "AdvancedAdapter2";
    private ArrayList<View> mHeaderViews = new ArrayList<>(); //头视图
    private ArrayList<View> mFooterViews = new ArrayList<>();   //尾视图


    public void addHeaderView(View headerView) {
        mHeaderViews.add(headerView);
    }

    public void addFooterView(View footerView) {
        mFooterViews.add(footerView);
    }

    private ArrayList<Integer> mHeaderViewTypes = new ArrayList<>();
    private ArrayList<Integer> mFooterViewTypes = new ArrayList<>();

    @Override
    public int getItemViewType(int position) {

        if (mHeaderViews.size() > 0 && position < mHeaderViews.size()) {
            //用position作为HeaderView 的   ViewType标记
            //记录每个ViewType标记
            mHeaderViewTypes.add(position * 100000);
            return position * 100000;
        }


        if (mFooterViews.size() > 0 && position > getAdvanceCount() - 1 + mHeaderViews.size()) {
            //用position作为FooterView 的   ViewType标记
            //记录每个ViewType标记
            mFooterViewTypes.add(position * 100000);
            return position * 100000;
        }

        if (mHeaderViews.size() > 0) {
            return getAdvanceViewType(position - mHeaderViews.size());
        }


        return getAdvanceViewType(position);
    }

    public abstract int getAdvanceViewType(int position);

    /**
     * !! 不能为0！！！
     *
     * @return
     */
    protected abstract int getAdvanceCount();

    protected abstract void onBindAdvanceViewHolder(RecyclerView.ViewHolder holder, int i);

    protected abstract RecyclerView.ViewHolder onCreateAdvanceViewHolder(ViewGroup parent, int viewType);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (mHeaderViewTypes.contains(viewType)) {
            return new HeaderHolder(mHeaderViews.get(viewType / 100000));
        }

        if (mFooterViewTypes.contains(viewType)) {
            int index = viewType / 100000 - getAdvanceCount() - mHeaderViews.size();
            return new FooterHolder(mFooterViews.get(index));
        }

        return onCreateAdvanceViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (mFooterViews.size() > 0 && (position > getAdvanceCount() - 1 + mHeaderViews.size())) {
            return;
        }

        if (mHeaderViews.size() > 0) {
            if (position < mHeaderViews.size()) {
                return;
            }
            onBindAdvanceViewHolder(holder, position - mHeaderViews.size());
            return;
        }
        onBindAdvanceViewHolder(holder, position);
    }


    class HeaderHolder extends RecyclerView.ViewHolder {

        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }

    class FooterHolder extends RecyclerView.ViewHolder {

        public FooterHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        if (mHeaderViews.size() > 0 && mFooterViews.size() > 0) {
            return getAdvanceCount() + mHeaderViews.size() + mFooterViews.size();
        }
        if (mHeaderViews.size() > 0) {
            return getAdvanceCount() + mHeaderViews.size();
        }
        if (mFooterViews.size() > 0) {
            return getAdvanceCount() + mFooterViews.size();
        }

        return getAdvanceCount();
    }


}