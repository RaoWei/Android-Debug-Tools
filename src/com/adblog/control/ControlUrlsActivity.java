package com.adblog.control;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tool.androidesk.R;

public class ControlUrlsActivity extends Activity {
	private Button aoi;
	private Button service;
	private Button s_cdn;
	private Button img0_cdn;
	private String key = "";
	private int flag = -1;
	private final String aoi_url = "域名1";
	private final String service_url = "域名2";
	private final String s_cdn_url = "域名3";
	private final String img0_cdn_url = "域名4";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_point);
		key = getIntent().getStringExtra("key");
		if (key.equals("access_domain")) {
			this.setTitle("************域名访问************");
			flag = 0;
		}
		if (key.equals("access_routing")) {
			this.setTitle("************路由追踪************");
			flag = 1;
		}
		if (key.equals("resolve_domain")) {
			this.setTitle("************域名解析************");
			flag = 2;
		}
		if (key.equals("ping")) {
			this.setTitle("************Ping************");
			flag = 3;
		}
		aoi = (Button) findViewById(R.id.button1);
		service = (Button) findViewById(R.id.button2);
		s_cdn = (Button) findViewById(R.id.button3);
		img0_cdn = (Button) findViewById(R.id.button4);

		aoi.setOnClickListener(new MyButtonListener());
		service.setOnClickListener(new MyButtonListener());
		s_cdn.setOnClickListener(new MyButtonListener());
		img0_cdn.setOnClickListener(new MyButtonListener());
	}

	class MyButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = null;
			String command = null;
			switch (v.getId()) {
			case R.id.button1:

				switch (flag) {

				case 0:
					intent = new Intent(ControlUrlsActivity.this,
							WebViewActivity.class);
					intent.putExtra("key", aoi_url);
					startActivity(intent);
					break;

				case 1:
					intent = new Intent("jackpal.androidterm.RUN_SCRIPT");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					command = "traceroute " + Util.server1;
					intent.putExtra("jackpal.androidterm.iInitialCommand",
							command);
					startActivity(intent);

					break;
				case 2:
					intent = new Intent("jackpal.androidterm.RUN_SCRIPT");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					command = "nslookup " + Util.server1;
					intent.putExtra("jackpal.androidterm.iInitialCommand",
							command);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent("jackpal.androidterm.RUN_SCRIPT");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					command = "ping " + Util.server1;
					intent.putExtra("jackpal.androidterm.iInitialCommand",
							command);
					startActivity(intent);
					break;
				default:
					break;
				}
				break;
			case R.id.button2:
				switch (flag) {
				case 0:
					intent = new Intent(ControlUrlsActivity.this,
							WebViewActivity.class);
					intent.putExtra("key", service_url);
					startActivity(intent);
					break;

				case 1:
					intent = new Intent("jackpal.androidterm.RUN_SCRIPT");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					command = "traceroute " + Util.server2;
					intent.putExtra("jackpal.androidterm.iInitialCommand",
							command);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent("jackpal.androidterm.RUN_SCRIPT");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					command = "nslookup " + Util.server2;
					intent.putExtra("jackpal.androidterm.iInitialCommand",
							command);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent("jackpal.androidterm.RUN_SCRIPT");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					command = "ping " + Util.server2;
					intent.putExtra("jackpal.androidterm.iInitialCommand",
							command);
					startActivity(intent);
					break;
				default:
					break;
				}
				break;
			case R.id.button3:
				switch (flag) {
				case 0:
					intent = new Intent(ControlUrlsActivity.this,
							WebViewActivity.class);
					intent.putExtra("key", s_cdn_url);
					startActivity(intent);
					break;

				case 1:
					intent = new Intent("jackpal.androidterm.RUN_SCRIPT");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					command = "traceroute " + Util.server3;
					intent.putExtra("jackpal.androidterm.iInitialCommand",
							command);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent("jackpal.androidterm.RUN_SCRIPT");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					command = "nslookup " + Util.server3;
					intent.putExtra("jackpal.androidterm.iInitialCommand",
							command);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent("jackpal.androidterm.RUN_SCRIPT");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					command = "ping " + Util.server3;
					intent.putExtra("jackpal.androidterm.iInitialCommand",
							command);
					startActivity(intent);
					break;
				default:
					break;
				}
				break;
			case R.id.button4:
				switch (flag) {
				case 0:
					intent = new Intent(ControlUrlsActivity.this,
							WebViewActivity.class);
					intent.putExtra("key", img0_cdn_url);
					startActivity(intent);
					break;

				case 1:
					intent = new Intent("jackpal.androidterm.RUN_SCRIPT");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					command = "traceroute " + Util.server4;
					intent.putExtra("jackpal.androidterm.iInitialCommand",
							command);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent("jackpal.androidterm.RUN_SCRIPT");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					command = "nslookup " + Util.server4;
					intent.putExtra("jackpal.androidterm.iInitialCommand",
							command);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent("jackpal.androidterm.RUN_SCRIPT");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					command = "ping " + Util.server4;
					intent.putExtra("jackpal.androidterm.iInitialCommand",
							command);
					startActivity(intent);
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
		}

	}
}
