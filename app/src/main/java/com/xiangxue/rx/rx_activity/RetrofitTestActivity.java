package com.xiangxue.rx.rx_activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.xiangxue.rx.bean.ProjectItem;
import com.xiangxue.rx.rx_apiservice.WanAndroidApi;
import com.xiangxue.util.HttpUtil;
import com.xiangxue.util.RxUtils;
import com.xiangxue.xxhomeworkdemo.R;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RetrofitTestActivity extends AppCompatActivity {

    private static final String TAG = "RetrofitTestActivity";
    private WanAndroidApi wanAndroidApi;
    private WanAndroidApi androidApi;
    private Disposable disposable;
    int j = 0;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);
        Button btn1 = findViewById(R.id.btn1);
        findViewById(R.id.btn2).setOnClickListener(v -> getProject());
        findViewById(R.id.btn3).setOnClickListener(v -> getProjectItem());
        wanAndroidApi = HttpUtil.getOnlineCookieRetrofit().create(WanAndroidApi.class);

//        btn1.setOnClickListener(v -> getProjectItems());

        /**
         * 1 : 网络请求嵌套
         * 2 : 功能防抖
         *
         *  // TODO  不知道为啥用subscribeOn切换子线程操作就会崩溃
         *
         */

        RxView.clicks(btn1)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .flatMap(o -> {
                    Log.e(TAG, Thread.currentThread().getName());
                    return wanAndroidApi.getProject();
                })
                .flatMap(projectBean -> Observable.fromIterable(projectBean.getData()))
                .flatMap(projectItem -> wanAndroidApi.getProjectItem(1, projectItem.getId()))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectItem>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProjectItem projectItem) {
                        j += 1;
                        Log.e(TAG, "caonima");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "xfxfxfxfxfxfxfxf"+j);
                    }
                });
    }

    int i = 0;

    public void getProjectItems() {
        wanAndroidApi.getProject()
                .flatMap(projectBean -> Observable.fromIterable(projectBean.getData()))
                .flatMap(dataBean -> wanAndroidApi.getProjectItem(1, dataBean.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectItem>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProjectItem projectItem) {
                        i += 1;
                        Log.e(TAG, "" + projectItem);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "lalalalalalalalalalaa" + i);
                    }
                });

    }

    public void getProject() {
        wanAndroidApi.getProject()
                .compose(RxUtils.io_main())
                .subscribe(projectBean -> Log.i(TAG, "projectBean: " + projectBean));
    }

    public void getProjectItem() {
        disposable = androidApi.getProjectItem(1, 294)
                .compose(RxUtils.io_main())
                .subscribe(projectItem -> Log.i(TAG, "projectBeanItem: " + projectItem));
    }

}
