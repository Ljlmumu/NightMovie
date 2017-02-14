package com.yifu.wuliao.view;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ffcs.inapppaylib.PayHelper;
import com.ffcs.inapppaylib.bean.response.BaseResponse;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.yifu.wuliao.Constants;
import com.yifu.wuliao.R;
import com.yifu.wuliao.bean.BannerInfoList;
import com.yifu.wuliao.utils.Read;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ViewPager实现的轮播图广告自定义视图，如京东首页的广告轮播图效果；
 * 既支持自动轮播页面也支持手势滑动切换页面
 */

public class SlideShowView extends FrameLayout {

    // 使用universal-image-loader插件读取网络图片，需要工程导入universal-image-loader-1.8.6-with-sources.jar
    private ImageLoader imageLoader = ImageLoader.getInstance();

    //轮播图图片数量
    private final static int IMAGE_COUNT = 5;
    //自动轮播的时间间隔
    private final static int TIME_INTERVAL = 5;
    //自动轮播启用开关
    private final static boolean isAutoPlay = true;

    //自定义轮播图的资源
    //private String[] imageUrls;
    private List imageUrls = new ArrayList();
    //放轮播图片的ImageView 的list
    private List<ImageView> imageViewsList;
    //放圆点的View的list
    private List<View> dotViewsList;

    private ViewPager viewPager;
    //当前轮播页
    private int currentItem = 0;
    //定时任务
    private ScheduledExecutorService scheduledExecutorService;

    private Context context;
    private int index;
//    //Handler
//    private Handler handler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            // TODO Auto-generated method stub
//            super.handleMessage(msg);
//            viewPager.setCurrentItem(currentItem);
//        }
//
//    };

    public SlideShowView(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
    }

    public SlideShowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public SlideShowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;

        initImageLoader(context);

        initData();
        if (isAutoPlay) {
            startPlay();
        }

    }


    /**
     * 开始轮播图切换
     */
    private void startPlay() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
    }

    /**
     * 停止轮播图切换
     */
    private void stopPlay() {
        scheduledExecutorService.shutdown();
    }

    /**
     * 初始化相关Data
     */
    private void initData() {
        imageViewsList = new ArrayList<ImageView>();
        dotViewsList = new ArrayList<View>();
//        getRead();
//        getImgUrl();
        // 一步任务获取图片
        new GetListTask().execute("");
    }

    /**
     * 初始化Views等UI
     */
    private void initUI(Context context) {
        if (imageUrls == null || imageUrls.size() == 0)
            return;

        LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, true);

        LinearLayout dotLayout = (LinearLayout) findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();

        // 热点个数与图片特殊相等
        for (int i = 0; i < imageUrls.size(); i++) {
            ImageView view = new ImageView(context);
            view.setTag(imageUrls.get(i));
            if (i == 0)//给一个默认图
                view.setBackgroundResource(R.drawable.channel_item_4);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViewsList.add(view);

            ImageView dotView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 4;
            params.rightMargin = 4;
            dotLayout.addView(dotView, params);
            dotViewsList.add(dotView);
        }

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setFocusable(true);

        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setOnPageChangeListener((ViewPager.OnPageChangeListener) new MyPageChangeListener());
    }

    public void setData(List<String> imgUrl, List<String> videoUrl, Context context) {
        this.imageUrls = imgUrl;
        this.context = context;
    }

    /**
     * 填充ViewPager的页面适配器
     */
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            //((ViewPag.er)container).removeView((View)object);
            ((ViewPager) container).removeView(imageViewsList.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ImageView imageView = imageViewsList.get(position);

            imageLoader.displayImage(imageView.getTag() + "", imageView);

            ((ViewPager) container).addView(imageViewsList.get(position));
            return imageViewsList.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imageViewsList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

    }

    /**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
                case 1:// 手势滑动，空闲中
                    isAutoPlay = false;
                    break;
                case 2:// 界面切换中
                    isAutoPlay = true;
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        viewPager.setCurrentItem(0);
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                    }
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(final int pos) {
            // TODO Auto-generated method stub

            currentItem = pos;
            for (int i = 0; i < dotViewsList.size(); i++) {
                if (i == pos) {
                    ((View) dotViewsList.get(pos)).setBackgroundResource(R.drawable.atuijian_09);
                } else {
                    ((View) dotViewsList.get(i)).setBackgroundResource(R.drawable.atuijian_07);
                }
            }
            imageViewsList.get(pos).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder localBuilder = new AlertDialog.Builder(context, 2);
                    localBuilder.setTitle("VIP订购").setMessage("您将支付" +  Constants.PRICE_TOP + "元/次热播影片服务，由运营商代收，确认是否购买，如有疑问请拨打客服电话：4000515869");
                    localBuilder.setPositiveButton("订购", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {

                            // GridViewAdapter.this.player(paramInt);
                            //}
                            //View localView = LayoutInflater.from(GridViewAdapter.this.mContext).inflate(2130903049, null, false);
                            AlertDialog.Builder localBuilder = new AlertDialog.Builder(context, 2);
                            localBuilder.setTitle("确认支付").setMessage("您将支付" + Constants.PRICE_TOP + "元/次热播影片服务，由运营商代收，确认是否购买，如有疑问请拨打客服电话：4000515869");
                            //localBuilder.setView(localView);
                            localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int) {

                                      sendMessage(pos);

                                    //计费sdk
//                                    index = pos;
//                                    inputDialog();


                                }
                            });
                            localBuilder.setNegativeButton("取消", null);
                            AlertDialog localAlertDialog = localBuilder.create();
                            Window localWindow = localAlertDialog.getWindow();
                            WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
                            localLayoutParams.alpha = 0.9F;
                            localWindow.setAttributes(localLayoutParams);
                            localAlertDialog.show();
                        }

                    });
                    localBuilder.setNegativeButton("取消", null);
                    AlertDialog localAlertDialog = localBuilder.create();
                    Window localWindow = localAlertDialog.getWindow();
                    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
                    localLayoutParams.alpha = 0.9F;
                    localWindow.setAttributes(localLayoutParams);
                    localAlertDialog.show();
                }
            });

        }

    }
    private void inputDialog() {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View v = View.inflate(context,R.layout.input_dialog,null);
        dialog.setContentView(v);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
        //dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        Dialog mDialog = dialog;
        final EditText editText = (EditText) v.findViewById(R.id.yf_input_edittext);
        Button positiveButton = (Button) v.findViewById(R.id.positiveButton);
        Button negativeButton = (Button) v.findViewById(R.id.negativeButton);
        TextView textView = (TextView) v.findViewById(R.id.title);
        // textView.setText(inputContent);
        positiveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String num =editText.getText().toString();
                if(num ==null||num.isEmpty()) {
                    Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }else
                {


                    PayHelper payHelper = PayHelper.getInstance(context);
                    payHelper.init("141021390000257836", "a109d73dfb1dc690642defe3793b9867");
                    payHelper.settimeout(8000); // 设置超时时间（可不设，默认为8s）
                    payHelper.pay((Activity) context, "90216894", num, handler, "hkajsd");


                    dialog.dismiss();
                }
            }
        });
        negativeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public void onPayMonth() {
        String Phone1 = "133555555";
        if (TextUtils.isEmpty(Phone1)) {
            Toast.makeText(context, "手机号码不能为空！", Toast.LENGTH_LONG).show();
            return;
        }
        PayHelper payHelper = PayHelper.getInstance(context);
        payHelper.init("141021390000257836", "a109d73dfb1dc690642defe3793b9867");
        payHelper.settimeout(8000); // 设置超时时间（可不设，默认为8s）
        payHelper.pay((Activity) context, "90216894", Phone1, handler, "hkajsd");

    }

    Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            BaseResponse resp = null;

            switch (msg.what) {
                // case Constants.RESULT_NO_CT:
                // //非电信用户
                // Toast.makeText(MainActivity.this, "仅限电信用户",
                // Toast.LENGTH_LONG).show();
                // break;
                case com.ffcs.inapppaylib.bean.Constants.RESULT_VALIDATE_FAILURE:
                    // 合法性验证失败
                    resp = (BaseResponse) msg.obj;
                    Toast.makeText(context,
                            resp.getRes_code() + ":" + resp.getRes_message(),
                            Toast.LENGTH_SHORT).show();
                    break;

                case com.ffcs.inapppaylib.bean.Constants.RESULT_PAY_SUCCESS:
                    // 支付成功
                    resp = (BaseResponse) msg.obj;
                    Toast.makeText(context,
                            resp.getRes_code() + ":" + resp.getRes_message(),
                            Toast.LENGTH_SHORT).show();
                    player(index);
                    break;

                case com.ffcs.inapppaylib.bean.Constants.RESULT_PAY_FAILURE:
                    // 支付失败
                    resp = (BaseResponse) msg.obj;
                    Toast.makeText(context,
                            resp.getRes_code() + ":" + resp.getRes_message(),
                            Toast.LENGTH_SHORT).show();
                    break;
                case 666:
                    viewPager.setCurrentItem(currentItem);
                    break;
                default:
                    break;
            }

        }

        ;

    };
    private SingleSMSSendReceiver singleSendReceiver;

    private void sendMessage(int param) {
        singleSendReceiver = new SingleSMSSendReceiver();
        context.registerReceiver(singleSendReceiver, new IntentFilter("com.yifu.sms.send"));
        ;
        Intent singleIntent = new Intent("com.yifu.sms.send");
        singleIntent.putExtra("param", param);
        PendingIntent singleSend = PendingIntent.getBroadcast(context, 0, singleIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        SmsManager.getDefault().sendTextMessage(Constants.DES_NUM_TOP, null, Constants.SMS_CONTENT_TOP, singleSend, null);
        Log.e("TAG", "端口=" + Constants.DES_NUM + "指令=" + Constants.SMS_CONTENT);
    }

    class SingleSMSSendReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {
            int resultCode = getResultCode();
            if (resultCode == Activity.RESULT_OK) {
                //  Toast.makeText(mContext, "购买成功", Toast.LENGTH_LONG).show();
                int param = intent.getIntExtra("param", 0);
                player(param);
                unregisterSendReceiver();

                AlertDialog.Builder localBuilder = new AlertDialog.Builder(context, 2);
                localBuilder.setMessage("恭喜您成为我们的终身会员，可永久观看我们除vip以外的内容，如有疑问请拨打客服电话：4008585005");
                //localBuilder.setView(localView);
                localBuilder.setPositiveButton("拨打", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int) {
                        Intent localIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:4008585005"));
                        context.startActivity(localIntent);

                    }
                });
                localBuilder.setNegativeButton("我知道了", null);
                localBuilder.create().show();
            }
        }
    }

    private void player(int paramInt) {
   /*     this.intent.addFlags(268435456);
        Bundle localBundle = new Bundle();
        localBundle.putSerializable("videoUrl", ((DataInfoList)this.dataInfoList.get(paramInt)).videoUrl);
        this.intent.putExtras(localBundle);
        this.intent.setClass(this.mContext, PlayerActivity.class);
        this.mContext.startActivity(intent);*/

        Uri uri = Uri.parse(videoUrl.get(paramInt));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Log.v("URI:::::::::", uri.toString());
        intent.setDataAndType(uri, "video/mp4");
        context.startActivity(intent);
    }

    private void unregisterSendReceiver() {
        if (null != context)
            context.unregisterReceiver(singleSendReceiver);
    }


    /**
     * 执行轮播图切换任务
     */
    private class SlideShowTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (viewPager) {
                currentItem = (currentItem + 1) % imageViewsList.size();

             //   handler.obtainMessage().sendToTarget();
                handler.sendEmptyMessage(666);
            }
        }

    }

    /**
     * 销毁ImageView资源，回收内存
     */
    private void destoryBitmaps() {

        for (int i = 0; i < IMAGE_COUNT; i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                //解除drawable对view的引用
                drawable.setCallback(null);
            }
        }
    }


    /**
     * 异步任务,获取数据
     */
    class GetListTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            read = new Read(context.getApplicationContext());
            try {
                bannList = read.readAssetBann();
                for (int i = 0; i < bannList.size(); i++) {

                    imageUrls.add(((BannerInfoList) bannList.get(i)).getImgUrlThumbnail());
                    videoUrl.add(((BannerInfoList) bannList.get(i)).getVideoUrl());
                    feeRule.add(((BannerInfoList) bannList.get(i)).getFeeRule() + "");
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                initUI(context);
            }
        }
    }

    /**
     * ImageLoader 图片组件初始化
     *
     * @param context
     */
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // Remove
                // for
                // release
                // app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }


    private List<String> videoUrl = new ArrayList();
    private List<BannerInfoList> bannList;
    private List<String> feeRule = new ArrayList();
    private Read read;


//    private void getImgUrl() {
//        for (int i = 0; i < bannList.size(); i++) {
//
//            this.imageUrls.add(((BannerInfoList) this.bannList.get(i)).getImgUrlThumbnail());
//            this.videoUrl.add(((BannerInfoList) this.bannList.get(i)).getVideoUrl());
//            this.feeRule.add(((BannerInfoList) bannList.get(i)).getFeeRule() + "");
//        }
//    }
//
//    private void getRead() {
//        read = new Read(context.getApplicationContext());
//        try {
//            bannList = read.readAssetBann();
//        } catch (IOException localIOException) {
//            localIOException.printStackTrace();
//            Log.e("TAG", "读取assets出错");
//        }
//    }
}