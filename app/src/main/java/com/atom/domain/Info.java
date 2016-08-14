package com.atom.domain;

/**
 * Created by Administrator on 2016/8/10.
 */
public class Info {
    private int isMusic;
    private String name;
    private String singer;
    private long time;
    private String url;
    private String image;

    public int getIsMusic() {
        return isMusic;
    }

    public void setIsMusic(int isMusic) {
        this.isMusic = isMusic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Info(String name, String singer, long time, String url, String image) {
        this.name = name;
        this.singer = singer;
        this.time = time;
        this.url = url;
        this.image = image;
    }

    public Info() {
    }
}
