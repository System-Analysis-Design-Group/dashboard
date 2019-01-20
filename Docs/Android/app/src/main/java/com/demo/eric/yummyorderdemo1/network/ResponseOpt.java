package com.demo.eric.yummyorderdemo1.network;

import android.content.Intent;
import android.util.Log;

import com.demo.eric.yummyorderdemo1.R;
import com.demo.eric.yummyorderdemo1.controller.OrdersController;
import com.demo.eric.yummyorderdemo1.model.bean.CommodityList;
import com.demo.eric.yummyorderdemo1.model.bean.GoodsInfo;
import com.demo.eric.yummyorderdemo1.model.bean.MerchantList;
import com.demo.eric.yummyorderdemo1.model.bean.OrderInfo;
import com.demo.eric.yummyorderdemo1.model.bean.OrderList;
import com.demo.eric.yummyorderdemo1.ui.activity.MainActivity;
import com.demo.eric.yummyorderdemo1.ui.activity.ShopDetailActivity;
import com.demo.eric.yummyorderdemo1.ui.adapter.CommodityAdapter;
import com.demo.eric.yummyorderdemo1.ui.adapter.OrderListAdapter;
import com.demo.eric.yummyorderdemo1.ui.fragment.MerchantListFragment;
import com.demo.eric.yummyorderdemo1.ui.fragment.OrderListFragment;
import com.google.gson.Gson;

import java.util.List;

import static com.demo.eric.yummyorderdemo1.ui.fragment.MerchantListFragment.getCdList;
import static com.demo.eric.yummyorderdemo1.ui.fragment.MerchantListFragment.getMtList;
import static com.demo.eric.yummyorderdemo1.util.Constants.RequestType.REQUEST_GET_COMMODITYLIST;
import static com.demo.eric.yummyorderdemo1.util.Constants.RequestType.REQUEST_GET_MERCHANTLIST;
import static com.demo.eric.yummyorderdemo1.util.Constants.RequestType.REQUEST_GET_USER_ORDERLIST;
import static com.demo.eric.yummyorderdemo1.util.Constants.RequestType.REQUEST_POST_SIGNIN;
import static com.demo.eric.yummyorderdemo1.util.Constants.RequestType.REQUEST_POST_SIGNUP;

public class ResponseOpt {
    public static boolean handleResponse(String responseData, int ct) {
        try {
            Gson gson=new Gson();
            if (ct == REQUEST_GET_MERCHANTLIST) {
                Log.i("resbody: ","1");
                MerchantList merchantList=gson.fromJson(responseData,MerchantList.class);
                List<String> mtList=getMtList();
                int[] storeId=merchantList.getObj().getStoreId();
                for(int i=0;i<storeId.length;i++){           //将数据添加进List
                    mtList.add(Integer.toString(storeId[i]));
                }
                Log.i("resbody: ",responseData);
                Log.i("resbody: ",mtList.toString());
            }
            else if(ct==REQUEST_GET_COMMODITYLIST){
//                CommodityList cdList=getCdList();   //错误，若该其中一个变量，另一个不会变，改了值才有变化
                MerchantListFragment.cdList=gson.fromJson(responseData,CommodityList.class);  //明确变量的赋值和地址
//                Log.i("resbody: ",responseData);
//                Log.i("resbody: ",MerchantListFragment.cdList.getObj().toString());
            }
            else if(ct==REQUEST_POST_SIGNIN){
                //To be continued
                //可能要map

            }
            else if(ct==REQUEST_GET_USER_ORDERLIST ){
                OrderList orderList=gson.fromJson(responseData, OrderList.class);   //获取OrderInfo列表
                OrderListFragment.orderInfo=orderList.getObj();
//                Log.i("orderInfo",orderInfo.toString());
            }       
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
