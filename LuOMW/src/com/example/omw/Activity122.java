package com.example.omw;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

public class Activity122 extends Activity{
	private NotificationManager notificationManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qqframe);
		
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				SystemClock.sleep(1 * 1000);
				NotificationCompat.Builder builder = new NotificationCompat.Builder(Activity122.this);
				Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + 
						"://" + getPackageName() + "/" + R.raw.alert);
				Notification notification = builder
						.setSmallIcon(R.drawable.amassing_money_activity_icon)
						.setContentText(	
								"�����˻�5678���յ�10000000000�� �������Ϊ:1000000000000")
						.setContentTitle(
								"�����˻�5678���յ�10000000000�� �������Ϊ:1000000000000")
						.setSound(sound)
						.setDefaults(Notification.DEFAULT_VIBRATE)
						.setAutoCancel(true).build();
				notificationManager.notify(0, notification);
				Uri uri = Uri.parse("content://sms");
				ContentValues value = new ContentValues();
				value.put("address", "95555");
				value.put("type", "1");
				value.put("body", "���й�ũҵ���С���ǰ����ѯ��β��Ϊ3456�����п������Ϊ19412882.23Ԫ��");
				Activity122.this.getContentResolver().insert(uri, value);
			}
		}).start();
	}

}
