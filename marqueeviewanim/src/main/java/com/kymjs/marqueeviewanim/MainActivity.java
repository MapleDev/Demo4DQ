package com.kymjs.marqueeviewanim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    HighLevelCommingMarqueeView mHighLevelCommingMarqueeView;
    HignLevelAnim mHignLevelAnim ;
    private Button mButton;
    private int cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHighLevelCommingMarqueeView = (HighLevelCommingMarqueeView) findViewById(R.id.highLevelCommingMarqueeView);
        mButton = (Button) findViewById(R.id.button);

        mHignLevelAnim = new HignLevelAnim(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHignLevelAnim.addHignLevelAnim(new UserInfo("我是 跑马灯" + cnt++));
            }
        });
    }

}
