package com.sanshisoft.fragmentbase.fragment.child;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanshisoft.fragmentbase.MainActivity;
import com.sanshisoft.fragmentbase.R;

/**
 * Created by chenleicpp on 2015/5/4.
 */
public class InteractFragment extends Fragment {

    private MainActivity mMainContainer = null;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMainContainer = (MainActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainContainer.showLeftAndTop();
        return inflater.inflate(R.layout.fragment_interact,container,false);
    }
}
