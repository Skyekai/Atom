package com.atom.domain;

/**
 * Created by Administrator on 2016/8/12.
 */
public class Config {
    public static final String MAIN_ACTION="com.mainActivity.cn";
    public static final String SERVICE_ACTION="com.service.cn";
    public static final String PLAYER_ACTION="com.player.cn";
    public static final String PSEEK_ACTION="com.pseek.cn";
    public static final String SSEEK_ACTION="com.sseek.cn";
    public static final String SERVICE_NAME="com.service.MyService";
    public static final String TB_CURRENT="currentSong";
    public static final String DC_CURRENT_POSITION="position";
    public static final String DC_CURRENT_MENU="menu";
    public static final String DC_CURRENT_ISFIRST="isFirst";
    public static int isFirst=0;//是否第一次登录，0表示第一次
    public static int CONTROL=0;//控制按钮，-1上一首，0暂停，1下一首，2播放
    public static int STATE =-1;//播放状态，0暂停，1播放中,-1停止
    public static int POSITION =0;
    public static int where =0;//当前所在的页面
    public static int MENU=0;//播放歌单，0本地音乐
}
