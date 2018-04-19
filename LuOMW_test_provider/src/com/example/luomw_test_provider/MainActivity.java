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
 * �����Լ�д��GiftContentProvider
 */
public class MainActivity extends Activity {
	private ListView lv;
	// ְȨ������
	private static final String AUTHORITY = "cn.com.omw.gift";

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.lv);
		final Uri uri = Uri.parse("content://" + AUTHORITY + "/gift");
		
		// ��ѯidΪ18 �ļ�¼
//		uri=ContentUris.withAppendedId(uri, 18);
		// ͨ������ȥ��ѯ
//		uri=Uri.withAppendedPath(uri, "¬�Ƚ�");
		
		final ContentResolver resolver = getContentResolver();
		final Cursor cursor = resolver.query(uri, new String[] { "_id", "name" },
				null, null, null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_dropdown_item_1line, cursor,
				new String[] { "name" }, new int[] { android.R.id.text1 }, 2);
		lv.setAdapter(adapter);
		
		// �����Զ����ContentProvder insert��update,delete
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// ��ȡ�����Ŀ������
				TextView tv = (TextView) view;
				String name = tv.getText().toString();
				// ɾ��
//				resolver.delete(uri, "name=?", new String[] { name });
				// ����
//				ContentValues values=new ContentValues();
//				values.put("name", name);
//				resolver.update(uri, values, null, null);
				// ���
				ContentValues values=new ContentValues();
				values.put("name", "С��");
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
