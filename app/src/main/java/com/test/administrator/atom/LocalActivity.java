package com.test.administrator.atom;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.atom.domain.Info;

import java.util.ArrayList;
import java.util.List;

public class LocalActivity extends AppCompatActivity {
    TextView title;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        init();
    }
    private void init(){
        title= (TextView) findViewById(R.id.title_name);
        lv= (ListView) findViewById(R.id.local_lv);
    }
    public void click(View view){
        switch(view.getId()){
            case R.id.title_back:
                finish();
                break;
        }
    }
    private List<Info> GetLocalMusic(ContentResolver contentResolver) {
        List<Info> infos=new ArrayList<Info>();
        Cursor cursor=contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        while (cursor.moveToNext()){
            Info info=new Info();
            String title = cursor.getString((cursor
                    .getColumnIndex(MediaStore.Audio.Media.TITLE)));//音乐标题

            String artist = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST));//艺术家

            long duration = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DURATION));//时长

            String url = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA));	//文件路径

            String album = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM)); //唱片图片

            int isMusic = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));//是否为音乐

            if (isMusic != 0 && duration/(1000 * 60) >= 1) {		//只把1分钟以上的音乐添加到集合当中
                info.setName(title);
                info.setSinger(artist);
                info.setTime(duration);
                info.setUrl(url);
                info.setImage(album);
                info.setIsMusic(isMusic);
            }
            infos.add(info);
        }
        return infos;
    }
}
