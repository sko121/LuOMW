package com.example.omw;

import com.example.omw.service.LifeService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Activity140 extends Activity {
	private ListView lv;
	private static final String[] data = { "startService", "stopService" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
		
		lv = (ListView) findViewById(R.id.lv);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_dropdown_item_1line, data);
		
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				testServiceLift(position);
			}
		});
	}

	/**
	 * @param position
	 * �����������ڵĲ���
	 */
	protected void testServiceLift(int position) {
		Intent intent = null;
		switch (position) {
		case 0:
			//��������
			intent = new Intent();
			intent.putExtra("name", "������");
			intent.setClass(Activity140.this, LifeService.class);
			startService(intent);
			break;
		case 1:
			// �رշ���
			intent = new Intent();
			intent.setClass(Activity140.this, LifeService.class);
			stopService(intent);
			break;

		default:
			break;
		}
	}

}
