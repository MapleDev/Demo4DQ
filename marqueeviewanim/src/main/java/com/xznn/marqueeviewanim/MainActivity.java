package com.xznn.marqueeviewanim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.plattysoft.leonids.ParticleSystem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView bg_level;
    MarqueeView marquee_view;
    private View mInflate;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
//        marquee_view = (MarqueeView)findViewById(R.id.marquee_view);


//        mInflate = View.inflate(this, R.layout.vip_comming_marquee, null);
//        TextView tv = (TextView) mInflate.findViewById(R.id.tv);
//        tv.setText("XXXXXXXXXXXXXXXXXXXXXXX");

//        marqueeBackGroundAnimation();

//-------------------

    }


    /**
     * 跑马灯背景动画
     */
    private void marqueeBackGroundAnimation() {

        final VIPCommingMarqueeView ll = (VIPCommingMarqueeView) findViewById(R.id.ll);
        ll.setTextCont("XXXXXXX");

        // 向 left 移动  screenWidth
        int screenWidth = getScreenWidth(this); // 屏幕宽度


        final ObjectAnimator anim = ObjectAnimator.ofFloat(ll, "translationX", screenWidth, -screenWidth);
        anim.setDuration(3000);

        anim.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                float result;
                if (input <= 0.5) {
                    result = (float) (Math.sin(Math.PI * input)) / 2;
                } else {
//                    result = (float) (2 - Math.sin(Math.PI * input)) / 2;
                    result = (float) (Math.cos((input + 1) * Math.PI) / 2.0f) + 0.7f;
                }


                return result;
            }
        });

//        AccelerateDecelerateInterpolator
//        DecelerateInterpolator



        /*
         anim.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                float result;
                if (input <= 0.5) {
                    result = (float) (Math.sin(Math.PI * input)) / 2;
                } else {
//                    result = (float) (2 - Math.sin(Math.PI * input)) / 2;
                    result = (float)(Math.cos((input + 1) * Math.PI) / 2.0f) + 0.7f;
                }
                return result;
            }
        });

        * */

//        anim.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                SystemClock.sleep(2000);
//                ll.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                super.onAnimationRepeat(animation);
//            }
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//            }
//        });
        // 正式开始启动执行动画
        anim.start();
    }

    public int getScreenWidth(Context mContext) {
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public void onClick(View v) {
        marqueeBackGroundAnimation2();
    }


    private void marqueeBackGroundAnimation1() {

        final VIPCommingMarqueeView ll = (VIPCommingMarqueeView) findViewById(R.id.ll);
        ll.setTextCont("XXXXXXX");

        // 向 left 移动  screenWidth
        int screenWidth = getScreenWidth(this); // 屏幕宽度


        //---------------------------
        // 动画效果
        final ObjectAnimator anim = ObjectAnimator.ofFloat(ll, "translationX", screenWidth, 0);
        final ObjectAnimator anim2 = ObjectAnimator.ofFloat(ll, "translationX", 0, -screenWidth);

        anim.setDuration(250);
        anim2.setDuration(250);

        anim.setInterpolator(new DecelerateInterpolator());
//        anim.setInterpolator(new Interpolator() {
//            @Override
//            public float getInterpolation(float input) {
//                float result;
////                if (input <= 0.5) {
////                    result = (float) (Math.sin(Math.PI * input)) / 2;
////                } else {
//                    result = (float) (2 - Math.sin(Math.PI * input)) / 2;
////                }
//                return result;
//            }
//        });

        anim2.setInterpolator(new DecelerateInterpolator(1.0f));

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                button.postOnAnimationDelayed(new Runnable() {
                    @Override
                    public void run() {
                        anim2.start();
                    }
                }, 1000);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ll.setVisibility(View.VISIBLE);
//                isAnimRunning = true;
            }
        });

        anim2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
//                isAnimRunning = false;
            }
        });

        // 正式开始启动执行动画
        anim.start();
        //---------------------------
    }

    private void marqueeBackGroundAnimation2() {



        final VIPCommingMarqueeView view = (VIPCommingMarqueeView) findViewById(R.id.ll);
        view.setTextCont("XXXXXXX");

        // 向 left 移动  screenWidth
        int screenWidth = getScreenWidth(this); // 屏幕宽度


        //---------------------------
        // 动画效果
        final ObjectAnimator animIn = ObjectAnimator.ofFloat(view, "translationX", screenWidth, screenWidth / 7);
        final ObjectAnimator animMid = ObjectAnimator.ofFloat(view, "translationX", screenWidth / 7, 10);
        final ObjectAnimator animOut = ObjectAnimator.ofFloat(view, "translationX", 10, -screenWidth);

        animIn.setDuration(200);
        animMid.setDuration(2600);
        animOut.setDuration(200);

        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animIn).before(animMid);
        animSet.play(animMid).before(animOut);


        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

            }
        });

        animMid.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

//                new ParticleSystem(this, numParticles, drawableResId, timeToLive)
//                        .setSpeedRange(0.2f, 0.5f)
//                        .oneShot(anchorView, numParticles);

                new ParticleSystem(MainActivity.this, 10, R.mipmap.bg_white_dot, 200)
                        .setSpeedRange(0.2f, 0.5f)
                        .oneShot(view, 10);

////                new ParticleSystem(MainActivity.this, 100, R.mipmap.bg_white_dot, 2000)
////                        .setSpeedRange(0.2f, 0.5f)
////                        .setFadeOut(200)
////                        .setRotationSpeedRange(0, 10)
////                        .setAcceleration(0.00003f, 0)
////                        .addModifier(new ScaleModifier(0, 1.2f, 1000, 4000))
////                        .emit(view, 10, 500)
////                            ;
//
//                new ParticleSystem(MainActivity.this, 50, R.mipmap.bg_white_dot, 5000)
//                        .setAcceleration(0.00003f, 90)
//                        .addModifier(new ScaleModifier(0, 1.2f, 1000, 4000))
//                        .setFadeOut(5000)
////                        .setRotationSpeedRange(0, 180)
//                        .emit(view, 50);

            }
        });

        animSet.start();


//        animSet.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                button.postOnAnimationDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        anim2.start();
//                    }
//                }, 1000);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                super.onAnimationRepeat(animation);
//            }
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//                view.setVisibility(View.VISIBLE);
////                isAnimRunning = true;
//            }
//        });
//
//        anim2.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
////                isAnimRunning = false;
//            }
//        });

        // 正式开始启动执行动画
//        anim.start();
        //---------------------------
    }
}
