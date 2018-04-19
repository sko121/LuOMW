package com.example.omw;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Lu.omw.bean.MyNews;
import com.example.omw.view.FootView;

public class Activity38 extends Activity implements OnScrollListener{
	private List<MyNews> myNewsContainer;
	private ListView lv;
	private LayoutInflater inflater;
	private FootView footView;
	private MyAdapter adapter;
	public static int OFFSET=20;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv	);
		
		inflater = LayoutInflater.from(this);
		//��ʼ������
		myNewsContainer = new ArrayList<MyNews>();
		initView();
		initListener();
		
		new MyAsyncTask().execute(Constant.MNEWSURL,"0","10");
	}
	
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return myNewsContainer.size();
		}

		@Override
		public Object getItem(int position) {
			return myNewsContainer.get(position);
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
				convertView = inflater.inflate(R.layout.activity_simple_demo, parent,
						false);
				holder.title = (TextView) convertView
						.findViewById(R.id.title);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			MyNews MNews = myNewsContainer.get(position);
			holder.title.setText(MNews.getTitle());
			return convertView;
		}
		
		class ViewHolder {
			public ImageView img;
			public TextView title;
			public TextView content;
		}
		
	}
	
	class MyAsyncTask extends AsyncTask<String, Void, List<MyNews>>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			//�������ݵ�ʱ����ʾfootView
			footView.setVisibility(View.VISIBLE);
		}
		
		@Override
		protected List<MyNews> doInBackground(String... params) {
			List<MyNews> data = getNewsFromOffsetByGet(params[0], params[1],
					params[2]);
			if (data != null) {
				myNewsContainer.addAll(data);
			}
			return myNewsContainer;
		}
		
		@Override
		protected void onPostExecute(List<MyNews> result) {
			//���ݼ�����ɣ�����footview
			footView.setVisibility(View.GONE);
			if(adapter == null){
				adapter = new MyAdapter();
				lv.setAdapter(adapter);
			} else{
				//����listview
				adapter.notifyDataSetChanged();
			}
		}
	}

	private void initListener() {
		lv.setOnScrollListener(this);
	}

	public List<MyNews> getNewsFromOffsetByGet(String url, String start,
			String end) {
		List<MyNews> newsList = null;
		// ƴ��Url��http://192.168.10.54:8080/ServerForXML/ServletForXML?start=0&end=2
		url = url + "?start=" + start + "&end=" + end;
		//��ȡHttpClient����
		HttpClient client = new DefaultHttpClient();
		//�½�get��HttpGet����
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = client.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				// ��ȡ���:��ȡʵ��
				HttpEntity entity = response.getEntity();
				// ��ʵ�����ת�����ַ���
				String result = EntityUtils.toString(entity);
				Log.d("lu", result);
				// �������ȡ���ַ���ת��ʵ�弯��
				newsList = getNewsFromResultByXml(result);
				Log.d("lu", String.valueOf(newsList.size()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return newsList;
	}

	/**
	 * �������ȡ���ַ���ת��ʵ�弯��
	 */
	private List<MyNews> getNewsFromResultByXml(String result) {
		List<MyNews> newsList = null;
		MyNews myNews = null;
		//����pull������
		XmlPullParser parser = Xml.newPullParser();
		try {
			// parser ����StringReader
			parser.setInput(new StringReader(result));
			int eventType = parser.getEventType();// ��ȡ�¼�����
			while (eventType != XmlPullParser.END_DOCUMENT) {// ���ǽ����ĵ���ǩ��ʱ��һֱѭ��
				if (XmlPullParser.START_DOCUMENT == eventType) {// �ĵ���ʼ��ʱ��
					newsList = new ArrayList<MyNews>();
				}
				if (XmlPullParser.START_TAG == eventType) {// ��ǩ��ʼ
					if ("news".equals(parser.getName())) {
						myNews = new MyNews();
						myNews.setId(Integer.parseInt(parser.getAttributeValue(
								null, "id")));
					}
					if ("title".equals(parser.getName())) {
						myNews.setTitle(parser.nextText());
					}
					if ("timelength".equals(parser.getName())) {
						myNews.setTimeLength(Integer.parseInt(parser.nextText()));
					}
				}
				if (XmlPullParser.END_TAG == eventType) {
					if ("news".equals(parser.getName())) {
						newsList.add(myNews);
					}
				}
				eventType = parser.next();// �ǵø������͸�ֵ
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newsList;
	}

	private void initView() {
		lv = (ListView) findViewById(R.id.lv);
		footView = new FootView(this);
		lv.addFooterView(footView);
		footView.setVisibility(View.GONE);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
		if(OnScrollListener.SCROLL_STATE_IDLE == scrollState){//����ֹͣ��ʱ��
			if(lv.getLastVisiblePosition() == lv.getCount() - 1){//1Ϊ�ײ�
				if(myNewsContainer.size() > 100){//ֻ�������һ����
					return;
				}
				new MyAsyncTask().execute(Constant.MNEWSURL,
						String.valueOf(myNewsContainer.size()),
						String.valueOf(myNewsContainer.size() + OFFSET));
			}
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
	}
}
