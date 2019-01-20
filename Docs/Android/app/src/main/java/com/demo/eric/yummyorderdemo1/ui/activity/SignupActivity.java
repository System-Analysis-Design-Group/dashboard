package com.demo.eric.yummyorderdemo1.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.demo.eric.yummyorderdemo1.R;
import com.demo.eric.yummyorderdemo1.controller.AccountsController;
import com.demo.eric.yummyorderdemo1.model.bean.CommodityInfo;
import com.demo.eric.yummyorderdemo1.model.bean.PostData;
import com.demo.eric.yummyorderdemo1.model.bean.SendData;
import com.demo.eric.yummyorderdemo1.network.RequestOpt;
import com.demo.eric.yummyorderdemo1.network.ResponseOpt;
import com.demo.eric.yummyorderdemo1.ui.fragment.UserCenterFragment;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.demo.eric.yummyorderdemo1.util.Constants.RequestType.REQUEST_POST_SIGNUP;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton ic_back;

    EditText edit_acco;

    EditText edit_pass;

    EditText edit_againpass;

    Button confirmsignup;

    String acc;

    String pass;

    String againpass;

    PostData postData=new PostData();

    @Override
    public void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_signup);
        setWidget();   //设置actionbar  //一定要现有layout才能有widget，记住！
        initView();
    }

    private void initView(){
        ic_back=(ImageButton)findViewById(R.id.ab_signup_icback);
        edit_acco=(EditText)findViewById(R.id.signup_username);
        edit_pass=(EditText)findViewById(R.id.signup_password);
        edit_againpass=(EditText)findViewById(R.id.signup_againpassword);
        confirmsignup=(Button)findViewById(R.id.signup_confirmsignup);

        //设置OnClick
        ic_back.setOnClickListener(this);
        confirmsignup.setOnClickListener(this);
    }

    private void setWidget(){
        //设置ActionBar
        try {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setCustomView(R.layout.actionbar_signup);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);    //displayoptions 对返回按钮，标题等显示
        }catch(Exception e){
            Log.d("Error: ",e.toString());
        }
    }


    @Override
    public void onClick(View v){
        Intent intent;
        switch(v.getId()){
            case R.id.ab_signup_icback:    //返回逻辑，返回登录页面
                intent=new Intent(SignupActivity.this, UserCenterFragment.class);
                startActivity(intent);
                break;
            case R.id.signup_confirmsignup:   //登录逻辑，若注册成功，自动返回登录页面
                acc=edit_acco.getText().toString();
                pass=edit_pass.getText().toString();
                againpass=edit_againpass.getText().toString();
                DetailSignUp();
                break;
        }
    }

    /*注册具体处理*/
    private void DetailSignUp(){
        if(!pass.equals(againpass)){    //不等返回1
            Toast.makeText(SignupActivity.this,"输入密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
        }
        else{
            postData.setAccount(acc);
            postData.setPassword(pass);
            postData.setContentType("REQUEST_POST_SIGNUP");
            SendData sendData= AccountsController.PostSignUp(postData);
            queryFromServer(sendData);
        }
    }

    private void queryFromServer(final SendData sendData){
        RequestOpt.sendOkHttpRequest(sendData,new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                Log.i("resbody: ",responseText);
                boolean result = true;
                if (result) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(sendData.ContentType==REQUEST_POST_SIGNUP){   //若为注册，显示toast
                                Toast.makeText(SignupActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(SignupActivity.this,SigninActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call call,IOException e){
                //通过runOnUiThread()回到主线程处理逻辑
                runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        Toast.makeText(SignupActivity.this,"加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
