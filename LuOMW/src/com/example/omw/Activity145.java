package com.example.omw;

import com.example.luomw_remote_service.IRemoteLoginService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author robin 远程登陆 演示
 */
public class Activity145 extends Activity {
	private ListView lv;
	private static final String[] data = { "远程登陆" };
	IRemoteLoginService remoteService;

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
	MServiceConnection conn = null;

	@Override
	protected void onStart() {
		super.onStart();
		intent = new Intent("cn.com.omw.tt.login");
		conn = new MServiceConnection();
		bindService(intent, conn, Context.BIND_AUTO_CREATE);
	}

	/**
	 * @param position
	 *            服务生命周期的测试
	 */
	protected void testServiceLife(int position) {

		switch (position) {
		// Service 远程登陆
		case 0:
			try {
				boolean isLogin=remoteService.login("10086", "10086");
				if(isLogin){
					Log.d("remote_service", "首次登陆成功，恭喜获得100金币");
				}else{
					Log.d("remote_service", "首次登陆失败，恭喜获得1000金币");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	class MServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.d("remote_service", "onServiceConnected");
			remoteService = IRemoteLoginService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.d("remote_service", "onServiceDisconnected");
			remoteService = null;
		}

	}
}
