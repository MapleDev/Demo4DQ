package com.xznn.marqueeviewanim;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ywy on 2016/4/15.
 */
public class DeviceUtils {

    /**
     * dp2px
     */
    public static int dipToPixel(float dp, Context mContext)
    {
        float scale = mContext.getResources().getDisplayMetrics().density;
        int pixel = (int) (dp * scale + 0.5f);
        return pixel;
    }

    public static int getScreenWidth(Context mContext) {
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * setting margins
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
}
