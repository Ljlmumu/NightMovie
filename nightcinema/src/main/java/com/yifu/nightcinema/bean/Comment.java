package com.yifu.nightcinema.bean;

/**
 * Created by lijilei on 2017/2/14.
 */

public class Comment {


    private String avatar;
    private String content;
    private String nickname;
    private String zan;

    public Comment() {
    }

    public Comment(String avatar, String content, String nickname, String zan) {
        this.avatar = avatar;
        this.content = content;
        this.nickname = nickname;
        this.zan = zan;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getZan() {
        return zan;
    }

    public void setZan(String zan) {
        this.zan = zan;
    }

}
