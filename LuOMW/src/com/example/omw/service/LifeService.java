package com.example.omw.service;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class LifeService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		Log.d("lifeService", "onCreate:我只被调用一次");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("lifeService", "onStartCommend:" + intent.getStringExtra("name"));
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		Log.d("lifeService", "onDestroy:我被销毁了");
	}
}
