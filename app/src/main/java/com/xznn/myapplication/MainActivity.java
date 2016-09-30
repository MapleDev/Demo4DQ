package com.xznn.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String title = "XZWW{$nickname}XZQQ";
//        String title2 = title.replace("{$nickname}", "");
//        Log.d("DDD", "title2 = " + title2);

        GridView gridView = (GridView)findViewById(R.id.list_view);
//
//        TextView emptyView = new TextView(this);
//        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
//        emptyView.setText("This appears when the list is empty");
//                    emptyView.setVisibility(View.GONE);
//        ((ViewGroup)gridView.getParent()).addView(emptyView);

//        gridView.setEmptyView(findViewById(R.id.tv_empty));


        View emptyView = View.inflate(this,R.layout.empty, null);
        ((ViewGroup)gridView.getParent()).addView(emptyView);
//        ListView listView = (ListView)findViewById(R.id.list_view);
//        listView.setEmptyView(emptyView);
        gridView.setEmptyView(emptyView);

    }

//  public void onClick(View view) {

//        m://DouQuZhiBo
//        Uri uri = Uri.parse("m://DouQuZhiBo");
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);
//
//        Dialog dialog = new Dialog(this);
//        dialog.set
//        dialog.show();
//    }
}
