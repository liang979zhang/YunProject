package com.example.administrator.yunproject.Until;

/**
 * Created by Administrator on 2018/4/7.
 */

public class MessageInfo {
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    private String userName;
    private String userHead;
    private boolean key;
    public boolean isKey() {
        return key;
    }


    public void setKey(boolean key) {
        this.key = key;
    }

}
