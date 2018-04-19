package com.example.omw;

import com.example.omw.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Activity22 extends Activity{
	private ProgressBar pb;
	private TextView tv;
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			tv.setText(String.valueOf(msg.arg1) + "%");
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progressbar);
		pb = (ProgressBar) findViewById(R.id.pb);
		tv = (TextView) findViewById(R.id.pb_tv);
		
		tv.setVisibility(View.VISIBLE);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i<100; i++){
					SystemClock.sleep(100);
					pb.setProgress(i);
					
					Message msg = new Message();
					msg.arg1 = i;
					handler.sendMessage(msg);
				}
			}
		}).start();
	}
}
