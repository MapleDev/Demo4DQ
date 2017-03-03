package com.xznn.keng;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

// Android编程容易犯的错误之一 - 梦书 - 博客园 - http://www.cnblogs.com/mengshu-lbq/p/3372261.html
public class MainActivity extends AppCompatActivity {

    LinearLayout activity_main;
    TextView tv_0;
    ImageView iv_0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_0 = (TextView)findViewById(R.id.tv_0);
        iv_0 = (ImageView)findViewById(R.id.iv_0);
        activity_main = (LinearLayout)findViewById(R.id.activity_main);

        int color = ContextCompat.getColor(this, R.color.colorAccent);
        tv_0.setTextColor(color);

        iv_0.setImageResource(R.mipmap.ic_launcher);

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.crayzoom);
        iv_0.setImageDrawable(drawable);

        activity_main.addview

    }
}
