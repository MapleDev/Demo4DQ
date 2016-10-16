package com.kymjs.shache;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        ObjectAnimator anim = ObjectAnimator.ofFloat(textView, "translationX", 500f, -500f);
        anim.setDuration(1000);
        anim.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                float result;
                if (input <= 0.5) {
                    result = (float) (Math.sin(Math.PI * input)) / 2;
                } else {
                    result = (float) (2 - Math.sin(Math.PI * input)) / 2;
                }
                return result;
            }
        });
        anim.start();


    }
}
