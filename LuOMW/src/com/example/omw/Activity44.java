package com.example.omw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Lu.omw.bean.NewsCatalogBean;
import com.google.gson.Gson;

public class Activity44 extends Activity implements OnScrollListener{
	// װ������ʵ������
	private List<NewsCatalogBean.NewsBean> data = new ArrayList<NewsCatalogBean.NewsBean>();
	private ListView newsLv;
	private LayoutInflater inflater;
	
	private Gson gson;
	
	// ����LruCache����
	private LruCache<String, Bitmap> lruCache;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
		newsLv = (ListView) findViewById(R.id.lv);
		inflater = LayoutInflater.from(this);
		// Returns the maximum number of bytes the heap can expand to. See
		// {@link #totalMemory} for the
		// current number of bytes taken by the heap,
		long maxMemory = Runtime.getRuntime().maxMemory();
		int maxSize = (int) maxMemory;
		// ����LruCache ����
		lruCache = new LruCache<String, Bitmap>(maxSize) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// value.getByteCount():��ȡBitmap ��һ���ֽ���
				// Returns the minimum number of bytes that can be used to store
				// this bitmap's pixels
				return value.getByteCount();
			}
		};
		// ����gson����
		gson = new Gson();
		
		new MyAsyncTask().execute(Constant.URL);
		
		// listView ���û����ļ���
		newsLv.setOnScrollListener(this);
	}
	
	class MyAsyncTask extends AsyncTask<String, Void, List<NewsCatalogBean.NewsBean>>{
		@Override
		protected List<NewsCatalogBean.NewsBean> doInBackground(String... params) {
			String result = getResultForUrl(Constant.URL);
			data = getDataFromResult(result);
			return data;
		}
		@Override
		protected void onPostExecute(List<NewsCatalogBean.NewsBean> result) {
			super.onPostExecute(result);
			newsLv.setAdapter(new NewsAdapter());
		}
	}
	
	class NewsAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		ViewHolder holder = null;
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				convertView = inflater.inflate(R.layout.activity_lv_newsitem,
						parent, false);
				holder = new ViewHolder();
				holder.newsImage = (ImageView) convertView.findViewById(R.id.img);
				holder.nameTv = (TextView) convertView.findViewById(R.id.title);
				holder.desTv = (TextView) convertView.findViewById(R.id.des);
				convertView.setTag(holder);
			} else{
				holder = (ViewHolder) convertView.getTag();
			}
			NewsCatalogBean.NewsBean newsBean = data.get(position);
			// holder.newsImage.setImageBitmap(bm)
			//imageview --- imageUrl ���а�
			holder.newsImage.setImageResource(R.drawable.new_list_defaulting);
			holder.newsImage.setTag(newsBean.picSmall);
			
			// ��������ȥ����ͼƬ�ˣ�����Ȩ������ListView ��OnScrollListener �����ķ���
//			new MyImageAsyncTask(holder.newsImage).execute(newsBean.getPicSmall());
			holder.nameTv.setText(newsBean.name);
			holder.desTv.setText(newsBean.description);
			return convertView;
		}
		
		class ViewHolder {
			public ImageView newsImage;
			public TextView nameTv;
			public TextView desTv;
		}
	}
	
	class MyImageAsyncTask extends AsyncTask<String, Void, Bitmap>{
		private ImageView imageView;
		private String url;
		
		public MyImageAsyncTask(ImageView imageView){
			this.imageView=imageView;
		}
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		@Override
		protected Bitmap doInBackground(String... params) {
			url = params[0];
			Bitmap bitmap = null;
			try {
				if(lruCache.get(url) == null){

					URL mUrl = new URL(url);
					bitmap = BitmapFactory.decodeStream(mUrl.openStream());
					lruCache.put(url, bitmap);

				} else{
					bitmap = lruCache.get(url);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bitmap;
		}
		@Override
		protected void onPostExecute(Bitmap result) {
			if(result!=null && imageView.getTag().equals(url))
				this.imageView.setImageBitmap(result);
		}
	}
	
	public String getResultForUrl(String url){
		InputStream is = null;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			URL mUrl = new URL(url);
			URLConnection connection = mUrl.openConnection();
			is = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String temp = null;
			while((temp = br.readLine())!= null){
				sb.append(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	/**
	 * @param result
	 * @return �Ѵӻ�ȡ��result ת���� List<NewsBean>
	 */
	public List<NewsCatalogBean.NewsBean> getDataFromResult(String result){
		// ��gson
		NewsCatalogBean newsCatalogBean = gson.fromJson(result,
				NewsCatalogBean.class);
		return newsCatalogBean.data;
	}

	private int start;
	private int end;
	private boolean isFirstVist = true;
	// view is being scrolled����һ�����е�ʱ����û�е���
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if(OnScrollListener.SCROLL_STATE_IDLE == scrollState){
			loadImageFromOffset(start, end);
		}
	}

	//����ֹͣ/��ֹʱ����
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		start = firstVisibleItem;
		end = firstVisibleItem + visibleItemCount;
		if(isFirstVist && totalItemCount > 0){
			isFirstVist = false;
			loadImageFromOffset(start, end);
		}
	}
	
	//����ͼƬ
	public void loadImageFromOffset(int start, int end){
		for(int i = start; i < end; i++){
			String url = data.get(i).picSmall;
			ImageView imageView = (ImageView) newsLv.findViewWithTag(url);
			new MyImageAsyncTask(imageView).execute(url);
		}
	}
}
