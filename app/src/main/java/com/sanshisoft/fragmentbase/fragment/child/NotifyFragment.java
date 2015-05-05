package com.sanshisoft.fragmentbase.fragment.child;

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

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by chenleicpp on 2015/5/4.
 */
public class NotifyFragment extends Fragment {

    private MainActivity mMainContainer = null;
    @InjectView(R.id.btn_detail)
    Button mBtnDetail;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMainContainer = (MainActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notify,container,false);
        mMainContainer.showLeftAndTop();
        ButterKnife.inject(this,view);
        return view;
    }

    @OnClick(R.id.btn_detail)
    public void doDetail(){
        mMainContainer.setRightFragment(new NotifyDetailFragment());
    }
}
