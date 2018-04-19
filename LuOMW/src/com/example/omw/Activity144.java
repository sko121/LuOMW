package com.example.omw;

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

import com.example.omw.service.LocalService;

/**
 * @author robin LinearLayout 演示
 */
public class Activity144 extends Activity {
	private ListView lv;
	private static final String[] data = { "绑定服务" };

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

	/**
	 * @param position
	 *            服务生命周期的测试
	 */
	protected void testServiceLife(int position) {

		switch (position) {
		// Service 绑定服务
		case 0:
			intent = new Intent(Activity144.this, LocalService.class);
			conn = new MServiceConnection();
			bindService(intent, conn, Context.BIND_AUTO_CREATE);
			break;
		}
	}

	class MServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.d("transact", "onServiceConnected");
			LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
			Parcel data = Parcel.obtain();
			data.writeString("tom");
			data.writeInt(20);
			Parcel reply = Parcel.obtain();
			try {
				binder.transact(IBinder.FIRST_CALL_TRANSACTION, data, reply, 0);
				Log.d("transact", "Activity--name-->" + reply.readString());
				Log.d("transact", "Activity--age-->" + reply.readInt());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			data.recycle();
			reply.recycle();

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.d("transact", "onServiceDisconnected");
		}

	}
}
