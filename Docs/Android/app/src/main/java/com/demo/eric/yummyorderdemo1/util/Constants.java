package com.demo.eric.yummyorderdemo1.util;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static class UrlHead{
        public static String URLHEAD_ACCOUNT_INNER="http://172.18.233.33:9090/";
        public static String URLHEAD_ACCOUNT_OUTER="http://food2.ngrok.xiaomiqiu.cn/";
        public static String URLHEAD_COMMENT_ORDER_DISH="http://food.ngrok.xiaomiqiu.cn/";
    }

    public static class RequestType{
        public static int REQUEST_GET_COMMODITYLIST=201;
        public static int REQUEST_GET_MERCHANTLIST=202;
        public static int REQUEST_POST_SIGNUP=203;
        public static int REQUEST_POST_SIGNIN=204;
        public static int REQUEST_GET_USER_ORDERLIST=205;
    }

    public static class SomeMap{
//        public static Map<Integer,String> map=new HashMap<Integer,String>();
        public static String[] cdIdToName={"","随意","随意","随意","猪肉炖粉条","小鸡炖蘑菇","白菜肉水饺"};
        public static String[] storeIdToName={"","","","","","","东北人家"};
    }


}
