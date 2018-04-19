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
								"您的账户5678，收到10000000000， 活期余额为:1000000000000")
						.setContentTitle(
								"您的账户5678，收到10000000000， 活期余额为:1000000000000")
						.setSound(sound)
						.setDefaults(Notification.DEFAULT_VIBRATE)
						.setAutoCancel(true).build();
				notificationManager.notify(0, notification);
				Uri uri = Uri.parse("content://sms");
				ContentValues value = new ContentValues();
				value.put("address", "95555");
				value.put("type", "1");
				value.put("body", "【中国农业银行】当前您查询的尾号为3456的银行卡，余额为19412882.23元。");
				Activity122.this.getContentResolver().insert(uri, value);
			}
		}).start();
	}

}
