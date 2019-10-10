package com.demo.mvp_dagger2.ui;

import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.demo.mvp_dagger2.R;
import com.demo.mvp_dagger2.base.BaseMvpFragment;
import com.demo.mvp_dagger2.network.bean.ProjectBean;
import com.demo.mvp_dagger2.ui.register.RegisterPresenter;
import com.demo.mvp_dagger2.ui.register.RegisterView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author :  lwb
 * Date: 2019/10/10
 * Desc:
 */
public class HomeFragment extends BaseMvpFragment<RegisterPresenter> implements RegisterView {

    private static final String TAG = "HomeFragment";

    @BindView(R.id.btn_click)
    Button btnClick;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void ComponentInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_login;
    }


    @OnClick(R.id.btn_click)
    public void onViewClicked() {
        mPresenter.showMsg("button被点击了");
    }

    @Override
    public void showData(ProjectBean bean) {
        Toast.makeText(getContext(), "请求成功了", Toast.LENGTH_SHORT).show();
        Log.e(TAG, bean.toString());
    }
}
