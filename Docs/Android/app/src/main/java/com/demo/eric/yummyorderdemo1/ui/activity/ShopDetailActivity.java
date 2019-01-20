package com.demo.eric.yummyorderdemo1.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.demo.eric.yummyorderdemo1.R;
import com.demo.eric.yummyorderdemo1.ui.adapter.MainpageAdapter;
import com.demo.eric.yummyorderdemo1.ui.fragment.CommodityListFragment;
import com.demo.eric.yummyorderdemo1.ui.fragment.MerchantCommentsFragment;
import com.demo.eric.yummyorderdemo1.ui.fragment.MerchantDetailFragment;
import com.demo.eric.yummyorderdemo1.ui.fragment.MerchantListFragment;
import com.demo.eric.yummyorderdemo1.ui.fragment.OrderListFragment;
import com.demo.eric.yummyorderdemo1.ui.fragment.UserCenterFragment;

import java.util.ArrayList;

public class ShopDetailActivity extends AppCompatActivity {
    private Button backButton;

    private TabLayout tabLayout;

    private ViewPager viewPager;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private ArrayList<String> tabList = new ArrayList<>();

    MainpageAdapter vpAdapter;     //ViewPager的Fragment Adapter


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetail);
        initView();
        setBaseLayout();   //设置TabLayout和ViewPager关联
    }

    /*初始化activity*/
    private void initView(){
        tabLayout = (TabLayout) findViewById(R.id.tab_shopdetail);
        viewPager = (ViewPager) findViewById(R.id.vp_shopdetail);
        vpAdapter=new MainpageAdapter(getSupportFragmentManager(),mFragments,tabList);
    }

    /*设置tablayout和viewpager*/
    private void setBaseLayout(){
        CommodityListFragment fragment1 = new CommodityListFragment();
        MerchantCommentsFragment fragment2 = new MerchantCommentsFragment();
        MerchantDetailFragment fragment3 = new MerchantDetailFragment();
        mFragments.add(fragment1);
        mFragments.add(fragment2);
        mFragments.add(fragment3);
        tabList.add("商品");
        tabList.add("评价");
        tabList.add("商家");
        viewPager.setAdapter(vpAdapter);   //ViewPager设置Adapter
        tabLayout.setupWithViewPager(viewPager);   //TabLayout和ViewPager关联
    }

}
