package com.example.omw;

import com.example.omw.service.LifeBindService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Activity143 extends Activity {
	private ListView lv;
	private static final String[] data = { "�󶨷���", "����󶨷���", "��ʼ����", "��������"};

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

	}

	Intent intent = null;
	MyServiceConnection conn = null;

	/**
	 * @param position
	 *            �����������ڵĲ���
	 */
	protected void testServiceLife(int position) {

		switch (position) {
		// Service �󶨷���
		case 0:
			intent = new Intent(Activity143.this, LifeBindService.class);
			conn = new MyServiceConnection();
			bindService(intent, conn, Context.BIND_AUTO_CREATE);
			break;
		// ����󶨷���
		case 1:
			if (conn != null) {
				unbindService(conn);
				conn = null;
			}
			break;
		// ��������
		case 2:
			intent = new Intent(Activity143.this, LifeBindService.class);
			startService(intent);
			break;
		// ��������
		case 3:
			if (intent != null) {
				stopService(intent);
			}
			break;
		default:
			break;
		}
	}

	class MyServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.d("bindService", "onServiceConnected");
			LifeBindService.MBinder binder = (LifeBindService.MBinder) service;
			binder.playMusic();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.d("bindService", "onServiceDisconnected");
		}

	}

//	@Override
//	protected void onDestroy() {
//		// android.app.ServiceConnectionLeaked: that was originally bound here
//		// �������쳣��ԭ������activity���ٵ�ʱ��û�е���unbindService
//		unbindService(conn);
//		super.onDestroy();
//	}

}
