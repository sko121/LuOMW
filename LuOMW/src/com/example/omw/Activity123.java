package com.example.omw;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.Lu.omw.utils.ContactHelperUtil;
import com.Lu.omw.utils.ContactHelperUtil;
import com.Lu.omw.utils.DialogUtils;
import com.example.omw.view.CustomDialog;

/**
 * @author Administrator 操作通讯录记录
 */
public class Activity123 extends Activity {

	private ListView lv;
	private List<Map<String, Object>> contacts;
	private String phone;
	private String contactName;

	private SimpleAdapter adapter;

	private int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);

		lv = (ListView) findViewById(R.id.lv);
		ContentResolver contentResolver = getContentResolver();

		contacts = ContactHelperUtil.queryContact(contentResolver);
//		Log.d("lianxiren", contacts.size() + ""); // 0
		adapter = new SimpleAdapter(this, contacts,
				R.layout.view_contact_item, new String[] { "contactName" },
				new int[] { R.id.contact_tv });
		lv.setAdapter(adapter);

		// 设置监听
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Activity123.this.position = position;
//				phone = contacts.get(position).get("phone").toString();
				contactName = contacts.get(position).get("contactName")
						.toString();
				 DialogUtils.showDialog(Activity123.this, phone, "拨打电话", "删除",
				 new MClickListener());
				return false;
			}
		});
	}
	
	class MClickListener implements CustomDialog.OnMClcikListener{

		@Override
		public void onPositiveListener(View view) {
			if(!TextUtils.isEmpty(phone)){
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:" + phone));
				Activity123.this.startActivity(intent);
			}
		}

		@Override
		public void onNegativeListener(View view) {
			if(!TextUtils.isEmpty(contactName)){
				//删除数据库数据
				ContactHelperUtil.deleteContact(getContentResolver(),
						contactName);
				//删除listview容器数据
				contacts.remove(position);
				adapter.notifyDataSetChanged();
			}
		}
		
	}
}
