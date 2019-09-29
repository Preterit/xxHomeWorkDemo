package com.xiangxue.rx.rx_activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.xiangxue.xxhomeworkdemo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

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
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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

    public void flatmap() {
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
            return Observable.fromIterable(list);
        }).subscribe(s -> Log.e(TAG, s));
    }

    public void concatmap() {

    }

    public void buffer() {

    }

    public void flowabledemo() {

    }

    public void retry() {

    }

    public void retryWhen() {

    }
}
