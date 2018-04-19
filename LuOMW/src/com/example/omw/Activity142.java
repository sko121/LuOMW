package com.example.omw;

import com.example.omw.service.DownLoadService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author robin Service ���̺��߳� ��ʾ
 */
public class Activity142 extends Activity {
	private ListView lv;
	private static final String[] data = { "��������ͼƬ"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
		lv = (ListView) findViewById(R.id.lv);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, data);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				testServiceLife(position);
			}
		});


	}

	/**
	 * @param position
	 *            �����������ڵĲ���
	 */
	protected void testServiceLife(int position) {
		Intent intent = null;
		switch (position) {
		case 0:
			// ��������ͼƬ
			intent = new Intent();
			intent.setClass(Activity142.this, DownLoadService.class);
			startService(intent);
			break;
		default:
			break;
		}
	}
}
