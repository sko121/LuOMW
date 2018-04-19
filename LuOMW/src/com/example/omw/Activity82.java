package com.example.omw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author robin ContextMenu 的演示
 * */
public class Activity82 extends Activity {
	private ListView lv;
	private List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
	private LayoutInflater inflater;
	private MAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
		lv = (ListView) findViewById(R.id.lv);
		initData();
		// 获取布局填充器的服务
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// 创建一个Adapter
		adapter = new MAdapter();
		// listView 设置 adapter
		lv.setAdapter(adapter);

		// ListView 注册上下文菜单,长按显示
		registerForContextMenu(lv);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		// 解析context_menu 到 menu
		getMenuInflater().inflate(R.menu.context_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		Map<String, Object> map = null;
		int position = info.position;
		switch (item.getItemId()) {
		case R.id.menu_mark_unread:  //标记未读
			Toast.makeText(this, "标记为未读", 0).show();
			break;
		case R.id.menu_top:		//置顶
			// 置顶：先删除后添加到第一个位置并且刷新
			map = datas.get(position);
			datas.remove(position);
			datas.add(0, map);
			adapter.notifyDataSetChanged();
			break;
		case R.id.menu_delete:
			// 删除并且刷新
			map = datas.get(position);
			datas.remove(position);
			adapter.notifyDataSetChanged();
			break;

		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	private void initData() {
		HashMap<String, Object> map;
		for (int i = 0; i < 50; i++) {
			map = new HashMap<String, Object>();
			map.put("image", R.drawable.new_list_defaulting);
			map.put("title", "title" + i);
			map.put("des", "des" + i);
			datas.add(map);
		}
	}

	/**
	 * @author robin 自定义Adapter
	 */
	class MAdapter extends BaseAdapter {

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
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.view_lv_item, parent,
						false);// xml布局--->View
				// 创建ViewHodler并且给里面的View赋值
				holder = new ViewHolder();
				holder.image = (ImageView) convertView.findViewById(R.id.image);
				holder.titleTv = (TextView) convertView
						.findViewById(R.id.title);
				holder.desTv = (TextView) convertView.findViewById(R.id.des);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			// L.d("position:"+position);
			// L.d("convertView:"+convertView);
			// L.d("parent:"+parent);

			// 获取position 下的title值
			Map<String, Object> map = datas.get(position);
			final String title = (String) map.get("title");
			final String des = (String) map.get("des");
			// 给titleTV赋值
			holder.desTv.setText(des);
			holder.titleTv.setText(title);
			return convertView;
		}

		/**
		 * @author robin View 的 一个容器
		 */
		class ViewHolder {
			public ImageView image;// 显示图片
			public TextView titleTv;// 标题的TextView
			public TextView desTv;// 描述的TextView
		}
	}

}
