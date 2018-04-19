package com.example.omw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Activity130 extends Activity{
	private ListView lv;
	private String[] datas = { "��ͨ�㲥", "����㲥" };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
		
		lv = (ListView) findViewById(R.id.lv);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), 
				android.R.layout.simple_dropdown_item_1line, datas);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				send(position);
			}
		});
	}
	
	protected void send(int position) {
		Intent intent = null;
		
		switch (position) {
		case 0:	//��ͨ�㲥
			intent =  new Intent();
			intent.putExtra("msg", "���������ȽϿ�");
			intent.setAction("com.lu.omw");	//���ö���
			sendBroadcast(intent);
			break;
		case 1:	//����㲥
			intent =  new Intent();
			intent.putExtra("msg", "10000Ԫ");
			intent.setAction("com.lu.omw.send");
			sendOrderedBroadcast(intent, null);
			break;

		default:
			break;
		}
	}

}
