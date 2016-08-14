package com.atom.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.atom.domain.Config;
import com.atom.domain.Info;
import com.test.administrator.atom.LocalActivity;

import java.io.IOException;
import java.util.List;

/**
 * 播放音乐服务
 */
public class MyService extends Service {
    Intent intent;
    MediaPlayer player;

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
        registerReceiver(new Service_Reiceiver(), new IntentFilter(Config.SERVICE_ACTION));
        player.setOnPreparedListener(preparedListener);
        player.setOnCompletionListener(completionListener);

    }

    //播放音乐
    private void playMusic(List<Info> infos) {
        Info info = infos.get(Config.POSITION);
        player.reset();
            try {
                player.setDataSource(info.getUrl());
                player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            player.start();
        }
    };
    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            switch (Config.MENU) {
                case 0:
                    if (++Config.POSITION >= LocalActivity.infos.size()) {
                        Config.POSITION = 0;
                    }
                    playMusic(LocalActivity.infos);
                    sendBroadcast(new Intent(Config.MAIN_ACTION));
                    break;
            }
        }
    };

    class Service_Reiceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (Config.CONTROL) {
                case -1:
                    if (Config.MENU == 0) {
                        Config.POSITION = --Config.POSITION < 0 ? LocalActivity.infos.size() - 1 : Config.POSITION;
                        playMusic(LocalActivity.infos);
                    }
                    Config.STATE = 1;
                    break;
                case 0:
                    player.pause();
                    Config.STATE = 0;
                    break;
                case 1:
                    if (Config.MENU == 0) {
                        Config.POSITION = ++Config.POSITION >= LocalActivity.infos.size() ? 0 : Config.POSITION;
                        playMusic(LocalActivity.infos);
                    }
                    Config.STATE = 1;
                    break;
                case 2:
                        if (Config.MENU==0){
                            if (Config.STATE==0){
                                player.start();
                            }else {
                                playMusic(LocalActivity.infos);
                            }
                        }
                        Config.STATE = 1;
                    break;
            }
            sendBroadcast(new Intent(Config.MAIN_ACTION));
        }
    }
}
