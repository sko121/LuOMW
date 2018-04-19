package com.example.luomw_test_provider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author robin
 * 测试自己写的GiftContentProvider
 */
public class MainActivity extends Activity {
	private ListView lv;
	// 职权，域名
	private static final String AUTHORITY = "cn.com.omw.gift";

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.lv);
		final Uri uri = Uri.parse("content://" + AUTHORITY + "/gift");
		
		// 查询id为18 的记录
//		uri=ContentUris.withAppendedId(uri, 18);
		// 通过名字去查询
//		uri=Uri.withAppendedPath(uri, "卢钊杰");
		
		final ContentResolver resolver = getContentResolver();
		final Cursor cursor = resolver.query(uri, new String[] { "_id", "name" },
				null, null, null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_dropdown_item_1line, cursor,
				new String[] { "name" }, new int[] { android.R.id.text1 }, 2);
		lv.setAdapter(adapter);
		
		// 测试自定义的ContentProvder insert，update,delete
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 获取点击条目的名字
				TextView tv = (TextView) view;
				String name = tv.getText().toString();
				// 删除
//				resolver.delete(uri, "name=?", new String[] { name });
				// 更新
//				ContentValues values=new ContentValues();
//				values.put("name", name);
//				resolver.update(uri, values, null, null);
				// 添加
				ContentValues values=new ContentValues();
				values.put("name", "小明");
				resolver.insert(uri, values);
				cursor.requery(); 
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present. 
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
