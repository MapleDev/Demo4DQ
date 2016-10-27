package com.xznn.gridview_divider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    GridView gv;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gv = (GridView)findViewById(R.id.gv);
        mData = new ArrayList<>();
        mData.add("A");
        mData.add("B");
        mData.add("C");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item,
                R.id.text1,
                mData);
        gv.setAdapter(adapter);

    }
}
