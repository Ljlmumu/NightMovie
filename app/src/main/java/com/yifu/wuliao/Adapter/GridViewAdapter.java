package com.yifu.wuliao.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.yifu.wuliao.Constants;
import com.yifu.wuliao.R;
import com.yifu.wuliao.bean.DataInfoList;
import com.yifu.wuliao.pay.BaseBean;
import com.yifu.wuliao.pay.BeanGetIndentify;
import com.yifu.wuliao.pay.HttpClientimp;
import com.yifu.wuliao.pay.UrlUtil;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private List<DataInfoList> dataInfoList;
    private com.nostra13.universalimageloader.core.ImageLoader imageLoader;
    private Intent intent = new Intent();
    private Context mContext;
    private DisplayImageOptions options;
    private int payType;
    private RequestQueue queue;

    public GridViewAdapter(Context paramContext, List<DataInfoList> paramList) {
        this.mContext = paramContext;
        this.queue = Volley.newRequestQueue(paramContext);
        imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(paramContext));
        this.dataInfoList = paramList;
        this.options = new DisplayImageOptions.Builder().showStubImage(2130837558).showImageForEmptyUri(2130837558).showImageOnFail(2130837558).cacheInMemory(true).cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).build();
    }

    private void player(int paramInt) {
   /*     this.intent.addFlags(268435456);
        Bundle localBundle = new Bundle();
        localBundle.putSerializable("videoUrl", ((DataInfoList)this.dataInfoList.get(paramInt)).videoUrl);
        this.intent.putExtras(localBundle);
        this.intent.setClass(this.mContext, PlayerActivity.class);
        this.mContext.startActivity(intent);*/

        Uri uri = Uri.parse(dataInfoList.get(paramInt).videoUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Log.v("URI:::::::::", uri.toString());
        intent.setDataAndType(uri, "video/mp4");

        mContext.startActivity(intent);
    }

    public int getCount() {
        return this.dataInfoList.size();
    }

    public Object getItem(int paramInt) {
        return this.dataInfoList.get(paramInt);
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup) {
        ViewHolder localViewHolder = new ViewHolder();
        View localView = LayoutInflater.from(this.mContext).inflate(R.layout.gd_item, null, false);
        localViewHolder.it_tv1 = ((TextView) localView.findViewById(R.id.it_tv1));
        localViewHolder.it_tv2 = ((TextView) localView.findViewById(R.id.it_tv2));
        localViewHolder.vipImgView = ((ImageView) localView.findViewById(R.id.vipImaView));
        localViewHolder.imgView = ((ImageView) localView.findViewById(R.id.imgView));
        localView.setTag(localViewHolder);
        final String message;

        if (((DataInfoList) this.dataInfoList.get(paramInt)).feeRule == 2) {
            ImageView localImageView = localViewHolder.vipImgView;
            localImageView.setVisibility(View.VISIBLE);
            message = "您将支付" + Constants.PRICE_VIP + "元/月购买VIP会员服务，由运营商代收，确认是否购买，如有疑问请拨打客服电话：4000515869";
        } else {
            message = "您将支付" + Constants.PRICE + "元/月购买会员服务，由运营商代收，确认是否购买，如有疑问请拨打客服电话：4000515869";
        }
        String str = ((DataInfoList) dataInfoList.get(paramInt)).imgUrl1;
        localViewHolder.imgView.setTag(str);
        imageLoader.displayImage(str, localViewHolder.imgView, this.options);
//        ImageLoader.ImageListener listener = ImageLoader.getImageListener(localViewHolder.vipImgView, R.drawable.recommend_img_default, R.drawable.recommend_img_default);
//        imageLoader.get(str,listener,300,300);
        localViewHolder.it_tv1.setText(((DataInfoList) this.dataInfoList.get(paramInt)).title);
        localViewHolder.it_tv2.setText(((DataInfoList) this.dataInfoList.get(paramInt)).description);
        localView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"NewApi"})
            public void onClick(View paramAnonymousView) {
                AlertDialog.Builder localBuilder = new AlertDialog.Builder(GridViewAdapter.this.mContext, 2);
                localBuilder.setTitle("VIP订购").setMessage(message);
                localBuilder.setPositiveButton("订购", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {

                        AlertDialog.Builder localBuilder = new AlertDialog.Builder(GridViewAdapter.this.mContext, 2);
                        localBuilder.setTitle("确认支付").setMessage(message);
                        localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int) {

                              //  sendMessage(paramInt, paramInt);

                                //计费sdk

                                // onPayMonth();
//                                index=paramInt;
//                                inputDialog();
                                inputDialog(false);

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
        return localView;
    }

    private class ChoiceOnClickListener
            implements DialogInterface.OnClickListener {
        private int which = 0;

        private ChoiceOnClickListener() {
        }

        public int getWhich() {
            return this.which;
        }

        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
            this.which = paramInt;
        }
    }

    private class ViewHolder {
        private ImageView imgView;
        private TextView it_tv1;
        private TextView it_tv2;
        private ImageView vipImgView;


    }

    private SingleSMSSendReceiver singleSendReceiver;
    AccBroadcastReceiver accBroadcastReceiver;

    private void sendMessage(int param, int index) {
        singleSendReceiver = new SingleSMSSendReceiver();
        accBroadcastReceiver = new AccBroadcastReceiver();
        mContext.registerReceiver(singleSendReceiver, new IntentFilter("com.yifu.sms.send"));


        String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";
        Intent deliverIntent = new Intent(DELIVERED_SMS_ACTION);
        PendingIntent deliverPI = PendingIntent.getBroadcast(mContext, 0,
                deliverIntent, 0);
        mContext.registerReceiver(accBroadcastReceiver, new IntentFilter(DELIVERED_SMS_ACTION));

        Intent singleIntent = new Intent("com.yifu.sms.send");
        singleIntent.putExtra("param", param);
        PendingIntent singleSend = PendingIntent.getBroadcast(mContext, 0, singleIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        if ((this.dataInfoList.get(index)).feeRule == 2) {//vip

            SmsManager.getDefault().sendTextMessage(Constants.DES_NUM_VIP, null, Constants.SMS_CONTENT_VIP, singleSend, deliverPI);
        } else {
            SmsManager.getDefault().sendTextMessage(Constants.DES_NUM, null, Constants.SMS_CONTENT, singleSend, deliverPI);

        }
    }

    class SingleSMSSendReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            int resultCode = getResultCode();
            if (resultCode == Activity.RESULT_OK) {
                unregisterSendReceiver();

                AlertDialog.Builder localBuilder = new AlertDialog.Builder(GridViewAdapter.this.mContext, 2);
                localBuilder.setMessage("恭喜您成为我们的终身会员，可永久观看我们除vip以外的内容，如有疑问请拨打客服电话：4000515869");
                //localBuilder.setView(localView);
                localBuilder.setPositiveButton("拨打", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int) {
                        Intent localIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:4000515869"));
                        context.startActivity(localIntent);

                    }
                });
                localBuilder.setNegativeButton("我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int param = intent.getIntExtra("param", 0);
                        GridViewAdapter.this.player(param);
                    }
                });
                localBuilder.create().show();
            }
        }
    }

    class AccBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context _context, Intent _intent) {
            Log.e("TAG", "收信人已经成功接收");
            mContext.unregisterReceiver(accBroadcastReceiver);
        }

    }

    private void unregisterSendReceiver() {
        if (null != mContext)
            mContext.unregisterReceiver(singleSendReceiver);


    }

    private String paychannel = "ct_zykfpt_by";
    private String orderid;

    private int price = 20;

    private void inputDialog(final boolean isYanzhengma) {
        //
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View v = View.inflate(mContext, R.layout.input_dialog, null);
        if (isYanzhengma) {
            EditText editText = (EditText) v.findViewById(R.id.yf_input_edittext);
            TextView title = (TextView) v.findViewById(R.id.title);
            editText.setHint("请输入短信验证码");
            title.setText("请输入短信验证码");
        }
        dialog.setContentView(v);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
        // dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        Dialog mDialog = dialog;
        final EditText editText = (EditText) v
                .findViewById(R.id.yf_input_edittext);
        Button positiveButton = (Button) v.findViewById(R.id.positiveButton);
        Button negativeButton = (Button) v.findViewById(R.id.negativeButton);
        TextView textView = (TextView) v.findViewById(R.id.title);
        // textView.setText(inputContent);
        positiveButton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                String num = editText.getText().toString();
                if (num == null || num.isEmpty()) {
                    Toast.makeText(mContext, "不能为空", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    if (isYanzhengma) {
                        sendYanzhengma(paychannel, orderid, num);

                    } else {
                        requestCode(num);
                    }

                    dialog.dismiss();
                }
            }
        });
        negativeButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void requestCode(String phone) {
        String url = UrlUtil.createUrl_getKongjian(mContext, price + "", "VIP会员", phone, paychannel);
        HttpClientimp.getInstance(mContext).parseJson(url,
                new HttpClientimp.Callback() {

                    public void onSuccess(BaseBean result) {
//						if (null != result) {
//							code = result.getMoSmsCommand();
//							dest = result.getMoSmsPort();
//							content = "";
                        //sendMessage();
//						} else {
//							Toast.makeText(context, "获取失败",
//									Toast.LENGTH_SHORT).show();
//						}


                        BeanGetIndentify beanGetIndentify = (BeanGetIndentify) result;

                        if (beanGetIndentify.errorcode == 0) {
                            orderid = beanGetIndentify.getOrderid();
                            paychannel = beanGetIndentify.getPaychannel();
                            inputDialog(true);
                        } else {
                            Toast.makeText(mContext, beanGetIndentify.errormsg, Toast.LENGTH_SHORT).show();
                        }


                    }

                    public void onFail(String str) {
                        Toast.makeText(mContext, str,
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
    }

    protected void sendYanzhengma(String paychannel, String orderid, String indentify) {
        String url = UrlUtil.createUrl_indentify(paychannel, orderid, indentify);
        HttpClientimp.getInstance(mContext).parseJson(url,
                new HttpClientimp.Callback() {

                    public void onSuccess(BaseBean result) {
//

                    }

                    public void onFail(String str) {
                        Toast.makeText(mContext, str,
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
    }
//    public void onPayMonth() {
//        String Phone1 = "17310086463";
//
//        if(TextUtils.isEmpty(Phone1)){
//            Toast.makeText(mContext,"手机号码不能为空！", Toast.LENGTH_LONG).show();
//            return;
//        }
//        PayHelper payHelper = PayHelper.getInstance(mContext);
//        payHelper.init("141021390000257836", "c4b820bc057e6f6a5eaa9a0725c401d0");
//        payHelper.settimeout(8000); // 设置超时时间（可不设，默认为8s）
//        payHelper.pay((Activity) mContext, "90216893",Phone1, handler, "hkajsd");
//
//    }

    //    private void inputDialog() {
//
//        final Dialog dialog = new Dialog(mContext);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        View v = View.inflate(mContext,R.layout.input_dialog,null);
//        dialog.setContentView(v);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//        dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
//        //dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(false);
//        Dialog mDialog = dialog;
//        final EditText editText = (EditText) v.findViewById(R.id.yf_input_edittext);
//        Button positiveButton = (Button) v.findViewById(R.id.positiveButton);
//        Button negativeButton = (Button) v.findViewById(R.id.negativeButton);
//        TextView textView = (TextView) v.findViewById(R.id.title);
//        // textView.setText(inputContent);
//        positiveButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                String num =editText.getText().toString();
//              if(num ==null||num.isEmpty()) {
//                  Toast.makeText(mContext, "手机号不能为空", Toast.LENGTH_SHORT).show();
//              }else
//                {
//
//
//                    PayHelper payHelper = PayHelper.getInstance(mContext);
//                    payHelper.init("141021390000257836", "a109d73dfb1dc690642defe3793b9867");
//                    payHelper.settimeout(8000); // 设置超时时间（可不设，默认为8s）
//                    payHelper.pay((Activity) mContext, "90216893", num, handler, "hkajsd");
//
//
//                    dialog.dismiss();
//                }
//            }
//        });
//        negativeButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//
//    }
    private int index;
//    Handler handler = new Handler() {
//
//        public void handleMessage(android.os.Message msg) {
//
//            BaseResponse resp = null;
//
//            switch (msg.what) {
//                // case Constants.RESULT_NO_CT:
//                // //非电信用户
//                // Toast.makeText(MainActivity.this, "仅限电信用户",
//                // Toast.LENGTH_LONG).show();
//                // break;
//                case com.ffcs.inapppaylib.bean.Constants.RESULT_VALIDATE_FAILURE:
//                    // 合法性验证失败
//                    resp = (BaseResponse) msg.obj;
//                    Toast.makeText(mContext,
//                            resp.getRes_code() + ":" + resp.getRes_message(),
//                            Toast.LENGTH_LONG).show();
//                    break;
//
//                case com.ffcs.inapppaylib.bean.Constants.RESULT_PAY_SUCCESS:
//                    // 支付成功
//                    resp = (BaseResponse) msg.obj;
//                    Toast.makeText(mContext,
//                            resp.getRes_code() + ":" + resp.getRes_message(),
//                            Toast.LENGTH_LONG).show();
//                    GridViewAdapter.this.player(index);
//                    break;
//
//                case com.ffcs.inapppaylib.bean.Constants.RESULT_PAY_FAILURE:
//                    // 支付失败
//                    resp = (BaseResponse) msg.obj;
//                    Toast.makeText(mContext,
//                            resp.getRes_code() + ":" + resp.getRes_message(),
//                            Toast.LENGTH_LONG).show();
//                    break;
//
//                default:
//                    break;
//            }
//
//        };
//
//    };
}