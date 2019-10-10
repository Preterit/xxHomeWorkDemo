package com.demo.mvp_dagger2.base;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.mvp_dagger2.utils.StatusBarUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author :  lwb
 * Date: 2019/10/10
 * Desc:
 */
public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(layoutId(), container, false);
        //绑定到butterknife
        mUnbinder = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    public void statusBar(boolean isDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//Android6.0以上系统
            StatusBarUtil.setDarkStatusIcon(getActivity().getWindow(), isDark);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mRootView = null;
        this.mUnbinder = null;
    }

    public abstract int layoutId();

    public abstract void initData();

    public abstract void initView();

}
