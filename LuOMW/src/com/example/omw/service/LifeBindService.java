package com.example.omw.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LifeBindService extends Service {
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("bindService","LifeBindService:onCreate");
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.d("bindService","LifeBindService:onBind");
		return new MBinder();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("bindService","LifeBindService:onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onRebind(Intent intent) {
		Log.d("bindService","LifeBindService:onRebind");
		super.onRebind(intent);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.d("bindService","LifeBindService:onUnbind");
		return super.onUnbind(intent);
		
	}
	@Override
	public void onDestroy() {
		Log.d("bindService","LifeBindService:onDestroy");
		super.onDestroy();
	}

	private void playMusic() {
		Log.d("bindService","ÖÐ¹úºÃÉùÒô");
	}

	public class MBinder extends Binder {
		public void playMusic() {
			LifeBindService.this.playMusic();
		}
	}
}
