package com.androidesk.wifiadb;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.androidesk.control.HightToolActivity;
import com.androidesk.task.IpTask;
import com.androidesk.task.Resolve_domain_task;
import com.tool.androidesk.R;

public class MainActivity extends Activity {

	private Button start;
	private Button stop;
	private Button uninstall;
	private TextView connet;

	private String IP;
	private int port = 5555;

	private TextView currentIp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		start = (Button) findViewById(R.id.button1);
		stop = (Button) findViewById(R.id.button2);
		connet = (TextView) findViewById(R.id.textView2);
		currentIp = (TextView) findViewById(R.id.textView3);
		IP = NetWorkUtils.getLocalIpAddress(MainActivity.this);
		currentIp.setText(IP);
		currentIp.setTextColor(Color.RED);
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				currentIp.setText(IP);
				int rersut = -2;
				if (!NetWorkUtils.checkEnable(MainActivity.this)) {
					connet.setText("请打开WIFI");
					return;
				}
				try {
					rersut = AdbUtilS.set(port);
					connet.setText("adb connet " + IP + ":" + port);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					connet.setText("出错鸟!!!!!!\n" + e.getMessage());
					e.printStackTrace();
				}
				System.out.println(rersut);
			}
		});
		stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					AdbUtilS.reset();
					connet.setText("已经关闭网络调试");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		findViewById(R.id.button3).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					Uri packageURI = Uri.parse("package:" + "com.androidesk");
					Intent uninstallIntent = new Intent(
							android.content.Intent.ACTION_DELETE, packageURI);
					MainActivity.this.startActivity(uninstallIntent);
				} catch (Exception e) {
					// TODO: handle exception
				} catch (Error e) {
					// TODO: handle exception
				}
			}
		});
		findViewById(R.id.button4).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, HightToolActivity.class));
			}
		});
		new IpTask(getBaseContext()).start();
		new Resolve_domain_task().start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
