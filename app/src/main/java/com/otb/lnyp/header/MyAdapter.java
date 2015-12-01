package com.otb.lnyp.header;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.otb.lnyp.header.recycler.BaseRecyclerAdapter;

import java.util.List;

public class MyAdapter extends BaseRecyclerAdapter<BaseRecyclerAdapter.BaseRecyclerViewHolder, DataBean> {

    public static final String TAG = "MyAdapter";

    private List<DataBean> mDataset;

    public class ViewHolder extends BaseRecyclerAdapter.BaseRecyclerViewHolder implements View.OnClickListener {
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

    public MyAdapter(List<DataBean> myDataset) {
        super(myDataset);
        mDataset = myDataset;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
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

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position, DataBean data) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.text.setText(mDataset.get(position).info);

        DataBean user = mDataset.get(position);
        viewHolder.bind(user);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    private interface IMyViewHolderClicks {

        void onUp(DataBean user, int position);

        void onDown(DataBean user, int position);
    }

}