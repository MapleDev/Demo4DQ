package com.xznn.customtoast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showCustomToast();
    }

    private void showCustomToast() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.book_reading_seekbar_toast, null);
        TextView chapterNameTV = (TextView) view.findViewById(R.id.tv_1);
        TextView percentageTV = (TextView) view.findViewById(R.id.tv_2);

        Toast toast = new Toast(this);
        toast.setGravity(Gravity.BOTTOM, 0, PixelFormat.formatDipToPx(this, 70));
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }
}
