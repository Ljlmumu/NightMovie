package com.yifu.wuliao.pay;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class Utils {
	public static String getWifiIpAddress(Context context) {
		// try {
		// for (Enumeration<NetworkInterface> en =
		// NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
		// NetworkInterface intf = en.nextElement();
		// for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
		// enumIpAddr.hasMoreElements();) {
		// InetAddress inetAddress = enumIpAddr.nextElement();
		// if (!inetAddress.isLoopbackAddress()) {
		// return inetAddress.getHostAddress().toString();
		// }
		// }
		// }
		// } catch (SocketException ex) {
		// //Log.e(LOG_TAG, ex.toString());
		// }
		// return null;
		// }
		//
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		// �ж�wifi�Ƿ���
		if (!wifiManager.isWifiEnabled()) {
			wifiManager.setWifiEnabled(true);
		}
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ipAddress = wifiInfo.getIpAddress();
		String ip = intToIp(ipAddress);
		return ip;
	}

	private static String intToIp(int i) {

		return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
				+ "." + (i >> 24 & 0xFF);
	}

	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("WifiPreference IpAddress", ex.toString());
		}
		return null;
	}

	public static String getIP(Context context) {
		String ip ="";
		int state =checkNetworkAvailable(context);
		if(state ==1){//wifi
			ip=getWifiIpAddress(context);
		}else if(2== state){
			ip=getLocalIpAddress();
		}
		return ip;

	}
	
	
	
	public static int checkNetworkAvailable(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);  
          
       final android.net.NetworkInfo wifi =connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);  
       final android.net.NetworkInfo mobile =connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);  
  
       if(wifi.isAvailable()){
               return 1;  
               
       }else if(mobile.isAvailable()){
               return 2;  
               
       }else{
        return 0;
       }
}
}
