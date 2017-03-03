package com.xznn.temp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.xznn.temp.R.id.listView1;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> mAdapterData;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(listView1);
        mAdapterData = new ArrayList<String>();
        for (int i = 0; i < 40; i++) {
            mAdapterData.add("ListItem" + i);
        }
//        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mAdapterData);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, mAdapterData);
        listView.setAdapter(mAdapter);




    }

    private int cnt = 20;
    public void btnClick(View view) {
        String str = "new item" + cnt++;
        mAdapterData.add(str);
        mAdapter.notifyDataSetChanged();
    }
}


