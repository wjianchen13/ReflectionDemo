package com.example.reflectiondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 通过对象取得包名和类名
     * @param v
     */
    public void onTest1(View v) {
        Test t = new Test();
        println(t.getClass());
        println(t.getClass().getName());
    }

    /**
     * Class类的实例化
     * @param v
     */
    public void onTest2(View v) {
        //方式一：
        Test t = new Test();
        Class<? extends Test> c1 = t.getClass();
        println(c1);

        //方式二：
        //为了避免特殊性，这里不用Test类，而用java库中的String类
        Class<String> c2 = String.class;
        println(c2);

        //方式三：
        //forName()方法会抛出异常
        Class<?> c3 = null;
        try {
            c3 = Class.forName("com.example.reflectiondemo.Test");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        println(c3);
    }
    
    private void println(Object str) {
        System.out.println("==============================> " + str);
    }
}