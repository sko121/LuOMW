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
 * @author robin ListView ��ҳ���� sqlite������ ��ʾ
 */
public class Activity111 extends Activity implements OnScrollListener {
	private List<GiftBean> giftsAllContainer;  //��������
	private ListView lv;  //listview
	private LayoutInflater inflater;  //������
	private MAdapter adapter;  //������
	private FootView footView;  //�ײ�ˢ�µ�view
	private int OFFSET = 20;  //ƫ����
	private int start = 0;  //��ʼ����
	private int count=0;  //������С
	// ��������Ĺ�������
	private List<MAsyncTask> tasks = new ArrayList<MAsyncTask>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
		inflater = LayoutInflater.from(this);
		// ��ʼ������
		giftsAllContainer = new ArrayList<GiftBean>();
		initView();
		initListener();
		MAsyncTask task=new MAsyncTask();
		task.execute(String.valueOf(start));
		tasks.add(task);
	}

	/**
	 * ��ʼ��Listener
	 */
	private void initListener() {
		lv.setOnScrollListener(this);
	}

	/**
	 * ��ʼ��view
	 */
	private void initView() {
		lv = (ListView) findViewById(R.id.lv);
		footView = new FootView(this);// �ײ���View
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
	 * @author robin �첽���ص�����
	 */
	class MAsyncTask extends AsyncTask<String, Void, List<GiftBean>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			footView.setVisibility(View.VISIBLE);// �������ݵ�ʱ����ʾ����
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
			footView.setVisibility(View.GONE);// ���ݼ�����ɣ���������
			if(result!=null){
				giftsAllContainer.addAll(result);
			}
			if (adapter == null) {// �ж�adapter �Ƿ�գ�Ϊ�½�һ�µĲ������ø�lv
				adapter = new MAdapter();
				lv.setAdapter(adapter);
			} else {// ����ListView
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
		// ����ֹͣ��ʱ��
		if (OnScrollListener.SCROLL_STATE_IDLE == scrollState) { //ֹͣ����
			if (lv.getLastVisiblePosition() == lv.getCount() - 1) {// �ײ�
				footView.setVisibility(View.VISIBLE);// �������ݵ�ʱ����ʾ����
				SystemClock.sleep(500);// ����Ƚ��������
				MAsyncTask task = new MAsyncTask();
				start += OFFSET;
				task.execute(String.valueOf(start));
				tasks.add(task);
			} else{
				for (int i = 0; i < tasks.size(); i++) {
					// �����Ǿ�ֹ״̬��ʱ����û�л������ײ���ȡ�����е�����
					tasks.get(i).cancel(true);
					tasks.clear();
					footView.setVisibility(View.GONE);
				}
			}
		} else {// ���Ǿ�ֹ״̬��ʱ�����صײ�������
			footView.setVisibility(View.GONE);
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
	}

}
