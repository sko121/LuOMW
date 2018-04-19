package com.example.omw;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;

public class Activity100 extends Activity{

	private ListView lv;
	public String[] datas = { "普通通知", "大视图通知", "进度条通知", "自定义通知", "取消通知"};

	private NotificationManager notificationMananger;// 短信的管理者
	private NotificationCompat.Builder builder; //创建视图builder对象
	
	private NotifyProgressTask task = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
		lv = (ListView) findViewById(R.id.lv);
		// 获取通知服务的对象
		notificationMananger = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, datas);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Activity100.this.notify(position);
			}
		});
		lv.setAdapter(adapter);
	}
	
	/**
	 * @param position
	 *            通知 演示
	 */
	protected void notify(int position) {
		
		// 创建通知Builder对象
		builder = new NotificationCompat.Builder(this);
		// 通知的图片
		builder.setSmallIcon(R.drawable.qq);
		// 设置自动消失
		builder.setAutoCancel(true);
		// 默认的声音
//			builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
		// 获取声音的Uri
		Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + 
				"://" + getPackageName() + "/" + R.raw.baby);
		// 设置自定义通知的声音
		builder.setSound(sound);
		builder.setDefaults(Notification.DEFAULT_VIBRATE);
		// 获取PendingIntent
		Intent intent = new Intent(this, Activity100.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
		// 设置未来的意图
		builder.setContentIntent(pIntent);
		Notification notification = null;  //通知
		
		switch (position) {
		case 0:
			// 设置标题
			builder.setContentTitle("I'm title");
			// 设置内容
			builder.setContentText("I'm content text");
			
			notification = builder.build();
			notificationMananger.notify(0, notification);
			break;
		case 1:  //大视图通知
			//创建大视图InBoxStyle
			NotificationCompat.InboxStyle style = 
				new NotificationCompat.InboxStyle(builder);
			style.setBigContentTitle("大视图通知");
			for(int i = 0; i<datas.length; i++){
				style.addLine(datas[i]);
//				Log.d("lu", datas[i]);
			}
			//创建通知对象
			notification = builder.build();
			//短信管理者调用通知对象，进行通知
			notificationMananger.notify(1, notification);
			break;
		case 2:
			//进度条通知
			task = new NotifyProgressTask();
			task.execute(builder);
			break;
		case 3:  //自定义通知
			//创建远程的View
			RemoteViews remoteViews = new RemoteViews(getPackageName(),
					R.layout.view_notification_remote);
			//修改远程view
			remoteViews.setImageViewResource(R.id.remote_image, R.drawable.sina);
			builder.setContent(remoteViews);
			notification = builder.build();
			notificationMananger.notify(3, notification);
			break;
		case 4: //取消通知
			notificationMananger.cancelAll(); //取消所有通知
//			notificationMananger.cancel(0); //取消id为0的通知
			if(task != null){
				task.cancel(true);
			}
			break;
		default:
			break;
		}
	}
	
	class NotifyProgressTask extends 
		AsyncTask<NotificationCompat.Builder, Integer, NotificationCompat.Builder>{

		@Override
		protected Builder doInBackground(Builder... params) {
			if(!isCancelled()){
				NotificationCompat.Builder builder = params[0];
				for(int i = 0; i < 100; i++){
					publishProgress(i);
					SystemClock.sleep(100);
				}
				//进度完成后创建通知
				builder.setContentText("最牛逼的apk已经下载完成.");
				Notification notification = builder.build();
				notificationMananger.notify(2, notification);
			}
			return builder;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			if(!isCancelled()){
				//取消声音
				builder.setSound(null);
				//取消震动
				builder.setDefaults(0);
				//设置进度条进度
				builder.setProgress(100, values[0], false);
				builder.setContentText("正在下载最牛逼的apk...  " + values[0] + "%");
				Notification notification = builder.build();
				notificationMananger.notify(2, notification);
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
