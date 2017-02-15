package com.yifu.nightcinema.bean;

import java.io.Serializable;

/**
 * Created by lijilei on 2017/1/17.
 */

public class VideoInfo implements Serializable {

        private String dianying_id;
        private String pic;
        private String title;
        private String video;

    public VideoInfo() {
    }

    public VideoInfo(String dianying_id, String pic, String title, String video) {
        this.dianying_id = dianying_id;
        this.pic = pic;
        this.title = title;
        this.video = video;
    }

    public String getDianying_id() {
            return dianying_id;
        }

        public void setDianying_id(String dianying_id) {
            this.dianying_id = dianying_id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

}
