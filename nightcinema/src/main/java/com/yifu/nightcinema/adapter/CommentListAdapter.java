package com.yifu.nightcinema.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yifu.nightcinema.R;
import com.yifu.nightcinema.bean.Comment;
import com.yifu.nightcinema.net.VolleyUtil;

import java.util.List;



/**
 * Created by lijilei on 2017/2/14.
 */

public class CommentListAdapter extends BaseAdapter {
    private List<Comment> comments;
    private Context context;

    public CommentListAdapter() {
    }

    public CommentListAdapter(Context context, List<Comment> comments) {
        this.comments = comments;
        this.context = context;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        Comment comment = comments.get(position);

        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();

        } else {
            convertView = View.inflate(context, R.layout.item_comment, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.comment_list_tv_name);
            viewHolder.tv_cotent = (TextView) convertView.findViewById(R.id.comment_list_tv_comment);
            viewHolder.tv_zan = (TextView) convertView.findViewById(R.id.comment_list_tv_zan);
            viewHolder.iv_header = (ImageView) convertView.findViewById(R.id.comment_list_iv);
            convertView.setTag(viewHolder);
        }
        viewHolder.tv_name.setText(comment.getNickname());
        viewHolder.tv_cotent.setText(comment.getContent());
        viewHolder.tv_zan.setText(comment.getZan());
        VolleyUtil.getInstance().showImage(comment.getAvatar(), viewHolder.iv_header);


        return convertView;
    }

    class ViewHolder {
        TextView tv_name;
        TextView tv_cotent;
        ImageView iv_header;
        TextView tv_zan;


    }


}
