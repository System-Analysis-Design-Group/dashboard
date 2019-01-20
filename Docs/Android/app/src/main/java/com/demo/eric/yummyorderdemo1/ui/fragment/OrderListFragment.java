package com.demo.eric.yummyorderdemo1.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.demo.eric.yummyorderdemo1.R;
import com.demo.eric.yummyorderdemo1.controller.OrdersController;
import com.demo.eric.yummyorderdemo1.model.bean.GoodsInfo;
import com.demo.eric.yummyorderdemo1.model.bean.OrderInfo;
import com.demo.eric.yummyorderdemo1.model.bean.OrderList;
import com.demo.eric.yummyorderdemo1.model.bean.SendData;
import com.demo.eric.yummyorderdemo1.network.RequestOpt;
import com.demo.eric.yummyorderdemo1.network.ResponseOpt;
import com.demo.eric.yummyorderdemo1.ui.activity.SignupActivity;
import com.demo.eric.yummyorderdemo1.ui.adapter.CommodityAdapter;
import com.demo.eric.yummyorderdemo1.ui.adapter.OrderListAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.demo.eric.yummyorderdemo1.util.Constants.RequestType.REQUEST_GET_USER_ORDERLIST;


/**
 * A simple {@link Fragment} subclass
 */
public class OrderListFragment extends Fragment {
    OrderList orderList=new OrderList();

    public static List<OrderInfo> orderInfo=new ArrayList<>();

    OrderListAdapter olAdapter;

    ListView listView;

    int userId=9;   //指定9号用户

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("life","orderlist_onview");
        view= inflater.inflate(R.layout.fragment_orderlist, container, false);
//        OrderInfo o1=new OrderInfo(2);
//        orderInfo.add(o1);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("life","orderlist_onact");

        SendData sendData= OrdersController.GetOrderList(userId);
        queryFromServer(sendData);
    }

    private void queryFromServer(final SendData sendData){
        RequestOpt.sendOkHttpRequest(sendData,new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                Log.i("resbody: ",responseText);
                boolean result = ResponseOpt.handleResponse(responseText,sendData.ContentType);
                if (result) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(sendData.ContentType==REQUEST_GET_USER_ORDERLIST){   //若为请求订单列表，则更新list
                                if(orderInfo.size()>0){
                                    Log.i("orderInfo",orderInfo.toString());
                                    olAdapter=new OrderListAdapter(getContext(), R.layout.adapter_orderlist_item
                                            ,orderInfo);
                                    listView=(ListView)view.findViewById(R.id.ol_listview);
                                    listView.setAdapter(olAdapter);
                                    listView.setSelection(0);
                                }
                            }
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call call,IOException e){
                //通过runOnUiThread()回到主线程处理逻辑
                getActivity().runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        Toast.makeText(getContext(),"加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public static List<OrderInfo> getOrderInfo(){
        return orderInfo;
    }
}
