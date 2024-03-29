package com.xiangxue.xxhomeworkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xiangxue.annotation.activity.AnnotationActivity;
import com.xiangxue.rx.rx_activity.FilterActivity;
import com.xiangxue.rx.rx_activity.GetCodeActivity;
import com.xiangxue.rx.rx_activity.OtherActivity;
import com.xiangxue.rx.rx_activity.RetrofitLoginActivity;
import com.xiangxue.rx.rx_activity.RetrofitTestActivity;
import com.xiangxue.viewpager2.ViewPager2DemoActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //字符串组合
    private String[] data = {
            "retrofit网络测试",
            "注册/登录",
            "GetCodeActivity",
            "FilterActivity",
            "OtherActivity",
            "dagger2",
            "ViewPager2Demo"
    };
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview = findViewById(R.id.listview);


        /**
         * 通过ArrayAdapter 将数据和布局联系起来
         * 参数1 当前上下文环境
         * 参数2 当前引用的布局 一般系统默认
         * 参数3 当前绑定的数据
         * */
        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, data);

        //将数据和布局 显示到列表
        listview.setAdapter(adapter);

        listview.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    startActivity(new Intent(MainActivity.this, RetrofitTestActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(MainActivity.this, RetrofitLoginActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this, GetCodeActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(MainActivity.this, FilterActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(MainActivity.this, OtherActivity.class));
                    break;
                case 5:
                    startActivity(new Intent(MainActivity.this, AnnotationActivity.class));
                    break;
                case 6:
                    startActivity(new Intent(MainActivity.this, ViewPager2DemoActivity.class));
                    break;
                default:
                    break;
            }
        });
    }

}
