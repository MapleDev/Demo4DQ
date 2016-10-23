package com.xznn.spannablestringbuilder;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tv;
    private TextView tv2;

    private final String TEXT_STRING = "我是[@百度 ]，我怕谁[@百度 ]地点！？";
    private final String TEXT_KEY = "[@百度 ]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int startIndex = TEXT_STRING.indexOf(TEXT_KEY);
        int endIndex = startIndex + TEXT_KEY.length();

        SpannableStringBuilder builder = new SpannableStringBuilder(TEXT_STRING);

//    可单独文本前景色
        builder.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        //设置文本点击事件
        if (startIndex >= 0) {
            builder.setSpan(new ClickSpannable(this), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv = (TextView) findViewById(R.id.tv);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv.setMovementMethod(LinkMovementMethod.getInstance());

//        SpannableStringBuilder replace = builder.replace(TEXT_STRING.indexOf(TEXT_KEY), TEXT_STRING.indexOf(TEXT_KEY) + 1, "");

        tv.setText(builder);

        //====================
        SpannableStringBuilder spannableString = matcherSearchTitle(Color.BLUE, TEXT_STRING, TEXT_KEY);

        SpannableStringBuilder spannableStringBuilder = replaceSpannableStringChar(spannableString, new String[]{"[@", " ]"});
        tv2.setText(spannableStringBuilder);
        //====================
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "我被点击了", Toast.LENGTH_SHORT).show();
    }

    /**
     * SpannableStringBuilder 点击事件
     */
    private class ClickSpannable extends ClickableSpan implements View.OnClickListener {

        private View.OnClickListener onClickListener;

        public ClickSpannable(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        @Override
        public void onClick(View widget) {
            onClickListener.onClick(widget);
        }
    }

    //====================

    public SpannableStringBuilder matcherSearchTitle(int color, String text, String keyword) {
        String string = text.toLowerCase();
        String key = keyword.toLowerCase();

        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(string);

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            spannableStringBuilder.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableStringBuilder;
    }

    public static SpannableString matcherSearchTitleReturnSpannableString(int color, String text, String keyword) {
        String string = text.toLowerCase();
        String key = keyword.toLowerCase();

        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(string);

        SpannableString ss = new SpannableString(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color), start, end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }

    private SpannableStringBuilder replaceSpannableStringChar(SpannableStringBuilder spannableStringBuilder, String... key){
//        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spannableString);
        String s = spannableStringBuilder.toString();
        while (s.indexOf(key[0]) != -1) { // 删除 [@XX ] 中的 [ & ]
            spannableStringBuilder = spannableStringBuilder.replace(s.indexOf(key[0]), s.indexOf(key[0]) + 1, " ");
            spannableStringBuilder = spannableStringBuilder.replace(s.indexOf(key[1])+1, s.indexOf(key[1]) + 2 , "\u200B");
            s = spannableStringBuilder.toString();
        }
        return spannableStringBuilder;
    }
    //====================
}
