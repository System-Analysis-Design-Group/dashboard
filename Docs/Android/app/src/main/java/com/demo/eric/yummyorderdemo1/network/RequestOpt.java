package com.demo.eric.yummyorderdemo1.network;

import android.util.Log;

import com.demo.eric.yummyorderdemo1.controller.AccountsController;
import com.demo.eric.yummyorderdemo1.controller.DishesController;
import com.demo.eric.yummyorderdemo1.model.bean.PostData;
import com.demo.eric.yummyorderdemo1.model.bean.SendData;
import com.demo.eric.yummyorderdemo1.model.bean.SignIn;
import com.demo.eric.yummyorderdemo1.model.bean.SignUp;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class RequestOpt {
    /*生成request body*/
    public static String reqbodyGenerate(PostData postData){
        Gson gson=new Gson();
        String reqbody="";
        if(postData.getContentType().equals("REQUEST_POST_SIGNUP")){
            SignUp signup=new SignUp();
            signup.setAccount(postData.getAccount());
            signup.setPassword(postData.getPassword());
            reqbody=gson.toJson(signup);
            Log.d("reqbody",reqbody);
        }
        else if(postData.getContentType().equals("REQUEST_POST_SIGNIN")){
            SignIn signIn=new SignIn();
            signIn.setAccount(postData.getAccount());
            signIn.setPassword(postData.getPassword());
            reqbody=gson.toJson(signIn);
            Log.d("reqbody",reqbody);
        }
        return reqbody;
    }

    /*处理http请求*/
    public static void sendOkHttpRequest(SendData sendData, okhttp3.Callback callback){
        String reqbody=sendData.reqbody;
        String address=sendData.address;
        int RequestType=sendData.RequestType;

        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                ,reqbody);
        OkHttpClient client=new OkHttpClient();
        Request request;
        RequestBody body=new RequestBody() {
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
            }
        };

        if(RequestType==0){
            request=new Request.Builder().url(address).build();
        }
        else if(RequestType==1){
            body = RequestBody.create(JSON, reqbody);
            request=new Request.Builder().url(address).post(body).build();
        }
        else if(RequestType==2){
            body = RequestBody.create(JSON, reqbody);
            request=new Request.Builder().url(address).put(body).build();
        }
        else if(RequestType==3){
            body = RequestBody.create(JSON, reqbody);
            request=new Request.Builder().url(address).delete(body).build();
        }
        else{
            System.out.println("Error http request code!");
            return ;
        }

        Log.i("reqbody",body.toString());

        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
//        okHttpClient.newCall(request).enqueue(callback);   //法一
        client.newCall(request).enqueue(callback);   //法二
    }
}
