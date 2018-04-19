package com.example.omw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.omw.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Activity31 extends ActionBarActivity {
	
	private ListView lv;
	private List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
		LayoutInflater inflater = LayoutInflater.from(this);
		lv = (ListView) findViewById(R.id.lv);
		initData();
		
		View listHeadView = inflater.inflate(R.layout.activity_lv_head,
				lv, false);
		lv.addHeaderView(listHeadView);
		
//		SimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
//		����context�������ģ�����this������SimpleAdapter���е���ͼ������
//		����data��Map�б��б�Ҫ��ʾ�����ݣ��ⲿ����Ҫ�Լ�ʵ�֣��������е�getData()������Ҫ�������һ�£�ÿ����ĿҪ��from��ָ����Ŀһ��
//		����resource��ListView������ļ���Id,������־������Զ���Ĳ����ˣ�������ʾʲô���ӵĲ��ֶ�����������С���������б��������to�ж���Ŀؼ�id
//		���� from��һ������ӵ�Map�Ϲ���ÿһ����Ŀ�����Ƶ��б�����������������
//		���� to����һ��int���飬���������id���Զ��岼���и����ؼ���id����Ҫ�������from��Ӧ
		SimpleAdapter adapter = new SimpleAdapter(this, datas, R.layout.activity_simple_demo,  
									new String[] { "title", "content" }, 
									new int[] { R.id.title, R.id.content });
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Map<String, Object> map = datas.get(position);
				Toast.makeText(Activity31.this,
						String.valueOf(map.get("title")), Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	private void initData() {
		for(int i = 0; i<10; i++){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", R.drawable.new_list_defaulting);
			map.put("title", "title" + i);
			map.put("content", "content" + i);
			datas.add(map);
		}
	}

}
