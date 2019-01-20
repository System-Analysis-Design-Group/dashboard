package com.demo.eric.yummyorderdemo1.controller;

import com.demo.eric.yummyorderdemo1.model.bean.SendData;

import static com.demo.eric.yummyorderdemo1.util.Constants.RequestType.REQUEST_GET_COMMODITYLIST;
import static com.demo.eric.yummyorderdemo1.util.Constants.UrlHead.URLHEAD_COMMENT_ORDER_DISH;


public class DishesController {

    private static SendData sendData=new SendData();

    public static SendData GetDishList(int storeId){
        sendData.address = URLHEAD_COMMENT_ORDER_DISH+Integer.toString(storeId);
        sendData.RequestType = 0;   //get
        sendData.reqbody= "None";
        sendData.ContentType=REQUEST_GET_COMMODITYLIST;
        return sendData;
    };
}
