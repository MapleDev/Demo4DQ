package com.kymjs.marqueeviewanim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.plattysoft.leonids.ParticleSystem;

import java.util.LinkedList;

/**
 * Created by Administrator on 2016/10/28 0028.
 * 队列动画,队列中的动画一个一个地显示
 */
public class HignLevelAnim {

    private static final String TAG = "HignLevelAnim";

    private LinkedList<UserInfo> linkedList;  //动画队列
    MainActivity mActivity;
    public boolean isAnimRunning;
    private Object lock = new Object();


    public HignLevelAnim(MainActivity liveInFragment){
        mActivity = liveInFragment;
        linkedList = new LinkedList<>();
//        linkedList.clear();
    }

    // 高等级进入 动画
    public void addHignLevelAnim(UserInfo userInfo) {
        synchronized (linkedList) {
            linkedList.add(userInfo);
        }
        showHignLevelAnim();
    }

    private void showHignLevelAnim() {

        Log.w(TAG, "ChatUtil{} ... showHignLevelAnim() --> isAnimRunning = " + isAnimRunning);
        Log.w(TAG, "ChatUtil{} ... showHignLevelAnim() --> linkedList.size() = " + linkedList.size());

        if (isAnimRunning) return;

        synchronized (lock) {
            if (linkedList.size() > 0) {
                UserInfo userInfo = linkedList.getFirst();
//                if (userInfo == null) {
                linkedList.removeFirst();
//                    return;
//                }

                //------------------------------------------------
                if (mActivity == null) return;
                final HighLevelCommingMarqueeView view = mActivity.mHighLevelCommingMarqueeView;
                if (view == null) return;

                TextView textView = view.getTv();
                textView.setText(userInfo.nickname + " 进入了房间 ");
                textView.setShadowLayer(3, 3, 3, Color.parseColor("#80000000"));
//                textView.setCompoundDrawablePadding(UIUtil.dip2px(mActivity.getActivity(), 2));

                int screenWidth = DeviceUtils.getScreenWidth(mActivity); // 屏幕宽度

                // 动画效果
                final ObjectAnimator animIn = ObjectAnimator.ofFloat(view, "translationX", screenWidth, screenWidth / 12);
                final ObjectAnimator animMid = ObjectAnimator.ofFloat(view, "translationX", screenWidth / 12, 4);
                final ObjectAnimator animOut = ObjectAnimator.ofFloat(view, "translationX", 4, -screenWidth);

                animIn.setDuration(200);
                animMid.setDuration(2500);
                animOut.setDuration(200);

                AnimatorSet animSet = new AnimatorSet();
                animSet.play(animIn).before(animMid);
                animSet.play(animMid).before(animOut);

                animSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        view.setVisibility(View.VISIBLE);
                        isAnimRunning = true;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        isAnimRunning = false;
                        showHignLevelAnim();
                    }
                });

                animMid.addListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        // 粒子动画
                        new ParticleSystem(mActivity, 70, R.mipmap.bg_white_dot, 1000)
                                .setScaleRange(0.2f, 1.5f)
                                .setSpeedByComponentsRange(0f, 0f, 0.05f, 0.1f)
                                .setAcceleration(0.00013f, 270)
                                .setFadeOut(200, new AccelerateInterpolator())
                                .emitWithGravity(view, Gravity.TOP, 50, 500);
                    }
                });

                animSet.start();
                //------------------------------------------------

            }
        }
    }
}
