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
 * @author robin Զ�̵�½ ��ʾ
 */
public class Activity145 extends Activity {
	private ListView lv;
	private static final String[] data = { "Զ�̵�½" };
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
	 *            �����������ڵĲ���
	 */
	protected void testServiceLife(int position) {

		switch (position) {
		// Service Զ�̵�½
		case 0:
			try {
				boolean isLogin=remoteService.login("10086", "10086");
				if(isLogin){
					Log.d("remote_service", "�״ε�½�ɹ�����ϲ���100���");
				}else{
					Log.d("remote_service", "�״ε�½ʧ�ܣ���ϲ���1000���");
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
