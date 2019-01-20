package com.demo.eric.yummyorderdemo1.controller;

import com.demo.eric.yummyorderdemo1.model.bean.SendData;

import static com.demo.eric.yummyorderdemo1.util.Constants.RequestType.REQUEST_GET_MERCHANTLIST;
import static com.demo.eric.yummyorderdemo1.util.Constants.RequestType.REQUEST_GET_USER_ORDERLIST;
import static com.demo.eric.yummyorderdemo1.util.Constants.UrlHead.URLHEAD_COMMENT_ORDER_DISH;

public class OrdersController {
    static SendData sendData=new SendData();

    public static SendData GetOrderList(int userId){
        sendData.address = URLHEAD_COMMENT_ORDER_DISH+"orders/user/"+String.valueOf(userId);
        sendData.RequestType = 0;   //get
        sendData.reqbody= "None";
        sendData.ContentType=REQUEST_GET_USER_ORDERLIST;
        return sendData;
    }
}