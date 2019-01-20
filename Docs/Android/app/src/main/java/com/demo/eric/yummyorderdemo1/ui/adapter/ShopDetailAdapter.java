package com.demo.eric.yummyorderdemo1.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ShopDetailAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private ArrayList<String> list;

    public ShopDetailAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> list) {
        super(fm);
        this.fragments = fragments;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
