package com.androidesk.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.androidesk.control.Util;
import com.androidesk.util.NetworkManager;

import android.content.Context;

/**
 * 3个获取IP的地址,要是3个都获取不到,那只有悲剧了的获取局域网IP
 * 
 * @author raowei
 * 
 */
public class IpTask extends Thread {

	String myip = null;
	String[] ipurls = { "http://iframe.ip138.com/ic.asp",
			"http://members.3322.org/dyndns/getip", "http://ifconfig.me/ip" };
	String[] matchs = { "\\[([\\d\\.]+)" };

	private Context context;

	public IpTask(Context context) {
		super();
		this.context = context;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String ipString = null;
		try {
			ipString = getip138Content(ipurls[0], matchs[0]);
			if (checkNull(ipString)) {
				ipString = getipCommonContent(ipurls[1]);
			}
			if (checkNull(ipString)) {
				ipString = getipCommonContent(ipurls[2]);
			}
			Util.IP = ipString;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.run();
	}

	private String getUrlString(String strUrl) throws Exception {
		URL url = new URL(strUrl);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				url.openStream(), "gb2312"));
		String s = "";
		StringBuffer sb = new StringBuffer("");
		String result = null;
		int flag = 0;
		while ((s = br.readLine()) != null) {
			sb.append(s + "/r/n");
			flag++;
			if (flag > 20) {// 访问的页面出错了,或者文字太多了抛弃不要了
				return null;
			}
		}
		br.close();
		result = sb.toString();
		return result;
	}

	/**
	 * 方法1
	 * 
	 * @param url
	 * @param matcherString
	 * @return
	 */
	private String getip138Content(String url, String matcherString) {
		try {
			String result = getUrlString(url);
			if (result == null) {
				return null;
			}
			int end = result.lastIndexOf("</center>");
			int start = result.indexOf("<center>");
			result = result.substring(start + 8, end);
			// Pattern pattern = Pattern.compile(matcherString);
			// Matcher matcher = pattern.matcher(result);
			// if (matcher.find()) {
			// return matcher.group(1);
			// }
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 方法2
	 * 
	 * @param url
	 * @return
	 */
	private String getipCommonContent(String url) {
		try {
			String result = getUrlString(url);
			if (result == null) {
				return null;
			}
			return result.replaceAll("/r/n", "");
		} catch (Exception e) {
			return null;
		}
	}

	private boolean checkNull(String string) {
		if (string == null || string.equals("")) {
			return true;
		}
		return false;
	}
}
