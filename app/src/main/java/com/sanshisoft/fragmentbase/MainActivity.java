package com.sanshisoft.fragmentbase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;

import com.sanshisoft.fragmentbase.Utils.AppManager;
import com.sanshisoft.fragmentbase.Utils.CommenUtils;
import com.sanshisoft.fragmentbase.fragment.LoginFragment;
import com.sanshisoft.fragmentbase.fragment.child.UserinfoFragment;

import java.util.Stack;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends FragmentActivity {

    @InjectView(R.id.ll_left)
    View mLeftContainer;
    @InjectView(R.id.ll_top)
    View mTopContainer;

    private HFragmentManager mFragMgr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        AppManager.getAppManager().addActivity(this);
        //初始化过程中设置了root fragment
        mFragMgr = HFragmentManager.instance(this, R.id.fg_right_container, UserinfoFragment.class.getSimpleName());
        if (savedInstanceState != null) {
            initFromState(savedInstanceState);
        }
        displayMainFragment(new LoginFragment());
    }

    /* 从状态中初始化 */
    private void initFromState(Bundle savedInstanceState) {
        Stack<String> data = mFragMgr.getCacheStack();
        String[] saved = savedInstanceState.getStringArray("stack");
        if (saved != null) {
            for (int i = 0; i < saved.length; i++) {
                data.add(saved[i]);
            }
        } else {
            mFragMgr.setCacheStack();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveToState(outState);
    }

    /* 保存到状态 */
    private void saveToState(Bundle outState) {
        int len = mFragMgr.getCacheStack().size();
        String[] data = new String[len];
        outState.putStringArray("stack", mFragMgr.getCacheStack().toArray(data));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mFragMgr != null) {
            mFragMgr.recycle();
        }
    }

    protected void displayMainFragment(Fragment fragment) {
        mFragMgr.display(fragment);
        mFragMgr.clearExceptCurrent();
        resetFragment();
    }

    protected void displayMainFragment(Fragment fragment, boolean restore) {
        mFragMgr.display(fragment, restore);
        mFragMgr.clearExceptCurrent();
        resetFragment();
    }

    public void resetFragment() {
        dimissKeyboard();
        showLeft();
    }

    private void showLeft() {
        mLeftContainer.setVisibility(View.VISIBLE);
    }

    public void hideLeft() {
        mLeftContainer.setVisibility(View.GONE);
    }

    public void showLeftAndTop() {
        mLeftContainer.setVisibility(View.VISIBLE);
        mTopContainer.setVisibility(View.VISIBLE);

    }

    public void showTop() {
        mTopContainer.setVisibility(View.VISIBLE);
    }

    public void hideLeftAndTop() {
        mLeftContainer.setVisibility(View.GONE);
        mTopContainer.setVisibility(View.GONE);
    }

    private void dimissKeyboard() {
    }

    /**
     * 设置右侧界面
     *
     * @param newRight Fragment子类
     */
    public void setRightFragment(Fragment newRight) {
        setRightFragment(newRight, null, null, true);
    }

    /**
     * 设置右侧界面
     *
     * @param newRight Fragment子类
     */
    public void setRightFragment(Fragment newRight, boolean restore) {
        setRightFragment(newRight, null, null, restore);
    }

    /**
     * 设置右侧界面
     *
     * @param newRight Fragment子类
     */
    public void setRightFragment(Fragment newRight, Bundle args) {
        setRightFragment(newRight, null, args, false);
    }

    /**
     * 设置右侧界面
     *
     * @param newRight Fragment子类
     */
    public void setRightFragment(Fragment newRight, Bundle args, boolean restore) {
        setRightFragment(newRight, null, args, restore);
    }

    /**
     * 设置右侧界面
     *
     * @param newRight Fragment子类
     */
    public void setRightFragment(Fragment newRight, String tag) {
        setRightFragment(newRight, tag, null, false);
    }

    /**
     * 设置右侧界面
     *
     * @param newRight Fragment子类
     */
    public void setRightFragment(Fragment newRight, String tag, Bundle args, boolean restore) {
        int which = -1;
        if (args != null) {
            // 附加参数
            newRight.setArguments(args);
            which = args.getInt("which", -1);
        }
        mFragMgr.display(newRight, tag, restore);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void goBack() {
        String display = mFragMgr.popBackStack();
        if (CommenUtils.isNotBlank(display)) {
            showLeft();
            showLeftAndTop();
        } else {
            CommenUtils.exit(this);
        }
    }
}
