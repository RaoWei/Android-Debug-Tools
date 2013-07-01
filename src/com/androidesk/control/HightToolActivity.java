package com.androidesk.control;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tool.androidesk.R;

public class HightToolActivity extends Activity {

	private Button access_domain;
	private Button access_routing;
	private Button resolve_domain;
	private Button ping;

	private Button log_sys;
	private Button log_androidesk;
	private Button log_androidesk_tag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hightool);
		access_domain = (Button) findViewById(R.id.button1);
		access_routing = (Button) findViewById(R.id.button2);
		resolve_domain = (Button) findViewById(R.id.button3);
		ping = (Button) findViewById(R.id.button4);
		log_sys=(Button) findViewById(R.id.button5);
		log_androidesk=(Button) findViewById(R.id.button6);
		log_androidesk_tag=(Button) findViewById(R.id.button6_1);
		
		
		access_domain.setOnClickListener(new MyButtonListener());
		access_routing.setOnClickListener(new MyButtonListener());
		resolve_domain.setOnClickListener(new MyButtonListener());
		ping.setOnClickListener(new MyButtonListener());
		
		
		log_sys.setOnClickListener(new MyButtonTermListener());
		log_androidesk.setOnClickListener(new MyButtonTermListener());
		log_androidesk_tag.setOnClickListener(new MyButtonTermListener());
		findViewById(R.id.button7).setOnClickListener(new MyButtonTermListener());
		findViewById(R.id.button8).setOnClickListener(new MyButtonTermListener());
		findViewById(R.id.button9).setOnClickListener(new MyButtonTermListener());
		findViewById(R.id.button10).setOnClickListener(new MyButtonTermListener());
		findViewById(R.id.button11).setOnClickListener(new MyButtonTermListener());
	}

	class MyButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String key = "";
			switch (v.getId()) {
			case R.id.button1:
				key = "access_domain";
				break;
			case R.id.button2:
				key = "access_routing";
				break;
			case R.id.button3:
				key = "resolve_domain";
				break;
			case R.id.button4:
				key = "ping";
				break;
			default:
				break;
			}
			Intent intent = new Intent(HightToolActivity.this,
					ControlUrlsActivity.class);
			intent.putExtra("key", key);
			startActivity(intent);
		}

	}
	
	class MyButtonTermListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent;
			String command = null;
			String key = "";
			switch (v.getId()) {
			case R.id.button5:
				command="logcat -v time";
				break;
			case R.id.button6:
				command="logcat -v time | grep "+Util.PACKAGE;
				break;
			case R.id.button6_1:
				command="logcat -s "+Util.TAG+" -v time";
				break;
			case R.id.button7:
				command="logcat -s dalvikvm -v time";
				break;
			case R.id.button8:
				command="logcat -s ActivityManager -v time";
				break;
			case R.id.button9:
				command="logcat -s System.err -v time";
				break;
			case R.id.button10:
				command="logcat -s System.out -v time";
				break;
			case R.id.button11:
				command="logcat -s dalvikvm-heap -v time";
				break;
			default:
				break;
			}
			intent = new Intent("jackpal.androidterm.RUN_SCRIPT");
			intent.addCategory(Intent.CATEGORY_DEFAULT);
			intent.putExtra("jackpal.androidterm.iInitialCommand",
					command);
			startActivity(intent);
		}

	}
}
