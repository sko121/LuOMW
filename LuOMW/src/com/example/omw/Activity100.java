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
	public String[] datas = { "��֪ͨͨ", "����ͼ֪ͨ", "������֪ͨ", "�Զ���֪ͨ", "ȡ��֪ͨ"};

	private NotificationManager notificationMananger;// ���ŵĹ�����
	private NotificationCompat.Builder builder; //������ͼbuilder����
	
	private NotifyProgressTask task = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
		lv = (ListView) findViewById(R.id.lv);
		// ��ȡ֪ͨ����Ķ���
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
	 *            ֪ͨ ��ʾ
	 */
	protected void notify(int position) {
		
		// ����֪ͨBuilder����
		builder = new NotificationCompat.Builder(this);
		// ֪ͨ��ͼƬ
		builder.setSmallIcon(R.drawable.qq);
		// �����Զ���ʧ
		builder.setAutoCancel(true);
		// Ĭ�ϵ�����
//			builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
		// ��ȡ������Uri
		Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + 
				"://" + getPackageName() + "/" + R.raw.baby);
		// �����Զ���֪ͨ������
		builder.setSound(sound);
		builder.setDefaults(Notification.DEFAULT_VIBRATE);
		// ��ȡPendingIntent
		Intent intent = new Intent(this, Activity100.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
		// ����δ������ͼ
		builder.setContentIntent(pIntent);
		Notification notification = null;  //֪ͨ
		
		switch (position) {
		case 0:
			// ���ñ���
			builder.setContentTitle("I'm title");
			// ��������
			builder.setContentText("I'm content text");
			
			notification = builder.build();
			notificationMananger.notify(0, notification);
			break;
		case 1:  //����ͼ֪ͨ
			//��������ͼInBoxStyle
			NotificationCompat.InboxStyle style = 
				new NotificationCompat.InboxStyle(builder);
			style.setBigContentTitle("����ͼ֪ͨ");
			for(int i = 0; i<datas.length; i++){
				style.addLine(datas[i]);
//				Log.d("lu", datas[i]);
			}
			//����֪ͨ����
			notification = builder.build();
			//���Ź����ߵ���֪ͨ���󣬽���֪ͨ
			notificationMananger.notify(1, notification);
			break;
		case 2:
			//������֪ͨ
			task = new NotifyProgressTask();
			task.execute(builder);
			break;
		case 3:  //�Զ���֪ͨ
			//����Զ�̵�View
			RemoteViews remoteViews = new RemoteViews(getPackageName(),
					R.layout.view_notification_remote);
			//�޸�Զ��view
			remoteViews.setImageViewResource(R.id.remote_image, R.drawable.sina);
			builder.setContent(remoteViews);
			notification = builder.build();
			notificationMananger.notify(3, notification);
			break;
		case 4: //ȡ��֪ͨ
			notificationMananger.cancelAll(); //ȡ������֪ͨ
//			notificationMananger.cancel(0); //ȡ��idΪ0��֪ͨ
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
				//������ɺ󴴽�֪ͨ
				builder.setContentText("��ţ�Ƶ�apk�Ѿ��������.");
				Notification notification = builder.build();
				notificationMananger.notify(2, notification);
			}
			return builder;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			if(!isCancelled()){
				//ȡ������
				builder.setSound(null);
				//ȡ����
				builder.setDefaults(0);
				//���ý���������
				builder.setProgress(100, values[0], false);
				builder.setContentText("����������ţ�Ƶ�apk...  " + values[0] + "%");
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
