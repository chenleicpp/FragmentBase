package com.sanshisoft.fragmentbase.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sanshisoft.fragmentbase.MainActivity;
import com.sanshisoft.fragmentbase.R;
import com.sanshisoft.fragmentbase.fragment.child.UserinfoFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by chenleicpp on 2015/5/4.
 */
public class LoginFragment extends Fragment {

    private MainActivity mMainContainer;

    @InjectView(R.id.btn_login)
    Button mBtnLogin;
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMainContainer = (MainActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        ButterKnife.inject(this, view);
        mMainContainer.hideLeftAndTop();
        return view;
    }

    @OnClick(R.id.btn_login)
    public void doLogin(){
        mMainContainer.setRightFragment(new UserinfoFragment());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
