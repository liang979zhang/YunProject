package com.example.administrator.yunproject.Bean;

/**
 * Created by Administrator on 2018/4/5.
 */

public class VideoData {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    private String title;   //视频名字
    private String videoPath;     //视频地址
    public boolean isKey() {
        return Key;
    }

    public void setKey(boolean key) {
        Key = key;
    }

    private boolean Key;
}
