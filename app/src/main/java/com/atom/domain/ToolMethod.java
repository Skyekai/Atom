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
}
