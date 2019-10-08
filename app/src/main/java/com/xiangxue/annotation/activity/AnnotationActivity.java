package com.xiangxue.annotation.activity;

import android.os.Bundle;

import com.xiangxue.xxhomeworkdemo.R;

import androidx.appcompat.app.AppCompatActivity;

public class AnnotationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
    }


    public static void main(String[] args) {
    }
}
