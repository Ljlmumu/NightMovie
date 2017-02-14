package com.yifu.wuliao.pay;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;



public class HttpClientimp {
	/**
	 * ct_xckjdb1
	 */
	private static final String TAG = "HttpClientimp";
//	public static final String BASEURL = "http://typay.189store.com/api/dx/tykj/new";
//	public static final String BASEURL = "http://123.57.237.83/wfapiSmsReqPay";//xckjdb
	public static final String BASEURL = "http://123.57.237.83/apicreateorder";
	public Context context;

	public static HttpClientimp instace;

	public static HttpClientimp getInstance(Context context) {

		if (instace == null) {
			instace = new HttpClientimp(context);
		}
		return instace;
	}

	public HttpClientimp(Context context) {
		this.context = context;
	}

	public String senGet(String url) {
		
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		StringBuilder result = new StringBuilder();
		try {
			HttpResponse res = client.execute(get);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				InputStreamReader isr = new InputStreamReader(
						entity.getContent(), HTTP.UTF_8);

				BufferedReader bufferedReader = new BufferedReader(isr);

				String line = null;

				while ((line = bufferedReader.readLine()) != null) {
					result.append(line + "\n");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);

		}
		Log.e(TAG, result.toString());
		return result.toString();
	}



	@SuppressLint("NewApi")
	public void parseJson( String url,final Callback callback) {
		
		new AsyncTask<String, Void, String>() {
			ProgressDialog dialog = new ProgressDialog(context);

			protected void onPreExecute() {
				dialog.show();
				dialog.setTitle("请稍候...");
				dialog.setMessage("正在请求中...");
				dialog.setCancelable(true);
			};

			@Override
			protected String doInBackground(String... params) {
				
				String str = senGet(params[0]);
				return str;
			}

			protected void onPostExecute(String result) {
				dialog.cancel();

				//JsontoBean(result,callback);
				JsontoBean_kongjian(result,callback);
			}	

		}.execute(url);
	}

	
	public void JsontoBean(String result,Callback callback){
		SmsCodeResult bean = null;
		try {
			JSONObject json = new JSONObject(result);
			int resCode = json.getInt("resCode");
			if (1 == resCode) {
				bean = new SmsCodeResult();
//				String resMsg = json.getString("resultMsg");
//				JSONObject content = new JSONObject(resMsg);
//				String moSmsPort = content.getString("num");
//				String moSmsCommand = content.getString("sms");
			
				
				//XCKJDB
				String code = json.getString("moSmsCommand");
				String moSmsCommand = URLDecoder.decode(code,
				 "utf-8");
				 String moSmsPort = json.getString("moSmsPort");
				 String paychannel = json.getString("paychannel");
				
				bean.setMoSmsPort(moSmsPort);
				bean.setMoSmsCommand(moSmsCommand);
				callback.onSuccess(bean);
			} else {
				Log.e(TAG, "error");
				callback.onFail("error");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}
	public void  JsontoBean_kongjian(String json,Callback callback){
		BeanGetIndentify bean = new BeanGetIndentify();
		try {
			JSONObject object = new JSONObject(json);
			JSONObject result = object.optJSONObject("result");
			int errorcode = object.optInt("errorcode");
			String errormsg = object.optString("errormsg");
			
			if(result!=null){
				
				if(result.has("orderid")&&result.has("paychannel")){
					bean = new BeanGetIndentify(result.optString("orderid"),result.optString("paychannel"));
					
				}
			}
			bean.errorcode = errorcode;
			bean.errormsg = errormsg;
			callback.onSuccess(bean);
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.i(TAG, json);
	}
	
	
	public interface Callback {
		public void onSuccess(BaseBean result);

		public void onFail(String str);

	}

}
