package com.test.administrator.atom;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.ListView;

public class MainActivity extends Activity {

    private TextView mainTv;
    private ListView mainLv;
    private TextView mainSongName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTv = (TextView) findViewById(R.id.main_tv);
        findViewById(R.id.main_locate);
        findViewById(R.id.main_like);
        findViewById(R.id.main_load);
        mainLv = (ListView) findViewById(R.id.main_lv);
        findViewById(R.id.main_addMenu);
        mainSongName = (TextView) findViewById(R.id.main_songName);
        findViewById(R.id.main_play);
        findViewById(R.id.main_next);

    }

    public void click(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.main_locate:
                intent.setClass(this,LocalActivity.class);
                break;
            case R.id.main_like:
                //TODO implement
                break;
            case R.id.main_load:
                //TODO implement
                break;
            case R.id.main_addMenu:
                //TODO implement
                return;
            case R.id.main_play:
                //TODO implement
                return;
            case R.id.main_next:
                //TODO implement
                return;
        }
        startActivity(intent);
    }


}
