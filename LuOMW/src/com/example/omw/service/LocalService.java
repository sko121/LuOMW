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
		Log.d("transact","onBind()-->被调用");
		return mBuilder;
	}
	@Override
	public void onCreate() {
		Log.d("transact","onCreate()-->被调用");
		super.onCreate();
	}
	public class LocalBinder extends Binder{
		
		LocalService getService(){
			//返回一个LocalService的对象
			return LocalService.this;
		}

		/* 
		 * data:从其他组件获得数据，使用read方法
		 * reply:向其他组件发送数据，使用write方法
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
		Log.d("transact","onUnbind()-->被调用");
		return super.onUnbind(intent);
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("transact","onDestroy()-->被调用");
	}
	

}
