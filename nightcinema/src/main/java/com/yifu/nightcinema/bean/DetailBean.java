package com.yifu.nightcinema.bean;

import java.util.List;

/**
 * Created by lijilei on 2017/2/14.
 */

public class DetailBean extends BaseBean {

    private List<VideoInfo> topvideo;
    private List<Comment> comments;

    public DetailBean() {
    }

    public DetailBean(List<VideoInfo> topvideo, List comments) {
        this.topvideo = topvideo;
        this.comments = comments;
    }

    public List<VideoInfo> getTopvideo() {
        return topvideo;
    }

    public void setTopvideo(List<VideoInfo> topvideo) {
        this.topvideo = topvideo;
    }

    public List getComments() {
        return comments;
    }

    public void setComments(List comments) {
        this.comments = comments;
    }


}
