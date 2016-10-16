package com.kymjs.marqueeviewanim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by sym on 4/27/16.
 * 自定义跑马灯View
 */
public class MarqueeView extends View {

    private boolean DEBUG = true;

    private static int sBaseSpeed = 3;

    private float mFactor = 2;

    private CharSequence mContent = new SpannableString("呵呵");
    private int mCurrX = 500;
    private int mCurrY;
    private StaticLayout staticLayout;
    private StaticLayout borderStaticLayout;

    private int mContentHeight;
    private int mConentWidth;
    private int mTextColor = Color.WHITE;
    private int mRepeat = 2;

//    private float mTextSize = LocalDisplay.px2dp(300);
    private float mTextSize = 300;

    private final int STATUS_RUNNING = 1;
    private final int STATUS_STOP = 2;

    private int status = STATUS_STOP;

    private static TextPaint strokePaint = new TextPaint();

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //measure();
//        BusManager.getIntance().register(this);
    }

    public MarqueeView(Context context) {
        this(context, null);
    }


    /**
     * 测量
     */
    private void measure() {

        TextPaint tp = new TextPaint();
        tp.setAntiAlias(true);
        tp.setColor(mTextColor);
        tp.setTextSize(mTextSize);
        strokePaint.setTextSize(mTextSize);
        mContentHeight = getFontHeight(tp);
        staticLayout = new StaticLayout(mContent,
                tp,
                (int) Layout.getDesiredWidth(mContent, 0, mContent.length(), tp) + 1,
                Layout.Alignment.ALIGN_NORMAL,
                1.0f,
                0.0f,
                false);
        mConentWidth = staticLayout.getWidth();
        borderStaticLayout = new StaticLayout(mContent,
                strokePaint,
                (int) Layout.getDesiredWidth(mContent, 0, mContent.length(), tp) + 1,
                Layout.Alignment.ALIGN_NORMAL,
                1.0f,
                0.0f,
                false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasuredHeight();
        //mCurrY = height / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (status == STATUS_RUNNING) {
            canvas.save();
            canvas.translate(mCurrX, mCurrY);
            borderStaticLayout.draw(canvas);
            staticLayout.draw(canvas);
            canvas.restore();
            mCurrX = (int) (mCurrX - sBaseSpeed * mFactor);
            if (isOut()) {
                reset();
            }

            if (mContent != null)
                invalidate();
        }
    }

    /**
     * 重置
     */
    private void reset() {
//        mCurrX = LocalDisplay.SCREEN_WIDTH_PIXELS;
        mRepeat--;
        if (mRepeat == 0) {
            hide();
        }
    }

    /**
     * 隐藏
     */
    public void hide() {
        status = STATUS_STOP;
        this.setVisibility(View.GONE);
        release();
    }

    /**
     * 判断文字是否播放完成
     *
     * @return
     */
    private boolean isOut() {
        return mCurrX < 0 && Math.abs(mCurrX) > mConentWidth;
    }

    /**
     * 获得字体的高度
     *
     * @param paint
     * @return
     */
    private int getFontHeight(TextPaint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) (Math.ceil(fm.descent - fm.top) + 2);
    }

    /**
     * 显示跑马灯
     */
    public MarqueeView show() {
        setVisibility(View.VISIBLE);
        status = STATUS_RUNNING;
        invalidate();
        return this;
    }

    /**
     * 添加跑马灯内容
     */
    public MarqueeView addContent(CharSequence content) {
        this.mContent = content;
        setVisibility(View.VISIBLE);
        measure();
        return this;
    }

    /**
     * 设置弹幕速率
     *
     * @param factor
     * @return
     */
    public MarqueeView setSpeedFactor(float factor) {
        this.mFactor = factor;
        return this;
    }

    /**
     * 释放
     */
    public void release() {
        mContent = null;
    }

    /**
     * 停止跑马灯
     */
    public void stop() {
        status = STATUS_STOP;
    }

    /**
     * 设置X轴开始的位置
     *
     * @param startX
     * @return
     */
    public MarqueeView setCurrX(int startX) {

        this.mCurrX = startX;
        return this;
    }

    /**
     * 设置Y轴开始的位置
     *
     * @param startY
     * @return
     */
    public MarqueeView setCurrY(int startY) {
        this.mCurrY = startY;
        return this;
    }

    /**
     * 设置文字的颜色
     *
     * @param color
     * @return
     */
    public MarqueeView setFontColor(int color) {
        this.mTextColor = color;
        measure();
        return this;
    }

    /**
     * 设置跑马灯重复次数
     *
     * @param repeat
     * @return
     */
    public MarqueeView setRepeat(int repeat) {
        this.mRepeat = repeat;
        return this;
    }

    /**
     * 设置字体大小
     *
     * @param textSize
     * @return
     */
    public MarqueeView setTextSize(int textSize) {
        this.mTextSize = textSize;
        measure();
        return this;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        BusManager.getIntance().unregister(this);
    }

    /**
     * 跑马灯
     */
//    @Subscribe
//    public void onBannerNotice(BannerNotice notice) {
//
//        try {
//            if (null != notice.content) {
//
//                JSONArray jsonArray = new JSONArray(notice.content);
//                String result = "";
//                if (jsonArray.length() > 0) {
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//
//                        JSONObject opt = (JSONObject) jsonArray.opt(0);
//                        result = result + spilt(opt.optString("color"), opt.optString("txt"));
//                    }
//                }
//
//                this.addContent(Html.fromHtml(result))
//                        .setRepeat(notice.count)
//                        .setSpeedFactor(0.7f)
//                        .setCurrY((int) LocalDisplay.px2dp(15f))
//                        .setTextSize((int) LocalDisplay.sp2px(15))
//                        .show();
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 拼接
     *
     * @return
     */
    private String spilt(String str, String content) {

        String color = "";
        if (str.equals("y")) {

            color = "#FFFF00";
        } else if (str.equals("g")) {

            color = "#C0FF3E";
        } else if (str.equals("r")) {

            color = "#FF0000";
        }

        return "<font color=\"" + color + "\">" + content + " </font > ";
    }
}
