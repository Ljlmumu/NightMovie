package com.yifu.nightcinema.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yifu.nightcinema.R;
import com.yifu.nightcinema.bean.VideoInfo;
import com.yifu.nightcinema.net.VolleyUtil;

import java.util.List;

/**
 * Created by lijilei on 2017/2/15.
 */

public class DetailGridviewAdapter extends GridviewAdapter {

    public DetailGridviewAdapter() {
    }

    public DetailGridviewAdapter(Context context, List<VideoInfo> data) {
        super(context, data);
    }

    @NonNull
    @Override
    protected View setView(int position, View convertView) {
        ViewHolder holder = null;
        final VideoInfo info = getData().get(position);
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = View.inflate(context, R.layout.item_detal_video, null);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.comment_iv_top_item_image);
            TextView textView = (TextView) convertView.findViewById(R.id.comment_tv_top_item_name);
           // imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 160));

            holder = new ViewHolder();
            holder.iv_pic = imageView;
            holder.tv_title = textView;
            convertView.setTag(holder);
        }
        VolleyUtil.getInstance().showImage(info.getPic(), holder.iv_pic);
        holder.tv_title.setText(info.getTitle());
        return convertView;


    }
}
