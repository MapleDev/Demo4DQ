package com.xznn.multi_text;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.xznn.multi_text.multi_text_utils.ClickableTextViewMentionLinkOnTouchListener;
import com.xznn.multi_text.multi_text_utils.TimeLineUtility;
import com.xznn.myapplication.R;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);

        tv.setText(TimeLineUtility.convertNormalStringToSpannableString("#秋天来了#夏天就这么静悄悄的走了~~@哲匠\u200B,加油!!!www.zheblog.com", TimeLineUtility.TimeLineStatus.FEED));
        tv.setOnTouchListener(new ClickableTextViewMentionLinkOnTouchListener());

    }
}
