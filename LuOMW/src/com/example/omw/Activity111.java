package com.example.omw;

import java.util.ArrayList;
import java.util.List;

import com.Lu.omw.bean.GiftBean;
import com.Lu.omw.utils.DBUtils;
import com.Lu.omw.utils.MToastUtil;
import com.example.omw.view.FootView;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author robin ListView 分页加载 sqlite的数据 演示
 */
public class Activity111 extends Activity implements OnScrollListener {
	private List<GiftBean> giftsAllContainer;  //数据容器
	private ListView lv;  //listview
	private LayoutInflater inflater;  //解析器
	private MAdapter adapter;  //适配器
	private FootView footView;  //底部刷新的view
	private int OFFSET = 20;  //偏移量
	private int start = 0;  //开始坐标
	private int count=0;  //容器大小
	// 创建任务的管理容器
	private List<MAsyncTask> tasks = new ArrayList<MAsyncTask>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
		inflater = LayoutInflater.from(this);
		// 初始化容器
		giftsAllContainer = new ArrayList<GiftBean>();
		initView();
		initListener();
		MAsyncTask task=new MAsyncTask();
		task.execute(String.valueOf(start));
		tasks.add(task);
	}

	/**
	 * 初始化Listener
	 */
	private void initListener() {
		lv.setOnScrollListener(this);
	}

	/**
	 * 初始化view
	 */
	private void initView() {
		lv = (ListView) findViewById(R.id.lv);
		footView = new FootView(this);// 底部的View
		lv.addFooterView(footView);
		footView.setVisibility(View.GONE);
	}

	class MAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return giftsAllContainer.size();
		}

		@Override
		public Object getItem(int position) {
			return giftsAllContainer.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.view_lv_item, parent,
						false);
				holder.titleTv = (TextView) convertView
						.findViewById(R.id.title);
				holder.desTv = (TextView) convertView.findViewById(R.id.des);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			GiftBean gift = giftsAllContainer.get(position);
			holder.titleTv.setText(gift.getGiftName());
			holder.desTv.setText(String.valueOf(gift.getId()));
			return convertView;
		}

		class ViewHolder {
			public ImageView imageView;
			public TextView titleTv;
			public TextView desTv;
		}

	}

	/**
	 * @author robin 异步加载的内容
	 */
	class MAsyncTask extends AsyncTask<String, Void, List<GiftBean>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			footView.setVisibility(View.VISIBLE);// 加载数据的时候，显示出来
		}

		@Override
		protected List<GiftBean> doInBackground(String... params) {
			if(!isCancelled()){
				List<GiftBean> giftsContainer=DBUtils.queryBySqlFromOffset(Activity111.this, start, OFFSET);
				count = DBUtils.getCurcorCount(Activity111.this);
				return giftsContainer;
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<GiftBean> result) {
			footView.setVisibility(View.GONE);// 数据加载完成，隐藏起来
			if(result!=null){
				giftsAllContainer.addAll(result);
			}
			if (adapter == null) {// 判断adapter 是否空，为新建一新的并且设置给lv
				adapter = new MAdapter();
				lv.setAdapter(adapter);
			} else {// 更新ListView
				adapter.notifyDataSetChanged();
			}
			if(start <= count){
				String temp=((start + OFFSET) > count )? 
						String.valueOf(count) : String.valueOf(start + OFFSET);
				MToastUtil.showBottomToast(getApplicationContext(),
						temp+ "/" + count);
			}
		}

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// 滑动停止的时候
		if (OnScrollListener.SCROLL_STATE_IDLE == scrollState) { //停止滑动
			if (lv.getLastVisiblePosition() == lv.getCount() - 1) {// 底部
				footView.setVisibility(View.VISIBLE);// 加载数据的时候，显示出来
				SystemClock.sleep(500);// 网络比较慢的情况
				MAsyncTask task = new MAsyncTask();
				start += OFFSET;
				task.execute(String.valueOf(start));
				tasks.add(task);
			} else{
				for (int i = 0; i < tasks.size(); i++) {
					// 假如是静止状态的时候并且没有滑动到底部，取消所有的任务
					tasks.get(i).cancel(true);
					tasks.clear();
					footView.setVisibility(View.GONE);
				}
			}
		} else {// 不是静止状态的时候，隐藏底部进度条
			footView.setVisibility(View.GONE);
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
	}

}
