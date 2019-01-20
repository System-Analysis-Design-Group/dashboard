package com.demo.eric.yummyorderdemo1.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.demo.eric.yummyorderdemo1.R;
import com.demo.eric.yummyorderdemo1.ui.adapter.MainpageAdapter;
import com.demo.eric.yummyorderdemo1.ui.fragment.MerchantListFragment;
import com.demo.eric.yummyorderdemo1.ui.fragment.OrderListFragment;
import com.demo.eric.yummyorderdemo1.ui.fragment.UserCenterFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();
    MainpageAdapter vpAdapter;     //ViewPager的Fragment Adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();         //初始化控件与数据结构
        setBaseLayout();   //设置TabLayout和ViewPager关联
        Log.i("life","act_end");
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tab_mainpage);
        viewPager = (ViewPager) findViewById(R.id.vp_mainpage);
        vpAdapter=new MainpageAdapter(getSupportFragmentManager(),mFragments,list);
    }

    private void setBaseLayout(){
        MerchantListFragment fragment1 = new MerchantListFragment();
        OrderListFragment fragment2 = new OrderListFragment();
        UserCenterFragment fragment3 = new UserCenterFragment();
        mFragments.add(fragment1);
        mFragments.add(fragment2);
        mFragments.add(fragment3);
        list.add("首页");
        list.add("订单");
        list.add("我的");
        viewPager.setAdapter(vpAdapter);   //ViewPager设置Adapter
        tabLayout.setupWithViewPager(viewPager);   //TabLayout和ViewPager关联
    }
}
