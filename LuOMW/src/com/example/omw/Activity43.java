package com.example.omw;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Activity43 extends Activity{
	private ProgressBar pb;
	private TextView pb_tv;
	private MyPbAsyncTask task;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progressbar);
		
		pb = (ProgressBar) findViewById(R.id.pb);
		pb_tv = (TextView) findViewById(R.id.pb_tv);
		
		//新建立一个Task
		task = new MyPbAsyncTask();
		task.execute();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 判断这个task 是不是正在运行
		if(task != null && task.getStatus() == AsyncTask.Status.RUNNING){
			//标记正在执行的进程，进程数不定
			task.cancel(true);//不是真正去取消，而且设置一个标志，说代表取消
		}
	}
	
	class MyPbAsyncTask extends AsyncTask<Void, Integer, Void>{

		@Override
		protected void onPreExecute() {
			pb.setVisibility(View.VISIBLE);
			pb_tv.setVisibility(View.VISIBLE);
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			for(int i = 0; i <= 100; i++){
				if(task.isCancelled()){// 判断是否任务已经被取消
					break;
				}
				publishProgress(i);//发布进度
				SystemClock.sleep(100);
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			if(task.isCancelled()){// 判断是否任务已经被取消
				return;
			}
			//在UI 线程里面接收进度
			pb.setProgress(values[0]);
			pb_tv.setText(values[0] + "%");
		}
	}
}
