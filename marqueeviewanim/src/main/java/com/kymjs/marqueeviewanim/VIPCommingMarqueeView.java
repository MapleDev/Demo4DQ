package com.kymjs.marqueeviewanim;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author MapleDev
 * @time 16/09/26  20:40
 * @desc ${TODD}
 */
public class VIPCommingMarqueeView extends FrameLayout {

    private int screenWidth;//屏幕宽度
    private Context context;
    private RelativeLayout mainLayout;//跑马灯滚动部分
    private TextView mTv;

    public VIPCommingMarqueeView(Context context) {
        this(context, null);
    }

    public VIPCommingMarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);


        this.context = context;
        initView();
    }

    private void initView() {

        LayoutInflater.from(context).inflate(R.layout.vip_comming_marquee, this);
        screenWidth = DeviceUtils.getScreenWidth(context);
        mTv = (TextView) findViewById(R.id.tv);
    }

    public void setTextCont(String text){
        mTv.setText(text);
    }


}
