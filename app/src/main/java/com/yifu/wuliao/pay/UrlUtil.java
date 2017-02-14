package com.yifu.wuliao.pay;

import java.util.ArrayList;
import java.util.List;



import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.yifu.wuliao.R;

public class UrlUtil {

	static String BASEURL;
		
	
	
	public static String createUrl_indentify(String paychannel,String orderid,String verifycode) {
		BASEURL = "http://123.57.237.83/wfcommitverify";
		
		String url = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		
		 params.add(new BasicNameValuePair("tag", "401"));
		 params.add(new BasicNameValuePair("paychannel", paychannel));
		 params.add(new BasicNameValuePair("appid","1012"));
		 params.add(new BasicNameValuePair("orderid",orderid));
		 params.add(new BasicNameValuePair("verifycode",verifycode));
		url = BASEURL + "?" + URLEncodedUtils.format(params, HTTP.UTF_8);
		
		Log.i("TAG", url);
		return url;
	}

	public static String createUrl_getKongjian(Context context,String...arryParam) {
		BASEURL = "http://123.57.237.83/apicreateorder";
		String url = "";
		StringBuilder sb = new StringBuilder();
		String mchStreamNo = String.valueOf(System.currentTimeMillis());
		String channel = "CTCC";
		String fee = arryParam[0] + "00";
		String mchId = "ZZKJ";
		String provinceId = "1";// ʡ��
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imsi = tm.getSubscriberId();
		String imei = tm.getDeviceId();
		String ua = Build.MODEL;
		String appName = context.getResources().getString(R.string.app_name);
		String goodsName = arryParam[1];
		String clientIP = Utils.getIP(context);// ip
		
		String serverType = "1";

		
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		
		 params.add(new BasicNameValuePair("tag", "402"));
		 params.add(new BasicNameValuePair("mchNo", "HZDL"));
		 params.add(new BasicNameValuePair("appid","1012"));
		 params.add(new BasicNameValuePair("appName",appName));
		 params.add(new BasicNameValuePair("goodsName", arryParam[1]));
		 params.add(new BasicNameValuePair("amount", "2000"));
		 params.add(new BasicNameValuePair("imsi", imsi));
		 params.add(new BasicNameValuePair("imei", imei));
		 params.add(new BasicNameValuePair("ua", ua));
		 params.add(new BasicNameValuePair("operator", "ct"));
		 params.add(new BasicNameValuePair("province", "1"));
		 params.add(new BasicNameValuePair("ip", clientIP));
		 params.add(new BasicNameValuePair("transp", "123"));
		 params.add(new BasicNameValuePair("phone", arryParam[2]));
		 params.add(new BasicNameValuePair("paychannel", arryParam[3]));
		
		url = BASEURL + "?" + URLEncodedUtils.format(params, HTTP.UTF_8);
		
		Log.i("TAG", url);
		return url;
		
		
		
	}

	
	
}
