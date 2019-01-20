package com.demo.eric.yummyorderdemo1.controller;

import com.demo.eric.yummyorderdemo1.model.bean.PostData;
import com.demo.eric.yummyorderdemo1.model.bean.SendData;
import com.demo.eric.yummyorderdemo1.network.RequestOpt;
import com.demo.eric.yummyorderdemo1.ui.activity.SignupActivity;

import static com.demo.eric.yummyorderdemo1.util.Constants.RequestType.REQUEST_GET_MERCHANTLIST;
import static com.demo.eric.yummyorderdemo1.util.Constants.RequestType.REQUEST_POST_SIGNIN;
import static com.demo.eric.yummyorderdemo1.util.Constants.RequestType.REQUEST_POST_SIGNUP;
import static com.demo.eric.yummyorderdemo1.util.Constants.UrlHead.URLHEAD_ACCOUNT_OUTER;

public class AccountsController {
    private static SendData sendData=new SendData();

    public static SendData GetMerchantList(){
        sendData.address = URLHEAD_ACCOUNT_OUTER+"Accounts/Stores";
        sendData.RequestType = 0;   //get
        sendData.reqbody= "None";
        sendData.ContentType=REQUEST_GET_MERCHANTLIST;
        return sendData;
    }

    public static SendData PostSignUp(PostData postData){
        sendData.address=URLHEAD_ACCOUNT_OUTER+"Accounts/Signup";
        sendData.RequestType=1;
        sendData.reqbody= RequestOpt.reqbodyGenerate(postData);
        sendData.ContentType=REQUEST_POST_SIGNUP;
        return sendData;
    }

    public static SendData PostSignin(PostData postData){
        sendData.address=URLHEAD_ACCOUNT_OUTER+"Accounts/Signin";
        sendData.RequestType=1;
        sendData.reqbody= RequestOpt.reqbodyGenerate(postData);
        sendData.ContentType=REQUEST_POST_SIGNIN;
        return sendData;
    }
}
