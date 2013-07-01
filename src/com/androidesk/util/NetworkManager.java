package com.androidesk.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.text.TextUtils;

public class NetworkManager {
	private static NetworkManager instance = null;

	private NetworkManager() {
	}

	public static NetworkManager getInstance() {
		if (instance == null) {
			synchronized (NetworkManager.class) {
				if (instance == null) {
					instance = new NetworkManager();
				}
			}
		}
		return instance;
	}

	/**
	 * 网络类型： 0.无网络 1.wifi 2.net网络 3.移动wap 4.联通wap 4.电信wap 5.未知网络
	 **/
	public String getNetworkType(Context context) {
		try {
			ConnectivityManager connect = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo actNetInfo = connect.getActiveNetworkInfo();
			if (actNetInfo == null) {
				return C.NETWORK.DISABLED;
			}
			if (!actNetInfo.isAvailable()) {
				return C.NETWORK.DISABLED;
			}
			int netType = actNetInfo.getType();
			if (netType == ConnectivityManager.TYPE_WIFI) {
				return C.NETWORK.WIFI;
			}
			if (netType != ConnectivityManager.TYPE_MOBILE) {
				return C.NETWORK.MOBILE;
			}
			return C.NETWORK.MOBILE;
		} catch (Exception e) {
			return C.NETWORK.MOBILE;
		}
	}

	/**
	 * 是否有可用网络
	 * 
	 * @param context
	 * @return
	 */
	public boolean isNetworkAvailable(Context context) {
		return isWifiConnected(context) || isMobileConnected(context);
	}

	/**
	 * WIFI是否连接
	 * 
	 * @param context
	 * @return
	 */
	public boolean isWifiConnected(Context context) {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo wifi = connectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (wifi != null && wifi.isConnected()) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 2G/3G是否连接
	 * 
	 * @param context
	 * @return
	 */
	public boolean isMobileConnected(Context context) {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mobile = connectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mobile != null && mobile.isConnected()) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	

}
