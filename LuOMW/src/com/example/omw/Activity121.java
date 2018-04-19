package com.example.omw;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;

public class Activity121 extends Activity{
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
	
		lv = (ListView) findViewById(R.id.lv);
		ContentResolver contentResolver = getContentResolver();
		Cursor cursor = contentResolver.query(Uri.parse("content://sms"),
				 new String[]{"_id","address","date","body","type"}, null,
				 null, null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.view_sms_lv_item, cursor,
				new String[] {"_id","address","date","body","type"},
				new int[] {R.id.tv_id, R.id.tv_address, R.id.tv_date, 
							R.id.tv_body, R.id.tv_type}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		lv.setAdapter(adapter);
	}

}
