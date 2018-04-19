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
//		参数context：上下文，比如this。关联SimpleAdapter运行的视图上下文
//		参数data：Map列表，列表要显示的数据，这部分需要自己实现，如例子中的getData()，类型要与上面的一致，每条项目要与from中指定条目一致
//		参数resource：ListView单项布局文件的Id,这个布局就是你自定义的布局了，你想显示什么样子的布局都在这个布局中。这个布局中必须包括了to中定义的控件id
//		参数 from：一个被添加到Map上关联每一个项目列名称的列表，数组里面是列名称
//		参数 to：是一个int数组，数组里面的id是自定义布局中各个控件的id，需要与上面的from对应
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
