package com.example.administrator.yunproject.Bean;

/**
 * Created by Administrator on 2018/3/29.
 */

public class LoginBean {


    /**
     * oauth_token : cd7f35e9d7356e223e6275d56e361ebe
     * oauth_token_secret : 0bc9cd712d1bea93efeab51ef796ba4d
     * uid : 41
     */

    private String oauth_token;
    private String oauth_token_secret;
    private String uid;

    public String getOauth_token() {
        return oauth_token;
    }

    public void setOauth_token(String oauth_token) {
        this.oauth_token = oauth_token;
    }

    public String getOauth_token_secret() {
        return oauth_token_secret;
    }

    public void setOauth_token_secret(String oauth_token_secret) {
        this.oauth_token_secret = oauth_token_secret;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
