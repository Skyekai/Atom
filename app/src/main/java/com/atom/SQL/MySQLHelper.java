package com.atom.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/8/13.
 */
public class MySQLHelper extends SQLiteOpenHelper{
    private String tb_currentSong="create table currentSong(id integer primary key,position text,menu text,isFirst text)";
    public MySQLHelper(Context context) {
        super(context, "MusicPlayer",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(tb_currentSong);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
