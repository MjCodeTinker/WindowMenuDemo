package com.code_tinker.window.menu;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.code_tinker.window.menu.adapter.MyAdapter;
import com.code_tinker.window.menu.info.ListEntity;
import com.code_tinker.window.menu.utils.PopupMenuUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ImageView ivImg;

    private RelativeLayout rlClick;
    private Context context;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListData();
    }

    private void initViews() {
        context = this;
        ivImg = (ImageView) findViewById(R.id.iv_img);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        rlClick = (RelativeLayout) findViewById(R.id.rl_click);
        rlClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenuUtil.getInstance()._show(context, ivImg);
            }
        });

    }

    @Override
    public void onBackPressed() {
        // 当popupWindow 正在展示的时候 按下返回键 关闭popupWindow 否则关闭activity
        if (PopupMenuUtil.getInstance()._isShowing()) {
            PopupMenuUtil.getInstance()._rlClickAction();
        } else {
            super.onBackPressed();
        }
    }


    /**************************
     * 列表数据
     *****************************/

    MyAdapter adapter;
    List<ListEntity> data;

    private void initListData() {
        data = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        data.add(new ListEntity());

        for(int i=0;i<20;i++){
            ListEntity listEntity=new ListEntity();
            listEntity.name="八千米海岸";
            listEntity.date="10月25日12:25";
            listEntity.content="追求随性的路上还不够洒脱。记得所有的好，感谢所遇到的一切";

            listEntity.avatarUrl="http://img2.imgtn.bdimg.com/it/u=2452097355,3155741241&fm=21&gp=0.jpg";
            listEntity.descUrl="http://img4.imgtn.bdimg.com/it/u=2373780812,700019726&fm=21&gp=0.jpg";

            listEntity.layoutType=1;
            data.add(listEntity);
        }
        setAdapter();
    }

    public void setAdapter() {
        if (adapter == null) {
            adapter = new MyAdapter(context, data);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

}
