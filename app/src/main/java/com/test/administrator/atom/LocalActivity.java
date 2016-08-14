package com.test.administrator.atom;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.atom.adapter.LocalListViewAdapter;
import com.atom.domain.Config;
import com.atom.domain.Info;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

public class LocalActivity extends AppCompatActivity {
    private TextView title;
    private ListView lv;
    public static List<Info> infos;
    private LocalListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(LocalActivity.this);
        setContentView(R.layout.activity_local);
        init();
    }

    private void init() {
        title = (TextView) findViewById(R.id.title_name);
        lv = (ListView) findViewById(R.id.local_lv);
        adapter = new LocalListViewAdapter(LocalActivity.this, infos);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(listener);
    }
    AdapterView.OnItemClickListener listener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Config.MENU=0;
            Config.POSITION=position;
            Config.CONTROL=2;
            sendBroadcast(new Intent(Config.SERVICE_ACTION));
        }
    };
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
        }
    }


}
