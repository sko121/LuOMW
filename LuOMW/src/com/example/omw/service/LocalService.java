package com.example.omw.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public class LocalService extends Service{
	private LocalBinder mBuilder = new LocalBinder();
	@Override
	public IBinder onBind(Intent intent) {
		Log.d("transact","onBind()-->������");
		return mBuilder;
	}
	@Override
	public void onCreate() {
		Log.d("transact","onCreate()-->������");
		super.onCreate();
	}
	public class LocalBinder extends Binder{
		
		LocalService getService(){
			//����һ��LocalService�Ķ���
			return LocalService.this;
		}

		/* 
		 * data:���������������ݣ�ʹ��read����
		 * reply:����������������ݣ�ʹ��write����
		 */
		@Override
		protected boolean onTransact(int code, Parcel data, Parcel reply,
				int flags) throws RemoteException {
			Log.d("transact","Service--name-->" + data.readString());
			Log.d("transact","Service--age-->" + data.readInt());
			reply.writeString("mary");
			reply.writeInt(30);
			return super.onTransact(code, data, reply, flags);
		}
		
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Log.d("transact","onUnbind()-->������");
		return super.onUnbind(intent);
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("transact","onDestroy()-->������");
	}
	

}
