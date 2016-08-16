package com.atom.domain;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/13.
 */
public class ToolMethod {
    //得到本地歌曲，返回list
    public static List<Info> GetLocalMusic(ContentResolver contentResolver) {
        List<Info> infos = new ArrayList<Info>();
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        while (cursor.moveToNext()) {
            Info info = new Info();
            String title = cursor.getString((cursor
                    .getColumnIndex(MediaStore.Audio.Media.TITLE)));//音乐标题

            String artist = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST));//艺术家

            long duration = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DURATION));//时长

            String url = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA));    //文件路径

            String album = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM)); //唱片图片

            int isMusic = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));//是否为音乐
            if (duration>20000) {        //只把20s以上的音乐添加到集合当中
                info.setName(title);
                info.setSinger(artist);
                info.setTime(duration);
                info.setUrl(url);
                info.setImage(album);
                info.setIsMusic(isMusic);
                infos.add(info);
            }
        }
        return infos;
    }
    public static String formatTime(Long time) {                     //将歌曲的时间转换为分秒的制度
        String min = time / (1000 * 60) + "";
        String sec = time % (1000 * 60) + "";

        if (min.length() < 2)
            min = "0" + min;
        switch (sec.length()) {
            case 4:
                sec = "0" + sec;
                break;
            case 3:
                sec = "00" + sec;
                break;
            case 2:
                sec = "000" + sec;
                break;
            case 1:
                sec = "0000" + sec;
                break;
        }
        return min + ":" + sec.trim().substring(0, 2);
    }
}
