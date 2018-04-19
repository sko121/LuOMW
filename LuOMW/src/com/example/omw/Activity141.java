package com.example.omw;

import com.example.omw.service.ThreadService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author robin Service 进程和线程 演示
 */
public class Activity141 extends Activity {
	private ListView lv;
	private static final String[] data = { "startService", "stopService" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
		lv = (ListView) findViewById(R.id.lv);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, data);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				testServiceLife(position);
			}
		});

		// 开启一个线程，每隔一秒打印一次log
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 288; i++) {
					Log.d("lulu","Activity141"+String.valueOf(i));
					SystemClock.sleep(1000);
				}
			}
		}).start();

	}

	/**
	 * @param position
	 *            服务生命周期的测试
	 */
	protected void testServiceLife(int position) {
		Intent intent = null;
		switch (position) {
		case 0:
			// 开启服务
			intent = new Intent();
			intent.setClass(Activity141.this, ThreadService.class);
			startService(intent);
			break;
		case 1:
			// 关闭服务
			intent = new Intent();
			intent.setClass(Activity141.this, ThreadService.class);
			stopService(intent);
			break;
		default:
			break;
		}
	}
}
