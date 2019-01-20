package com.demo.eric.yummyorderdemo1.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.eric.yummyorderdemo1.R;
import com.demo.eric.yummyorderdemo1.model.bean.CommodityInfo;

import org.w3c.dom.Text;

import java.net.ContentHandler;
import java.util.List;

public class CommodityAdapter extends ArrayAdapter<CommodityInfo> {
    private int resourceId;

    public CommodityAdapter(Context context,int textViewResourceId,
                            List<CommodityInfo> obj){
        super(context,textViewResourceId,obj);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        CommodityInfo cdInfo=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView cdImage=(ImageView)view.findViewById (R.id.cd_image);
        TextView cdName=(TextView)view.findViewById(R.id.cd_name);
        TextView cdDes=(TextView)view.findViewById(R.id.cd_des);
        TextView cdcuPrice=(TextView)view.findViewById(R.id.cd_cuPrice);
        Button cdAdd=(Button)view.findViewById(R.id.cd_add);
//        cdImage.setImageResource(R.drawable.hamburger);
        cdName.setText(cdInfo.getName());
        cdDes.setText(cdInfo.getDescription());
        cdcuPrice.setText(Double.toString(cdInfo.getCuPrice()));
        return view;
    }
}
