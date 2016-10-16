package com.kymjs.lambdatest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1.2使用 lambda expression
        new Thread(() -> System.out.println("Hello world !")).start();

        new Thread( () ->
            Log.d("DDD","====== run() ======")

        ).start();

    }

}
