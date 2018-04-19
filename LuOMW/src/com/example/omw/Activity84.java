package com.example.omw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.Lu.omw.utils.DisplayUtils;
import com.example.omw.view.PopupView;
import com.example.omw.view.PopupView.OnPopupClickListener;

public class Activity84 extends Activity {
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

		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				PopupView popupView = new PopupView(Activity84.this);
				final PopupWindow popupWindow = new PopupWindow(popupView,
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT, true);
				// PopupWindow 需要设置背景才能显示
				popupWindow.setBackgroundDrawable(getResources().getDrawable(
						android.R.color.transparent));
				// popupWindow.showAsDropDown(view);
				// pixels:94dp--->px
				popupWindow.showAsDropDown(
						view,
						DisplayUtils.getScreenWidth(Activity84.this) / 2
								- DisplayUtils.dip2px(Activity84.this, 100) / 2,
						-DisplayUtils.dip2px(Activity84.this, 80 + 40));// 负的往上偏移

				popupView.setOnPopupClickListener(new OnPopupClickListener() {

					@Override
					public void onPopupClickTop(View v) {
						Map<String, Object> map = datas.get(position);
						datas.remove(position);
						datas.add(0, map);
						adapter.notifyDataSetChanged();
						popupWindow.dismiss();
					}

					@Override
					public void onPopupClickDelete(View v) {
						datas.remove(position);
						adapter.notifyDataSetChanged();
						popupWindow.dismiss();
					}
				});
				return false;
			}
		});
	}

	private void initData() {
		for (int i = 0; i < 50; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", R.drawable.new_list_defaulting);
			map.put("title", "title" + i);
			map.put("des", "des" + i);
			datas.add(map);
		}
	}

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
			// 获取position 下的title值
			Map<String, Object> map = datas.get(position);
			final String title = (String) map.get("title");
			final String des = (String) map.get("des");
			// 给titleTV赋值
			holder.titleTv.setText(title);
			holder.desTv.setText(des);
			return convertView;
		}

		/**
		 * View 的 一个容器
		 */
		class ViewHolder {
			public ImageView image;// 显示图片
			public TextView titleTv;// 标题的TextView
			public TextView desTv;// 描述的TextView
		}
	}

}
