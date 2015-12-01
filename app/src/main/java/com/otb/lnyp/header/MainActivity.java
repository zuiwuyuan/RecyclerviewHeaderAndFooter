package com.otb.lnyp.header;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyAdapter2 mAdapter ;
    private RecyclerView mListView ;

    private  TextView headView ;
    private  TextView footerView ;
    private List<DataBean> myDataset = new ArrayList<DataBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDataset.add(new DataBean("无缘公子1"));
        myDataset.add(new DataBean("无缘公子2"));
        myDataset.add(new DataBean("无缘公子3"));
        myDataset.add(new DataBean("无缘公子4"));
        myDataset.add(new DataBean("无缘公子5"));
        myDataset.add(new DataBean("无缘公子6"));

        mAdapter = new MyAdapter2(myDataset);
        mListView = (RecyclerView) findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this) ;
        mListView.setLayoutManager(manager);

        headView = new TextView(this) ;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) ;
        headView.setLayoutParams(params);
        headView.setText("我是头部View001");

        mAdapter.addHeaderView(headView);

//        footerView = new TextView(this) ;
//        ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) ;
//        footerView.setLayoutParams(params1);
//        footerView.setText("我是底部View001");
//
//        mAdapter.addFootView(footerView);

        mListView.setAdapter(mAdapter);
    }

    public void add(View view) {
        myDataset.add(new DataBean());
        mAdapter.notifyDataSetChanged();
    }
}
