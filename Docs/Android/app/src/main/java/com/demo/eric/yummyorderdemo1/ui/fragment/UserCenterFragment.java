package com.demo.eric.yummyorderdemo1.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.eric.yummyorderdemo1.R;
import com.demo.eric.yummyorderdemo1.ui.activity.SigninActivity;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserCenterFragment extends Fragment implements View.OnClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("life","usercenter_onview");
        View inflate = inflater.inflate(R.layout.fragment_usercenter, container, false);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("life","usercenter_onact");

        TextView tv=(TextView)getActivity().findViewById(R.id.usercenter_signinOrsignup);
        tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.usercenter_signinOrsignup:
                Intent intent=new Intent(getActivity(),SigninActivity.class);
                startActivity(intent);
                break;
        }
    }
}
