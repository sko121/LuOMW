package com.example.omw;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;

public class Activity70 extends Activity {
	public static final int REQUEST_CODE = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_msg);
		
		//获取短信管理者
		SmsManager smsManager = SmsManager.getDefault();
		// PendingIntent:即将发生的意图
		// 获取PendingIntent的对象
		Intent intent = new Intent(this, Activity70.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE,
				intent, PendingIntent.FLAG_ONE_SHOT);
		//发送短信，注意添加权限
		smsManager.sendTextMessage("10086", null, "101", pendingIntent, null);
	}

}
