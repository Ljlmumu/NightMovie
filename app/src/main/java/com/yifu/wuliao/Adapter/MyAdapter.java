package com.yifu.wuliao.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.yifu.wuliao.R;
import com.yifu.wuliao.bean.DataInfoList;
import com.yifu.wuliao.bean.RmdInfoLst;
import com.yifu.wuliao.utils.BitmapCache;
import com.yifu.wuliao.view.MyGridView;

import java.util.List;

/**
 * Created by tf on 2016/8/5.
 */
public class MyAdapter extends BaseAdapter
{
    static final int TYPE_ONE = 0;
    static final int TYPE_THREE = 2;
    static final int TYPE_TWO = 1;
    private Context context;
    private List<DataInfoList> dataInfoLists;
    private ImageLoader imageLoader;
    private LayoutInflater inflater;
    private RequestQueue queue;
    private List<RmdInfoLst> rmdInfoLst;

    public MyAdapter(Context paramContext, List<RmdInfoLst> paramList)
    {
        this.context = paramContext;
        this.rmdInfoLst = paramList;
        this.queue = Volley.newRequestQueue(paramContext);
        this.imageLoader = new ImageLoader(this.queue, new BitmapCache());
    }

    public int getCount()
    {
        return this.rmdInfoLst.size();
    }

    public Object getItem(int paramInt)
    {
        return this.rmdInfoLst.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
        return paramInt;
    }

    public int getItemViewType(int paramInt)
    {
        if (paramInt == 6)
            return 0;
        return 1;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
        int i = getItemViewType(((RmdInfoLst)this.rmdInfoLst.get(paramInt)).getDataInfoList().size());
        this.inflater = LayoutInflater.from(this.context);
        ViewHolder localViewHolder = null;
        ViewHolder2 localViewHolder2 = null;

            localViewHolder = new ViewHolder();
            paramView = View.inflate(this.context, R.layout.lv_item, null);
            localViewHolder.gridV = ((MyGridView)paramView.findViewById(R.id.gridV));
            localViewHolder.lv_ittv = ((TextView)paramView.findViewById(R.id.lv_ittv));
            paramView.setTag(localViewHolder);

//            localViewHolder2 = new ViewHolder2();
//            paramView = View.inflate(this.context, 2130903054, null);
//            localViewHolder2.wTV_title = ((TextView)paramView.findViewById(2131296327));
//            localViewHolder2.lv_tV1 = ((TextView)paramView.findViewById(2131296330));
//            localViewHolder2.lv_tV2 = ((TextView)paramView.findViewById(2131296332));
//            localViewHolder2.lv_tV3 = ((TextView)paramView.findViewById(2131296335));
//            localViewHolder2.lv_tV4 = ((TextView)paramView.findViewById(2131296337));
//            localViewHolder2.lv_tV5 = ((TextView)paramView.findViewById(2131296339));
//            localViewHolder2.lv_tV6 = ((TextView)paramView.findViewById(2131296342));
//            localViewHolder2.lv_tV7 = ((TextView)paramView.findViewById(2131296344));
//            localViewHolder2.lv_tV8 = ((TextView)paramView.findViewById(2131296346));
//            localViewHolder2.lv_imgView1 = ((NetworkImageView)paramView.findViewById(2131296329));
//            localViewHolder2.lv_imgView2 = ((NetworkImageView)paramView.findViewById(2131296331));
//            localViewHolder2.lv_imgView3 = ((NetworkImageView)paramView.findViewById(2131296334));
//            localViewHolder2.lv_imgView4 = ((NetworkImageView)paramView.findViewById(2131296336));
//            localViewHolder2.lv_imgView5 = ((NetworkImageView)paramView.findViewById(2131296338));
//            localViewHolder2.lv_imgView6 = ((NetworkImageView)paramView.findViewById(2131296341));
//            localViewHolder2.lv_imgView7 = ((NetworkImageView)paramView.findViewById(2131296343));
//            localViewHolder2.lv_imgView8 = ((NetworkImageView)paramView.findViewById(2131296345));
//            paramView.setTag(localViewHolder2);
//            localViewHolder = null;
//            break;
            localViewHolder.lv_ittv.setText(((RmdInfoLst)this.rmdInfoLst.get(paramInt)).title);
            GridViewAdapter localGridViewAdapter = new GridViewAdapter(this.context, ((RmdInfoLst)this.rmdInfoLst.get(paramInt)).dataInfoList);
            localViewHolder.gridV.setAdapter(localGridViewAdapter);
//            return paramView;
            /*this.dataInfoLists = ((RmdInfoLst)this.rmdInfoLst.get(paramInt)).dataInfoList;
            localViewHolder2.wTV_title.setText(((RmdInfoLst)this.rmdInfoLst.get(paramInt)).title);
            localViewHolder2.lv_tV1.setText(((DataInfoList)this.dataInfoLists.get(0)).description);
            localViewHolder2.lv_tV2.setText(((DataInfoList)this.dataInfoLists.get(1)).description);
            localViewHolder2.lv_tV3.setText(((DataInfoList)this.dataInfoLists.get(2)).description);
            localViewHolder2.lv_tV4.setText(((DataInfoList)this.dataInfoLists.get(3)).description);
            localViewHolder2.lv_tV5.setText(((DataInfoList)this.dataInfoLists.get(4)).description);
            localViewHolder2.lv_tV6.setText(((DataInfoList)this.dataInfoLists.get(5)).description);
            localViewHolder2.lv_tV7.setText(((DataInfoList)this.dataInfoLists.get(6)).description);
            localViewHolder2.lv_imgView1.setImageUrl(((DataInfoList)this.dataInfoLists.get(0)).imgUrl1, this.imageLoader);
            localViewHolder2.lv_imgView1.setDefaultImageResId(2130837543);
            localViewHolder2.lv_imgView1.setErrorImageResId(2130837543);
            localViewHolder2.lv_imgView2.setImageUrl(((DataInfoList)this.dataInfoLists.get(1)).imgUrl1, this.imageLoader);
            localViewHolder2.lv_imgView3.setImageUrl(((DataInfoList)this.dataInfoLists.get(2)).imgUrl1, this.imageLoader);
            localViewHolder2.lv_imgView4.setImageUrl(((DataInfoList)this.dataInfoLists.get(3)).imgUrl1, this.imageLoader);
            localViewHolder2.lv_imgView5.setImageUrl(((DataInfoList)this.dataInfoLists.get(4)).imgUrl1, this.imageLoader);
            localViewHolder2.lv_imgView6.setImageUrl(((DataInfoList)this.dataInfoLists.get(5)).imgUrl1, this.imageLoader);
            localViewHolder2.lv_imgView7.setImageUrl(((DataInfoList)this.dataInfoLists.get(6)).imgUrl1, this.imageLoader);*/
//        }
       /* while (this.dataInfoLists.size() <= 7);
        localViewHolder2.lv_imgView8.setImageUrl(((DataInfoList)this.dataInfoLists.get(7)).imgUrl1, this.imageLoader);
        TextView localTextView1 = localViewHolder2.lv_tV8;
        localTextView1.setVisibility(0);
        localViewHolder2.lv_tV8.setText(((DataInfoList)this.dataInfoLists.get(7)).description);
        NetworkImageView localNetworkImageView = localViewHolder2.lv_imgView8;
        localNetworkImageView.setVisibility(0);
        TextView localTextView2 = localViewHolder2.lv_tV8;
        localTextView2.setVisibility(0);*/
        return paramView;
   }

    public int getViewTypeCount()
    {
        return 1;
    }

    public class ViewHolder
    {
        private MyGridView gridV;
        private TextView lv_ittv;


    }

    public class ViewHolder2
    {
        private NetworkImageView lv_imgView1;
        private NetworkImageView lv_imgView2;
        private NetworkImageView lv_imgView3;
        private NetworkImageView lv_imgView4;
        private NetworkImageView lv_imgView5;
        private NetworkImageView lv_imgView6;
        private NetworkImageView lv_imgView7;
        private NetworkImageView lv_imgView8;
        private TextView lv_tV1;
        private TextView lv_tV2;
        private TextView lv_tV3;
        private TextView lv_tV4;
        private TextView lv_tV5;
        private TextView lv_tV6;
        private TextView lv_tV7;
        private TextView lv_tV8;
        private TextView wTV_title;


    }
}