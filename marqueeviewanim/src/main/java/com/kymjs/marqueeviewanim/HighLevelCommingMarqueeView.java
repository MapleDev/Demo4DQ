package com.kymjs.marqueeviewanim;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * @author MapleDev
 * @time 16/09/26  20:40
 * @desc ${TODD}
 */
public class HighLevelCommingMarqueeView extends FrameLayout {

    private Context context;
    private TextView mTv;

    public HighLevelCommingMarqueeView(Context context) {
        this(context, null);
    }

    public HighLevelCommingMarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(context).inflate(R.layout.high_level_comming_marquee, this);
        mTv = (TextView) findViewById(R.id.tv);
    }

    public void setTextContent(String text){
        mTv.setText(text);
    }

    public TextView getTv() {
        return mTv;
    }

}
