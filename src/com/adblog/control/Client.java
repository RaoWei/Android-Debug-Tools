package com.adblog.control;

import java.net.URLDecoder;

import android.content.Context;
import android.os.Message;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 用以监听url
 * 
 * @since 2012-03-23
 */
public class Client extends WebViewClient {
	private Context ctx; // 关联的Activity的上下文

	public Client(Context ctx) {
		this.ctx = ctx;
	}

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		try {
			url = URLDecoder.decode(url, "UTF-8");
			view.loadUrl(url);
		} catch (Exception e) {
			return false;
		}
		return true;

	}


	@Override
	public void onPageFinished(WebView view, String url) {
		
	}

	@Override
	public void onFormResubmission(WebView view, Message dontResend,
			Message resend) {
		resend.sendToTarget();
	}

}