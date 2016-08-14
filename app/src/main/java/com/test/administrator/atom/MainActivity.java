package com.test.administrator.atom;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import com.atom.SQL.DBMethod;
import com.atom.SQL.MySQLHelper;
import com.atom.domain.Config;
import com.atom.domain.Info;
import com.atom.domain.ToolMethod;

import java.util.List;

public class MainActivity extends Activity {

    private TextView mainTv, mainSongName, singer;
    private Button play;
    private ListView mainLv;
    private Intent intent;
    private MySQLHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        DBMethod.Current_Query(db);
        registerReceiver(new Main_receiver(), new IntentFilter(Config.MAIN_ACTION));
        intent = new Intent();
        intent.setAction(Config.SERVICE_NAME);
        final Intent eintent = new Intent(createExplicitFromImplicitIntent(this, intent));
        startService(eintent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Config.isFirst==1){
            sendBroadcast(new Intent(Config.MAIN_ACTION));
        }
    }


    public void init() {
        mainTv = (TextView) findViewById(R.id.main_tv);
        mainLv = (ListView) findViewById(R.id.main_lv);
        mainSongName = (TextView) findViewById(R.id.main_songName);
        singer = (TextView) findViewById(R.id.main_songer);
        play = (Button) findViewById(R.id.main_play);
        helper = new MySQLHelper(MainActivity.this);
        db = helper.getReadableDatabase();
        LocalActivity.infos = ToolMethod.GetLocalMusic(getContentResolver());
    }

    public void click(View view) {
        Intent intent1 = new Intent();
        switch (view.getId()) {
            case R.id.main_locate:
                intent1.setClass(MainActivity.this, LocalActivity.class);
                break;
            case R.id.main_like:
                //TODO implement
                return;
            case R.id.main_load:
                //TODO implement
                return;
            case R.id.main_addMenu:
                //TODO implement
                return;
            case R.id.main_play:
                if (Config.isFirst == 0) {
                    Toast.makeText(MainActivity.this, "请选择您要播放的歌曲", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Config.STATE == 0||Config.STATE==-1) {
                    Config.CONTROL = 2;
                } else {
                    Config.CONTROL = 0;
                }
                sendBroadcast(new Intent(Config.SERVICE_ACTION));
                return;
            case R.id.main_next:
                Config.CONTROL = 1;
                sendBroadcast(new Intent(Config.SERVICE_ACTION));
                return;
        }
        startActivity(intent1);
    }

    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }

    class Main_receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Info info = null;
            if (Config.MENU == 0) {
                info = LocalActivity.infos.get(Config.POSITION);
            }
            mainSongName.setText(info.getName());
            singer.setText(info.getSinger());
            switch (Config.STATE) {
                case 0:
                    play.setBackgroundResource(R.mipmap.bofang);
                    break;
                case 1:
                    play.setBackgroundResource(R.mipmap.zanting);
                    break;
            }
            if (Config.isFirst == 0) {
                DBMethod.Current_insert(db);
                Config.isFirst=1;
            } else if (Config.isFirst == 1) {
                DBMethod.Current_UpData(db);
            }
        }
    }

}
