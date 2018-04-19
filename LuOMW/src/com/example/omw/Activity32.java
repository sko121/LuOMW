package com.example.omw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.omw.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity32 extends Activity{
	private ListView lv;
	private List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
		lv = (ListView) findViewById(R.id.lv);
		initData();
		
		// ��ȡ����������ķ���
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// ����һ��Adapter
		// listView ���� adapter
		lv.setAdapter(new MyAdapter());
	}

	private void initData() {
		for (int i = 0; i < 80; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", R.drawable.new_list_defaulting);
			map.put("title", "title" + i);
			map.put("content", "content" + i);
			datas.add(map);
		}
	}
	
	class MyAdapter extends BaseAdapter{
		
		@Override
		public int getCount() {
			return datas.size();
		}
		
		@Override
		public Object getItem(int position) {
			return datas.get(position);
		}
		
		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		/**
		 * @parm position: listview item ��λ��
		 * @Parm converView�� listview item �Ļ���view
		 * @ parm parent: adapterview
		 */
		public View getView(final int position, View convertView, ViewGroup parent) {
			Holder holder;
			String Tag = "lu";
//			Log.d(Tag, String.valueOf(position));
//			Log.d(Tag, String.valueOf(parent));		//ListView
			Log.d(Tag, String.valueOf(convertView));  //android.widget.LinearLayout
			
			if(convertView == null){
				//xml���� --> view
				//��attachroot Ϊ FALSE ��ʱ�򣬸����ֵ�����ᱻ����ȥ����������Ч
				convertView = inflater.inflate(R.layout.activity_simple_demo, parent, false);
				holder = new Holder(convertView);
				convertView.setTag(holder);
			} else{
				holder = (Holder) convertView.getTag();
			}
			ImageView image = (ImageView) convertView.findViewById(R.id.img);
			holder.img.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Map<String, Object> map = datas.get(position);
					String title = (String) map.get("title");
					Toast.makeText(getApplicationContext(), title,
							Toast.LENGTH_SHORT).show();
				}
			});
			holder.content.setText(String.valueOf(position));
			return convertView;
		}
		class Holder{
			ImageView img;
			TextView title;
			TextView content;
			public Holder(View v){
				img = (ImageView) v.findViewById(R.id.img);
				title = (TextView) v.findViewById(R.id.title);
				content = (TextView) v.findViewById(R.id.content);
			}
		}
	}
}

