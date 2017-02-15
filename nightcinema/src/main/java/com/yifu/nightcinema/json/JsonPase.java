package com.yifu.nightcinema.json;

import com.yifu.nightcinema.bean.BaseBean;
import com.yifu.nightcinema.bean.Comment;
import com.yifu.nightcinema.bean.DetailBean;
import com.yifu.nightcinema.bean.ListBean;
import com.yifu.nightcinema.bean.VideoInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijilei on 2017/1/17.
 */

public class JsonPase {


    public static BaseBean toBean(String json) {
        ListBean listBean = new ListBean();
        List<VideoInfo> list = new ArrayList<VideoInfo>();
        List<VideoInfo> banners = new ArrayList<VideoInfo>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray con = jsonObject.getJSONArray("con");

            for (int i = 0; i < con.length(); i++) {
                JSONObject obj = con.optJSONObject(i);
                String dianying_id = obj.optString("dianying_id");
                String pic = obj.optString("pic");
                String title = obj.optString("title");
                String video = obj.optString("video");
                VideoInfo info = new VideoInfo(dianying_id, pic, title, video);
                list.add(info);
            }
            listBean.setList(list);
            int page = jsonObject.optInt("page");
            int totalPage = jsonObject.optInt("totalPage");
            listBean.setPage(page);
            listBean.setTotalPage(totalPage);

            JSONArray top = jsonObject.optJSONArray("top");
            if (top != null) {
                for (int i = 0; i < top.length(); i++) {
                    JSONObject obj = top.optJSONObject(i);
                    String dianying_id = obj.optString("dianying_id");
                    String pic = obj.optString("pic");
                    String title = obj.optString("title");
                    String video = obj.optString("video");
                    VideoInfo info = new VideoInfo(dianying_id, pic, title, video);
                    banners.add(info);
                }

            }


            listBean.setBaners(banners);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listBean;
    }

    public static BaseBean toDetailBean(String json) {

        DetailBean detailBean = new DetailBean();
        List<VideoInfo> topvideo = new ArrayList<VideoInfo>();
        List<Comment> comments = new ArrayList<Comment>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);

            JSONArray jsonArrayVideos = jsonObject.optJSONArray("top_video");
            JSONArray jsonArrayComments = jsonObject.optJSONArray("comment");


            for (int i = 0; i < jsonArrayVideos.length(); i++) {

                JSONObject obj = jsonArrayVideos.optJSONObject(i);
                String dianying_id = obj.optString("dianying_id");
                String pic = obj.optString("pic");
                String title = obj.optString("title");
                String video = obj.optString("video");
                VideoInfo info = new VideoInfo(dianying_id, pic, title, video);
                topvideo.add(info);

            }
            for (int i = 0; i < jsonArrayComments.length(); i++) {

                JSONObject obj = jsonArrayComments.optJSONObject(i);

                String avatar = obj.optString("avatar");
                String content = obj.optString("content");
                String nickname = obj.optString("nickname");
                String zan = obj.optString("zan");
                Comment comment = new Comment(avatar, content, nickname, zan);
                comments.add(comment);

            }
            detailBean.setComments(comments);
            detailBean.setTopvideo(topvideo);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return detailBean;

    }
}
