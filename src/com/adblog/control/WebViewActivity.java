package com.adblog.control;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.tool.androidesk.R;

public class WebViewActivity extends Activity {
  private WebView webView;
  private Client listenClient;
  private String url="www.aoi.androidesk.com";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		url=getIntent().getStringExtra("key");
		setContentView(R.layout.activity_webview);
		webView = (WebView) findViewById(R.id.webView1);
		this.webView = (WebView)findViewById(R.id.webView1);
		this.webView.setScrollBarStyle(0);
		this.webView.setDownloadListener(new DownloadListener() {
			public void onDownloadStart(String url, String userAgent,
					String contentDisposition, String mimetype,
					long contentLength) {
				Intent intent = new Intent(Intent.ACTION_VIEW);

				intent.setData(Uri.parse(url));
				startActivity(intent);
			}
		});

		this.webView.getSettings().setJavaScriptEnabled(true);
		this.listenClient = new Client(WebViewActivity.this);
		this.webView.setWebViewClient(this.listenClient);
		this.webView.addJavascriptInterface(this.listenClient, "Android");
		this.webView.setWebChromeClient(new WebChromeClient() {
			private CountDownTimer loadCDT = null; // 加载倒计时

			@Override
			public void onProgressChanged(final WebView view, int progress) {
			}
		});

		this.webView.loadUrl(url);
	}


}
