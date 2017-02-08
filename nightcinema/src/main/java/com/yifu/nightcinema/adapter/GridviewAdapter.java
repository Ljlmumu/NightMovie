package com.yifu.nightcinema.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yifu.nightcinema.R;
import com.yifu.nightcinema.activityes.PlayerActivity;
import com.yifu.nightcinema.bean.VideoInfo;
import com.yifu.nightcinema.net.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijilei on 2017/1/17.
 */

public class GridviewAdapter extends BaseAdapter {
    private List<VideoInfo> data = new ArrayList<VideoInfo>();
    private Context context;

    public GridviewAdapter() {
    }

    public GridviewAdapter(Context context) {
        this.context = context;
    }

    public GridviewAdapter(Context context, List<VideoInfo> data) {
        this.data = data;
        this.context = context;
    }

    public void addData(List<VideoInfo> data) {
        data.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final VideoInfo info = data.get(position);
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = View.inflate(context, R.layout.iten_gv, null);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_item_pic);
            TextView textView = (TextView) convertView.findViewById(R.id.tv_item_title);
            holder = new ViewHolder();
            holder.iv_pic = imageView;
            holder.tv_title = textView;
            convertView.setTag(holder);
        }
        VolleyUtil.getInstance().showImage(info.getPic(), holder.iv_pic);
        holder.tv_title.setText(info.getTitle());
        holder.iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayerActivity.class);
                intent.putExtra("url", info.getVideo());
                intent.putExtra("title", info.getTitle());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView iv_pic;
        TextView tv_title;
    }


    public List<VideoInfo> getData() {
        return data;
    }

    public void setData(List<VideoInfo> data) {
        this.data = data;
    }
}
