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
import android.widget.TextView;
import android.widget.Toast;

import com.demo.eric.yummyorderdemo1.R;
import com.demo.eric.yummyorderdemo1.controller.AccountsController;
import com.demo.eric.yummyorderdemo1.model.bean.PostData;
import com.demo.eric.yummyorderdemo1.model.bean.SendData;
import com.demo.eric.yummyorderdemo1.network.RequestOpt;
import com.demo.eric.yummyorderdemo1.ui.fragment.UserCenterFragment;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.demo.eric.yummyorderdemo1.network.ResponseOpt.handleResponse;
import static com.demo.eric.yummyorderdemo1.util.Constants.RequestType.REQUEST_POST_SIGNUP;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener{
    TextView signupText;

    ImageButton ic_back;

    Button signinbutton;

    EditText account;

    EditText password;

    String acc;

    String pass;

    PostData postData=new PostData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        setWidget();   //设置组件
        initView();


    }

    private void initView(){
        ic_back=(ImageButton)findViewById(R.id.ab_signin_icback);
        signupText = (TextView) findViewById(R.id.ab_signin_nowsignup);    //立即注册;
        account=(EditText)findViewById(R.id.signin_username);
        password=(EditText)findViewById(R.id.signin_password);
        signinbutton=(Button)findViewById(R.id.signin_signin_button);

        //设置onClick
        ic_back.setOnClickListener(this);
        signinbutton.setOnClickListener(this);
    }

    private void setWidget(){
        //设置actionbar
        try {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setCustomView(R.layout.actionbar_signin);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);    //displayoptions 对返回按钮，标题等显示
        }catch(Exception e){
            Log.d("Error: ",e.toString());
        }
    }

    @Override
    public void onClick(View v){
        Intent intent;
        switch(v.getId()){
            case R.id.ab_signin_icback:
                intent=new Intent(SigninActivity.this,UserCenterFragment.class);
                startActivity(intent);
                break;
            case R.id.ab_signin_nowsignup:
                intent=new Intent(SigninActivity.this,SignupActivity.class);
                startActivity(intent);
            case R.id.ab_signin_signin:
                acc=signupText.getText().toString();
                pass=password.getText().toString();
                DetailSignIn();
                break;
        }
    }

    private void DetailSignIn(){
        postData.setAccount(acc);
        postData.setPassword(pass);
        SendData sendData= AccountsController.PostSignin(postData);
        queryFromServer(sendData);
    }

    private void queryFromServer(final SendData sendData){
        RequestOpt.sendOkHttpRequest(sendData,new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                Log.i("resbody: ",responseText);
                boolean result = handleResponse(responseText,sendData.ContentType);
                if (result) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(sendData.ContentType==REQUEST_POST_SIGNUP){   //若为注册，显示toast
                                Toast.makeText(SigninActivity.this,"登录",Toast.LENGTH_SHORT).show();
//
//                                Intent intent=new Intent(SigninActivity.this,);  //新的用户页面，更新Fragment？
//                                startActivity(intent);
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
                        Toast.makeText(SigninActivity.this,"加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
