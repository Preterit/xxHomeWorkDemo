package com.xiangxue.rx.rx_activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.xiangxue.rx.bean.ProjectBean;
import com.xiangxue.rx.rx_apiservice.WanAndroidApi;
import com.xiangxue.util.HttpUtil;
import com.xiangxue.xxhomeworkdemo.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OtherActivity extends AppCompatActivity {

    //字符串组合
    private String[] data = {
            "map",
            "flatmap",
            "concatmap",
            "buffer",
            "retry",
            "retryWhen",};

    private static final String TAG = "OtherActivity";

    @BindView(R.id.listview)
    ListView listview;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        ButterKnife.bind(this);

        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, data);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener((adapterView, view, i, l) -> {
            switch (i) {
                case 0:
                    map();
                    break;
                case 1:
                    flatmap();
                    break;
                case 2:
                    concatmap();
                    break;
                case 3:
                    buffer();
                    break;
                case 4:
                    retry();
                    break;
                case 5:
                    retryWhen();
                    break;
            }
        });
    }

    //  lambda
    public void map() {
        Observable.create(emitter -> {
            // 1. 被观察者发送事件 = 参数为整型 = 1、2、3
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
        })
                .map(integer -> {
                    // 2. 使用Map变换操作符中的Function函数对被观察者发送的事件进行统一变换：整型变换成字符串类型
                    return "" + integer + "_map";
                })
                .subscribe(s -> {
                    // 3. 观察者接收事件时，是接收到变换后的事件 = 字符串类型
                    Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();
                });

    }

    public void flatmap() {   //  并行无序,一般用于输出一个Observable
        Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
        }).flatMap(integer -> {
            final List<String> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                // 最终合并，再发送给被观察者
                list.add(integer + "_flatmap_" + i);
            }
            return Observable.fromIterable(list)
                    .subscribeOn(Schedulers.newThread());
        }).subscribe(s -> Log.e(TAG, s));
    }

    public void concatmap() {      // concatMap()和flatMap()很像，但串行有序
        Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
        }).concatMap(integer -> {
            final List<String> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                // 通过concatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                // 最终合并，再发送给被观察者
                list.add(integer + "_concatmap_" + i);
            }
            return Observable.fromIterable(list)
                    .subscribeOn(Schedulers.newThread());
        }).subscribe(s -> Log.e(TAG, s));
    }

    // 被观察者 需要发送5个数字
    public void buffer() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8)
                .buffer(3, 1)  // 设置缓存区大小 &  步长
                // 缓存区大小 == 每次从被观察者中获取的时间数量
                // 步长  ==  每次获取新事件的数量
                .subscribe(
                        integers -> {
                            Log.e(TAG, " 缓存区里的事件数量 = " + integers.size());
                            for (Integer value : integers) {
                                Log.e(TAG, " 事件 = " + value);
                            }
                        },
                        e -> Log.e(TAG, "对Complete事件作出响应"),
                        () -> Log.e(TAG, "对Complete事件作出响应")
                );

    }

    public void flowabledemo() {

    }

    public void retry() {
        int count = 0;
        WanAndroidApi androidApi = HttpUtil.getOnlineCookieRetrofit().create(WanAndroidApi.class);
        androidApi.getProject()
                .retry(throwable -> {
                    if (count < 4) {
                        Log.i(TAG, "重试： " + count);
                        return true;
                    }
                    return false;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(projectBean -> Log.e(TAG, "retry: "));
    }


    // 可重复次数
    int maxConnectCount = 10;
    //当前已重复次数
    int currentRetryCount = 0;
    //重试等待时间
    int waitRetryTime = 0;

    public void retryWhen() {

        WanAndroidApi wanAndroidApi = HttpUtil.getOnlineCookieRetrofit().create(WanAndroidApi.class);
        Observable<ProjectBean> observable = wanAndroidApi.getProject();
        // 步骤4 ：发送网络请求 & 通过retryWhen() 进行重试
        // 注 ： 主要异常 才会retryWhen() 进行重试
        observable.retryWhen(
                // 参数 Observable<Throwable> 中泛型 = 上游操作符抛出的异常，可通过该异常条件来判断异常的类型
                throwableObservable -> {
                    return throwableObservable.flatMap(throwable -> {

                        //输出异常信息
                        Log.e(TAG, "" + throwable.toString());

                        /**
                         * 需求1 : 根据异常类型选择是否要重试
                         * 即 :当发生的异常 = 网络异常 = IO异常 才选择重试
                         */
                        if (throwable instanceof IOException) {
                            /**
                             * 需求2 :限制重试次数
                             * 即 : 当已重复次数 < 设置的重复次数, 才选择重试
                             */
                            if (currentRetryCount < maxConnectCount) {
                                // 记录重复次数
                                currentRetryCount++;
                                Log.e(TAG, "重复次数 : " + currentRetryCount);
                                /**
                                 * 需求 2 : 实现重试
                                 * 通过放回的Observable发送的事件 = next()事件,从而使得retryWhen() 重新订阅,最终实现重试的功能.
                                 *
                                 * 需求 3 : 延时一段时间再重试
                                 * 采用delay操作符 = 延迟一段时间发送,以实现重试间隔设置
                                 *
                                 * 需求 4 : 遇到的异常越多,时间越长
                                 * 在delay操作符的等待时间设置 = 没重试一次,增多延迟重试时间1s
                                 */

                                // 设置等待时间
                                waitRetryTime = 1000 + currentRetryCount * 1000;
                                Log.e(TAG, "等待时间 = " + waitRetryTime);
                                return Observable.just(1).delay(waitRetryTime, TimeUnit.MILLISECONDS);
                            } else {
                                // 若重试次数 > 设置的重复次数,则不重试
                                // 通过发送error来停止重试,(可在观察者的onError()中获取信息)
                                Observable<Object> error = Observable.error(new Throwable("重试次数已超过设置的次数" + currentRetryCount + ", 即 不在重试"));
                                return error;
                            }
                        }
                        // 若发生的异常不属于I/O异常,则不重试
                        // 通过返回的ObserVable发送的事件 = Error事件 实现  ( 可在观察者的onError()中获取到信息 )
                        else {
                            Observable<Object> error = Observable.error(new Throwable("发生了非网络异常 (非 I/O 异常)"));
                            return error;
                        }
                    });
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        projectBean -> Log.e(TAG, "发送成功")// 接收服务器返回的数据
                        ,
                        e -> Log.e(TAG, e.toString()));
    }
}
