package com.example.omw;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Activity120 extends Activity{
	private LayoutInflater inflater;
	private ListView lv;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
		
		lv = (ListView) findViewById(R.id.lv);
		inflater = LayoutInflater.from(this);
		
		View headView = inflater.inflate(R.layout.view_contact_header, lv, false);
		//内容解析者
		ContentResolver contentResolver = getContentResolver();
		//查询
		Cursor cursor = contentResolver.query(CallLog.Calls.CONTENT_URI, 
				new String[]{CallLog.Calls._ID, CallLog.Calls.CACHED_NAME,
				CallLog.Calls.NUMBER, CallLog.Calls.TYPE}, null, null, null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
				R.layout.view_contact_lv_item, cursor, 
				new String[]{CallLog.Calls.CACHED_NAME, CallLog.Calls.NUMBER, 
				CallLog.Calls.TYPE}, new int[]{R.id.tv_name, R.id.tv_phone, R.id.tv_type}, 2);
		lv.addHeaderView(headView);
		lv.setAdapter(adapter);
	}

}
