package com.example.omw.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver3 extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		String msg = intent.getStringExtra("msg");
		Toast.makeText(context, "MyReceiver3:" + msg, 0).show();
		setResultData("100Ԫ");
		//��ֹ�㲥
//		abortBroadcast();
	}

}
