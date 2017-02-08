package com.yifu.nightcinema.bean;

import java.util.List;

/**
 * Created by lijilei on 2017/1/17.
 */

public class ListBean extends BaseBean {

    private List<VideoInfo> list;
    private List<VideoInfo> baners;
    private int page;
    private int totalPage;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<VideoInfo> getBaners() {
        return baners;
    }

    public void setBaners(List<VideoInfo> baners) {
        this.baners = baners;
    }

    public ListBean() {
    }

    public List<VideoInfo> getList() {
        return list;
    }

    public void setList(List<VideoInfo> list) {
        this.list = list;
    }
}
