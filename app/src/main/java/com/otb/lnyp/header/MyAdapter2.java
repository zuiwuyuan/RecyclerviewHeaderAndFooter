package com.otb.lnyp.header;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class MyAdapter2 extends AdvancedAdapter2 {

    public static final String TAG = "MyAdapter";

    private List<DataBean> mDataset;

    // Provide a reference to the type of views that you are using
    // (custom viewholder)
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Button up;
        public Button dwon;
        public TextView text;

        IMyViewHolderClicks mListener;

        public ViewHolder(View itemView, IMyViewHolderClicks mListener) {
            super(itemView);

            this.mListener = mListener;

            up = (Button) itemView.findViewById(R.id.btn_up);
            dwon = (Button) itemView.findViewById(R.id.btn_down);
            text = (TextView) itemView.findViewById(R.id.text_info);

            up.setOnClickListener(this);
            dwon.setOnClickListener(this);
        }

        public void bind(DataBean user) {
            up.setTag(user);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_up:
                    mListener.onUp((DataBean) up.getTag(), getLayoutPosition());
                    break;
                case R.id.btn_down:
                    mListener.onDown((DataBean) up.getTag(), getLayoutPosition());
                    break;
            }
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter2(List<DataBean> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public int getAdvanceViewType(int position) {
        return position;
    }

    @Override
    protected int getAdvanceCount() {
        return mDataset.size();
    }

    @Override
    protected void onBindAdvanceViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder mHolder = (ViewHolder) holder;
        mHolder.text.setText(mDataset.get(position).info);

        DataBean user = mDataset.get(position);
        mHolder.bind(user);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateAdvanceViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v, new IMyViewHolderClicks() {

            @Override
            public void onUp(DataBean user, int position) {
                if (position > 0) {
                    notifyItemMoved(position, position - 1);
                }
            }

            @Override
            public void onDown(DataBean user, int position) {

                if (position + 1 < getItemCount()) {
                    notifyItemMoved(position, position + 1);
                }
            }
        });
        return vh;
    }

    private interface IMyViewHolderClicks {

        void onUp(DataBean user, int position);

        void onDown(DataBean user, int position);
    }
}
