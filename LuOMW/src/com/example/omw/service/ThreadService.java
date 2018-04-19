package com.example.omw.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

/**
 * @author robin
 * 进程和线程 相关的测试：Service
 */
public class ThreadService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// 开启一个线程，每隔一秒打印一次log
				new Thread(new Runnable() {

					@Override
					public void run() {
						for (int i = 0; i < 100; i++) {
							Log.d("lulu","ThreadService"+String.valueOf(i));
							SystemClock.sleep(1000);
						}
					}
				}).start();
		return super.onStartCommand(intent, flags, startId);
	}
}
