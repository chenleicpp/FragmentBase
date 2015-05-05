package com.sanshisoft.fragmentbase.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sanshisoft.fragmentbase.MainActivity;
import com.sanshisoft.fragmentbase.R;
import com.sanshisoft.fragmentbase.Utils.CommenUtils;
import com.sanshisoft.fragmentbase.fragment.child.InteractFragment;
import com.sanshisoft.fragmentbase.fragment.child.NotifyFragment;
import com.sanshisoft.fragmentbase.fragment.child.SettingsFragment;
import com.sanshisoft.fragmentbase.fragment.child.UserinfoFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by chenleicpp on 2015/5/4.
 */
public class LeftFragment extends Fragment {

    @InjectView(R.id.ll_leftfragment_userinfo)
    RelativeLayout mUserInfo;
    @InjectView(R.id.ll_leftfragment_interact)
    RelativeLayout mInteract;
    @InjectView(R.id.ll_leftfragment_notify)
    RelativeLayout mNotify;
    @InjectView(R.id.ll_leftfragment_changepwd)
    RelativeLayout mChangePwd;

    private MainActivity mMainContainer;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMainContainer = (MainActivity) activity;
        registerBoradcastReceiver();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left,container,false);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        unregisterBoradcastReceiver();
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String name = intent.getStringExtra("name");
            if (CommenUtils.isNotBlank(name) && action.equals(CommenUtils.ACTION_DISPLAY)) {
                Log.i(CommenUtils.TAG, "Get Broadcast:" + name);
                if (name.equals("UserinfoFragment")) {
                    mUserInfo.setSelected(true);
                    mInteract.setSelected(false);
                    mNotify.setSelected(false);
                    mChangePwd.setSelected(false);
                } else if (name.equals("SettingsFragment")) {
                    mUserInfo.setSelected(false);
                    mInteract.setSelected(false);
                    mNotify.setSelected(false);
                    mChangePwd.setSelected(true);
                } else if (name.equals("NotifyFragment") || name.equals("NotifyDetailFragment")) {
                    mUserInfo.setSelected(false);
                    mInteract.setSelected(false);
                    mNotify.setSelected(true);
                    mChangePwd.setSelected(false);
                } else if (name.equals("InteractFragment")) {
                    mUserInfo.setSelected(false);
                    mInteract.setSelected(true);
                    mNotify.setSelected(false);
                    mChangePwd.setSelected(false);
                } else {
                    mUserInfo.setSelected(false);
                    mInteract.setSelected(false);
                    mNotify.setSelected(false);
                    mChangePwd.setSelected(false);
                }
            }
        }
    };

    @OnClick({R.id.ll_leftfragment_userinfo, R.id.ll_leftfragment_interact, R.id.ll_leftfragment_notify,R.id.ll_leftfragment_changepwd})
    public void onLeftClick(View v) {
        switch (v.getId()) {
            case R.id.ll_leftfragment_userinfo :
                mMainContainer.setRightFragment(new UserinfoFragment());
                break;
            case R.id.ll_leftfragment_interact :
                mMainContainer.setRightFragment(new InteractFragment());
                break;
            case R.id.ll_leftfragment_notify :
                mMainContainer.setRightFragment(new NotifyFragment());
                break;
            case R.id.ll_leftfragment_changepwd :
                mMainContainer.setRightFragment(new SettingsFragment());
                break;
            default :
                break;
        }

    }

    public void registerBoradcastReceiver() {
        IntentFilter displayIntentFilter = new IntentFilter();
        displayIntentFilter.addAction(CommenUtils.ACTION_DISPLAY);
        mMainContainer.registerReceiver(broadcastReceiver, displayIntentFilter);
    }

    public void unregisterBoradcastReceiver() {
        mMainContainer.unregisterReceiver(broadcastReceiver);
    }
}
