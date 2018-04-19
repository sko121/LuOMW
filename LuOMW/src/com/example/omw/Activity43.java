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
		
		//�½���һ��Task
		task = new MyPbAsyncTask();
		task.execute();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// �ж����task �ǲ�����������
		if(task != null && task.getStatus() == AsyncTask.Status.RUNNING){
			//�������ִ�еĽ��̣�����������
			task.cancel(true);//��������ȥȡ������������һ����־��˵����ȡ��
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
				if(task.isCancelled()){// �ж��Ƿ������Ѿ���ȡ��
					break;
				}
				publishProgress(i);//��������
				SystemClock.sleep(100);
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			if(task.isCancelled()){// �ж��Ƿ������Ѿ���ȡ��
				return;
			}
			//��UI �߳�������ս���
			pb.setProgress(values[0]);
			pb_tv.setText(values[0] + "%");
		}
	}
}
