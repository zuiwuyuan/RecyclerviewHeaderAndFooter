package com.otb.lnyp.headerandfooter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private List<String> lists;
//    private RecyclerView mRecyclerView;
//    RecyclerViewAdapter mAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        initDatas();
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        mAdapter = new RecyclerViewAdapter(lists) ;
//        mRecyclerView.setAdapter(mAdapter);
//    }
//    private void initDatas() {
//        lists = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            lists.add(i + "");
//        }
//    }
//
//    public void add(View view) {
//        lists.add("吴亚明");
//        mAdapter.notifyDataSetChanged();
//    }

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<DataBean> myDataset = new ArrayList<DataBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // improve performance if you know that changes in content
        // do not change the size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)\

        myDataset.add(new DataBean("无缘公子1"));
        myDataset.add(new DataBean("无缘公子2"));
        myDataset.add(new DataBean("无缘公子3"));
        myDataset.add(new DataBean("无缘公子4"));
        myDataset.add(new DataBean("无缘公子5"));
        myDataset.add(new DataBean("无缘公子6"));

        mAdapter = new RecyclerViewAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void up(View view) {

        mAdapter.notifyItemMoved(2, 1);
    }

    public void down(View view) {
        mAdapter.notifyItemMoved(1, 2);
    }

    public void add(View view) {
        myDataset.add(new DataBean(""));
        mAdapter.notifyDataSetChanged();
    }
}
