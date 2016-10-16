package com.xznn.fragmenttabhost_tu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements View.OnClickListener
{

    private FragmentTabHost mTabHost;
    private Class[] clas = new Class[] { HomeFragment.class, HomeFragment.class, HomeFragment.class, HomeFragment.class,
            HomeFragment.class };
    private int images[] = new int[] {R.mipmap.ic_launcher, R.mipmap.ic_launcher,1, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher };
    private TextView mBottom_center;
    private ImageView main_image_center;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    public void initUI()
    {
        //底部中间按钮控件
        main_image_center = (ImageView) findViewById(R.id.main_image_center);
        main_image_center.setImageResource(R.mipmap.ic_launcher);
        mBottom_center = (TextView) findViewById(R.id.main_tv_final);
        main_image_center.setOnClickListener(this);
        mBottom_center.setOnClickListener(this);
//        String[] tabIndicatorArray = getResources().getStringArray(R.array.arr_tab_indicator);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        LayoutInflater inflater = getLayoutInflater();
        for (int i = 0; i < images.length; i++) {
//            View indicatorView = inflater.inflate(R.layout.g_list_item_viewpagerindicator, null);
//            TextView tvIndicator = (TextView) indicatorView.findViewById(R.id.tv_title_indicator);
//            tvIndicator.setText(tabIndicatorArray[i]);
//            ImageView imageView = (ImageView) indicatorView.findViewById(R.id.ima_indicator);
//            imageView.setImageResource(images[i]);
            //tabhost添加tab切换事件
//            mTabHost.addTab(mTabHost.newTabSpec("tab"+i).setIndicator(indicatorView), clas[i], null);
            mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

                @Override
                public void onTabChanged(String tabId) {
                    switch (tabId)
                    {
                        case "tab2":
                            main_image_center.setImageResource(R.mipmap.ic_launcher);
                            mBottom_center.setTextColor(Color.parseColor("#ffd38a"));
                            break;
                        default:
                            main_image_center.setImageResource(R.mipmap.ic_launcher);
                            mBottom_center.setTextColor(Color.parseColor("#b2b2b2"));
                            break;
                    }

                }
            });

        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.main_image_center:
                mTabHost.setCurrentTab(2);
                break;
            default:
                break;
        }
    }
}
