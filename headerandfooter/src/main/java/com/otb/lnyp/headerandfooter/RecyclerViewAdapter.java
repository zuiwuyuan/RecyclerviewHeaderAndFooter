package com.otb.lnyp.headerandfooter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private List<DataBean> datas;
    private static final int IS_HEADER = 2;
    private static final int IS_FOOTER = 3;
    private static final int IS_NORMAL = 1;

    public RecyclerViewAdapter(List<DataBean> datas) {
        this.datas = datas;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerViewHolder holder;
        //对不同的flag创建不同的Holder
        if (viewType == IS_HEADER) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_header, viewGroup, false);
            holder = new RecyclerViewHolder(view, IS_HEADER, null);
            return holder;
        } else if (viewType == IS_FOOTER) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_footer, viewGroup, false);
            holder = new RecyclerViewHolder(view, IS_FOOTER, null);
            return holder;
        } else if (viewType == IS_NORMAL) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_text_view, viewGroup, false);
            holder = new RecyclerViewHolder(view, IS_NORMAL, new IMyViewHolderClicks() {
                @Override
                public void onUp(DataBean user, int position) {

                    if (position > 1) {
                        notifyItemMoved(position, position - 1);
                    }
                }

                @Override
                public void onDown(DataBean user, int position) {

                    if (position + 2 < getItemCount()) {
                        notifyItemMoved(position, position + 1);
                    }
                }
            });
            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder recyclerViewHolder, int position) {
        //对不同的Item相应不同的操作
        if (position != 0 && position != datas.size() + 1 && recyclerViewHolder.viewType == IS_NORMAL) {

            recyclerViewHolder.text.setText(datas.get(position - 1).info);

            DataBean user = datas.get(position - 1);
            recyclerViewHolder.bind(user);
        }
        if (position == 0 && recyclerViewHolder.viewType == IS_HEADER) {
            //header
            recyclerViewHolder.mButton.setOnClickListener(new View.OnClickListener() {
                int i = 0;

                @Override
                public void onClick(View v) {
                    recyclerViewHolder.mButton.setText(++i + "");
                }
            });
        }
        if (position == datas.size() + 1 && recyclerViewHolder.viewType == IS_FOOTER) {
            //footer
        }

    }

    @Override
    public int getItemCount() {
        return datas.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return IS_HEADER;
        } else if (position == datas.size() + 1) {
            return IS_FOOTER;
        } else {
            return IS_NORMAL;
        }
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;
        public Button mButton;

        public Button up;
        public Button dwon;
        public TextView text;

        IMyViewHolderClicks mListener;

        public int viewType;

        public RecyclerViewHolder(View itemView, int viewType, IMyViewHolderClicks mListener) {
            super(itemView);
            this.viewType = viewType;
            this.mListener = mListener;

            if (viewType == IS_HEADER) {
                mButton = (Button) itemView.findViewById(R.id.button);
            }
            if (viewType == IS_FOOTER) {
                //do some sthing
            }
            if (viewType == IS_NORMAL) {
                up = (Button) itemView.findViewById(R.id.btn_up);
                dwon = (Button) itemView.findViewById(R.id.btn_down);
                text = (TextView) itemView.findViewById(R.id.text_info);

                up.setOnClickListener(this);
                dwon.setOnClickListener(this);

            }
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

    private interface IMyViewHolderClicks {

        void onUp(DataBean user, int position);

        void onDown(DataBean user, int position);
    }

}
