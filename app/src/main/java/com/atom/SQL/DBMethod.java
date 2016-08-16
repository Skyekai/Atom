package com.atom.SQL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.atom.domain.Config;
import com.atom.domain.Info;

/**
 * 数据库工具类
 */

public class DBMethod {
    //创建当前歌曲
    public static void Current_insert(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(Config.DC_CURRENT_POSITION, Config.POSITION+"");
        values.put(Config.DC_CURRENT_MENU, Config.MENU+"");
        values.put(Config.DC_CURRENT_ISFIRST,1+"");
        db.insert(Config.TB_CURRENT, null, values);

    }
    //更改当前歌曲
    public static void Current_UpData(SQLiteDatabase db){
        ContentValues values=new ContentValues();
        values.put(Config.DC_CURRENT_POSITION, Config.POSITION+"");
        values.put(Config.DC_CURRENT_MENU, Config.MENU+"");
        values.put(Config.DC_CURRENT_ISFIRST,1+"");
        db.update(Config.TB_CURRENT, values, "id=?", new String[]{"1"});
    }
    //查询当前歌曲
    public static void Current_Query(SQLiteDatabase db){
        Cursor cursor=db.rawQuery("select * from "+Config.TB_CURRENT,null);
        while(cursor.moveToNext()){
            int index=1;
            Config.POSITION=Integer.parseInt(cursor.getString(index++));
            Config.MENU=Integer.parseInt(cursor.getString(index++));
            Config.isFirst=Integer.parseInt(cursor.getString(index++));
        }
    }
}
