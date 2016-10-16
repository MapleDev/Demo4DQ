package com.xznn.spannable_string;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xznn.myapplication.R;

public class MainActivity extends AppCompatActivity {

    //Android SpannableString实现富文本展现 - 路漫漫其修远兮 吾将上下而求索 - ITeye技术网站 -
    // http://sinye.iteye.com/blog/2103421

    private TextView tv,tv1;
    static Context ctx = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //富文本点击响应
        tv = (TextView)findViewById(R.id.tv);
        tv.setTextSize(20);
        tv.setText(getClickableSpan());
        tv.setMovementMethod(LinkMovementMethod.getInstance());

        //添加了普通字符串和表情的展示
        tv1 = (TextView)findViewById(R.id.tv1);
        tv1.setText(transferBiaoQing("hello android",R.mipmap.ic_launcher));
    }

    private SpannableString getClickableSpan() {
        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click Success",
                        Toast.LENGTH_SHORT).show();
            }
        };

        SpannableString spanableInfo = new SpannableString(
                "This is a test, Click Me");
        int start = 16;
        int end = spanableInfo.length();
        spanableInfo.setSpan(new Clickable(l), start, end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanableInfo;
    }

    /**
     * 内部类，用于截获点击富文本后的事件
     * @author pro
     *
     */
    class Clickable extends ClickableSpan implements View.OnClickListener {
        private final View.OnClickListener mListener;

        public Clickable(View.OnClickListener l) {
            mListener = l;
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ds.linkColor);
//            ds.setUnderlineText(false);    //去除超链接的下划线
            ds.setUnderlineText(true);
        }
    }

    /**
     * 将富文本转成CharSequence
     * @param commonStr 普通内容
     * @param bqId  表情图片
     * @return
     */
    public CharSequence transferBiaoQing(String commonStr,int bqId) {
        return Html.fromHtml(commonStr+"<img src=\"" + bqId + "\">", imageGetter, null);
    }

    /**
     * 获取本地图片资源
     */
    private Html.ImageGetter imageGetter = new Html.ImageGetter() {
        @Override
        public Drawable getDrawable(String source) {
            int id = Integer.parseInt(source);
            // 根据id从资源文件中获取图片对象
            Drawable d = MainActivity.this.getApplicationContext().getResources().getDrawable(id);
            // 以此作为标志位，方便外部取出对应的资源id
            d.setState(new int[] { id });
            d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            return d;
        }
    };

}
