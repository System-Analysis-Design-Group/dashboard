package com.demo.eric.yummyorderdemo1.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.eric.yummyorderdemo1.R;
import com.demo.eric.yummyorderdemo1.model.bean.CommodityInfo;
import com.demo.eric.yummyorderdemo1.model.bean.GoodsInfo;
import com.demo.eric.yummyorderdemo1.model.bean.OrderInfo;
import com.demo.eric.yummyorderdemo1.util.Constants;

import org.w3c.dom.Text;

import java.net.ContentHandler;
import java.util.List;

public class OrderListAdapter extends ArrayAdapter<OrderInfo> {
    private int resourceId;

    public OrderListAdapter(Context context, int textViewResourceId,
                            List<OrderInfo> obj){
        super(context,textViewResourceId,obj);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        OrderInfo orderInfo=getItem(position);
        List<GoodsInfo> goodsInfo=orderInfo.getGoodsInfo();
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        //getView
        TextView ol_item_shopname=(TextView)view.findViewById(R.id.ol_item_shopname);
        TextView ol_item_cdname=(TextView)view.findViewById(R.id.ol_item_cdname);
        TextView ol_item_cdprice=(TextView)view.findViewById(R.id.ol_item_cdprice);
        ol_item_shopname.setText(Constants.SomeMap.storeIdToName[orderInfo.getStoreID()]);
        StringBuilder s=new StringBuilder();
        s.append(Constants.SomeMap.cdIdToName[goodsInfo.get(0).getDishId()]);
        s.append(goodsInfo.get(0).getNum()+"个");
        if(goodsInfo.size()>1) s.append("   等"+goodsInfo.size()+"件商品");
        Log.d("string: ",s.toString());
        ol_item_cdname.setText(s);
        double ttlprice=0.0;
        for(int i=0;i<goodsInfo.size();i++){
            ttlprice+=(goodsInfo.get(i).getUnitPrice()*goodsInfo.get(i).getNum());
        }
        ol_item_cdprice.setText(String.valueOf("¥"+ttlprice));
        //设置View
        return view;
    }
}
