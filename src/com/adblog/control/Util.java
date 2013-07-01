package com.adblog.control;

import java.math.BigInteger;
import java.security.MessageDigest;

import com.adblog.util.DisplayManager;
import com.adblog.util.NetworkManager;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public class Util {
	/**
	 * 日志过滤  信息
	 */
	public static final String TAG="Androidesk";
	public static final String PACKAGE="com.androidesk";
	
	
	
	
	
	public static String IP = "";
    public static StringBuffer resolve_domain;
	private static String getLocalIpAddress(Context context) {
		try {
			WifiManager wifiManager = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			int ipInt = wifiInfo.getIpAddress();
			StringBuilder sb = new StringBuilder();
			sb.append(ipInt & 0xFF).append(".");
			sb.append((ipInt >> 8) & 0xFF).append(".");
			sb.append((ipInt >> 16) & 0xFF).append(".");
			sb.append((ipInt >> 24) & 0xFF);
			return sb.toString();
		} catch (Exception ex) {
		}
		return "UNKONWN";
	}

	public static String getIp(Context context) {
		String localIp = IP;
		if (IP == null || IP.equals("")) {
			localIp = getLocalIpAddress(context);
		}
		return localIp;
	}
    public static String getTitle() {
		return (android.os.Build.BRAND+" "+android.os.Build.DEVICE);
	}
	public static String getDevicesInfo(Context context) {
		DisplayManager dm = DisplayManager.getInstance();
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nBRAND:   " + android.os.Build.BRAND);
		buffer.append("\nDEVICE:   " + android.os.Build.DEVICE);
		buffer.append("\nSDK:   " + android.os.Build.VERSION.SDK);
		buffer.append("\n分辨率" + dm.getDisplayWidth(context) + "*"
				+ dm.getDisplayHeight(context) + "   壁纸分辨率"
				+ dm.getWallpaperWidth(context) + "*"
				+ dm.getWallpaperHeight(context));
        buffer.append("\n网络类型:  "+NetworkManager.getInstance().getNetworkType(context));
		TelephonyManager tManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = "";
		String androidid = null;
		try {
			deviceId = getUniqueID(context);

			androidid = Settings.Secure.getString(context.getContentResolver(),
					Settings.Secure.ANDROID_ID);
		} catch (Exception e) {
			// TODO: handle exception
		}
		buffer.append("\nDeviceId:   " + getUniqueID(context));
		WifiManager wm = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		String MAC = wm.getConnectionInfo().getMacAddress();
		buffer.append("\n IP:   "+IP);
		buffer.append("\n\n\n 域名解析************************************* "+resolve_domain+"\n\n");
		return buffer.toString();

	}

	public static String getUniqueID(Context ctx) {
		TelephonyManager tm = (TelephonyManager) ctx
				.getSystemService(Context.TELEPHONY_SERVICE);
		String device_id = "";
		try {
			device_id = tm.getDeviceId();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		if (device_id == null) {
			try {
				WifiManager wifi = (WifiManager) ctx
						.getSystemService(Context.WIFI_SERVICE);
				WifiInfo info = wifi.getConnectionInfo();
				device_id = digest(info.getMacAddress());
			} catch (Exception e) {
				device_id = "";
			}
		}

		return device_id;
	}

	/**
	 * MD5
	 * 
	 * @param input
	 * @return
	 */
	private static String digest(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return new BigInteger(1, md.digest(input.getBytes())).toString(16)
					.toUpperCase();
		} catch (Exception e) {
			return "";
		}
	}
}
