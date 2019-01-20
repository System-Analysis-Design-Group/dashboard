package com.demo.eric.yummyorderdemo1.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.demo.eric.yummyorderdemo1.R;
import com.demo.eric.yummyorderdemo1.controller.AccountsController;
import com.demo.eric.yummyorderdemo1.controller.DishesController;
import com.demo.eric.yummyorderdemo1.model.bean.CommodityInfo;
import com.demo.eric.yummyorderdemo1.model.bean.CommodityList;
import com.demo.eric.yummyorderdemo1.model.bean.SendData;
import com.demo.eric.yummyorderdemo1.network.RequestOpt;
import com.demo.eric.yummyorderdemo1.network.ResponseOpt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.demo.eric.yummyorderdemo1.util.Constants.RequestType.REQUEST_GET_COMMODITYLIST;
import static com.demo.eric.yummyorderdemo1.util.Constants.RequestType.REQUEST_GET_MERCHANTLIST;

import com.demo.eric.yummyorderdemo1.ui.activity.ShopDetailActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MerchantListFragment extends Fragment {

    ArrayAdapter<String> lvAdapter;   //ListView的ArrayAdapter

    public ListView listView;

    public static List<String> mtList;

    public static CommodityList cdList;

    private int selectedMerchant;

    private SendData sendData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("life","merlist_onview");
        View frg1 = inflater.inflate(R.layout.fragment_merchantlist, container, false);
        listView=(ListView)frg1.findViewById(R.id.list_view);  //list_view必须在当前setContentView的同一个layout中
        mtList=new ArrayList<>();
        lvAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,mtList);  //ListView适配,得到当前上下文
        listView.setAdapter(lvAdapter);     //listView初始设置
        return frg1;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.i("life","merlist_onact");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                selectedMerchant=Integer.parseInt(mtList.get(position));
                sendData=DishesController.GetDishList(selectedMerchant);      //获取具体商家的商品列表
                queryFromServer(sendData);     //从server获取数据并跳转界面
            }
        });
        queryMerchantList();    //请求商家列表
    }

    /*查询商家列表*/
    private void queryMerchantList(){
        sendData= AccountsController.GetMerchantList();   //得到商家列表的request data
        queryFromServer(sendData);    //查询商家
    }

    /*更新ListView*/
    private void updateListView(){
        lvAdapter.notifyDataSetChanged();
        listView.setSelection(0);
    }

    /*从服务器请求SendData对应响应*/
    private void queryFromServer(final SendData sendData){
        RequestOpt.sendOkHttpRequest(sendData,new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                Log.i("resbody: ",responseText);
                boolean result = false;
                result = ResponseOpt.handleResponse(responseText, sendData.ContentType);
                if (result) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("dataList: ",mtList.toString());
                            if(sendData.ContentType==REQUEST_GET_MERCHANTLIST){   //若为商家列表，更新ListView
                                updateListView();
                            }
                            else if(sendData.ContentType==REQUEST_GET_COMMODITYLIST){   //若为CommodityList，跳转商品列表界面
                                Intent intent=new Intent(getActivity(), ShopDetailActivity.class);
                                Bundle bundle=new Bundle();
                                bundle.putSerializable("GETCOMMODITYLIST",cdList);
                                intent.putExtras(bundle);

                                List<CommodityInfo> obj=cdList.getObj();
                                CommodityInfo cdInfo=obj.get(0);
                                Log.d("cdList: ",cdInfo.getName()+cdInfo.getDescription());

                                startActivity(intent);
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

    /*外部访问商家列表List*/
    public static List<String> getMtList(){
        return mtList;
    }

    /*外部访问商品列表bean*/
    public static CommodityList getCdList(){
        return cdList;
    }
}
