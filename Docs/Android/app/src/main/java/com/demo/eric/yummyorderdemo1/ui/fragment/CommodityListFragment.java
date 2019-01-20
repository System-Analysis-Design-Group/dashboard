package com.demo.eric.yummyorderdemo1.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.demo.eric.yummyorderdemo1.R;
import com.demo.eric.yummyorderdemo1.model.bean.CommodityInfo;
import com.demo.eric.yummyorderdemo1.model.bean.CommodityList;
import com.demo.eric.yummyorderdemo1.network.ResponseOpt;
import com.demo.eric.yummyorderdemo1.ui.adapter.CommodityAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class CommodityListFragment extends Fragment{
    CommodityList cdList;

    public static List<CommodityInfo> cdInfo=new ArrayList<>();

    CommodityAdapter cdAdapter;

    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent=getActivity().getIntent();    //解析数据
        cdList=(CommodityList)intent.getSerializableExtra("GETCOMMODITYLIST");
        cdInfo=cdList.getObj();    //获得数据
        cdAdapter=new CommodityAdapter(getContext(), R.layout.adapter_commoditylist_item
                                ,cdInfo);
        View view= inflater.inflate(R.layout.fragment_commoditylist,container,false);
        listView=(ListView)view.findViewById(R.id.cd_listview);
        listView.setAdapter(cdAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
//        pareseIntent();
//        updataListView();
    }

    private void updataListView(){
        cdAdapter.notifyDataSetChanged();
        listView.setSelection(0);
    }

    /*解析数据并显示*/
    private void pareseIntent(){
        for(int i=0;i<cdInfo.size();i++){
        Log.d("cdInfo: ",cdInfo.get(i).toString());
        Log.d("cdInfo: ",cdInfo.get(i).getName());
        Log.d("cdInfo: ",cdInfo.get(i).getDescription());
//        Log.d("cdInfo: ",cdInfo.get(0).getImagePath());
        Log.d("cdInfo: ",cdInfo.get(i).getTypeId());
        Log.d("cdInfo: ",Double.toString(cdInfo.get(i).getCuPrice()));
        Log.d("cdInfo: ",Double.toString(cdInfo.get(i).getOrPrice()));
        Log.d("cdInfo: ",Integer.toString(cdInfo.get(i).getId()));
        Log.d("cdInfo: ",Integer.toString(cdInfo.get(i).getStoreId())); }
    }
}
