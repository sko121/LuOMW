package com.example.omw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.Lu.omw.utils.DBUtils;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Activity110 extends Activity {
	private ListView lv;
	private List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
		lv = (ListView) findViewById(R.id.lv);
		Cursor cursor = DBUtils.queryCursorBySql(this);
		// impleCursorAdapter(Context context, int layout, Cursor c, String[]
		// from,
		// int[] to, int flags)
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.view_lv_item, cursor, new String[] { "_id", "name" },
				new int[] { R.id.des, R.id.title },
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		lv.setAdapter(adapter);
	}

}
