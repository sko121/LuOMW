package com.example.omw.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver4 extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		String result = getResultData();
		if(result != null){
			Toast.makeText(context, "MyReceiver4:" + result, 0).show();
		}
		setResultData("1Ԫ");
	}

}
