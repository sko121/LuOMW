package com.example.omw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Lu.omw.bean.NewsBean;
import com.Lu.omw.bean.NewsCatalogBean;
import com.google.gson.Gson;

/**
 * �첽�������̵߳���ʽ+LruCache ��ʾ
 *
 */
public class Activity42 extends Activity{
	// װ������ʵ������
	private List<NewsBean> data = new ArrayList<NewsBean>();
//	private List<NewsCatalogBean.NewsBean> data = new ArrayList<NewsCatalogBean.NewsBean>();
	private ListView newsLv;
	private LayoutInflater inflater;
	
	private Gson gson;
	
	// ����LruCache����
	private LruCache<String, Bitmap> lruCache;
	
	// �ȴ��첽����������֮�󣬲�����Adapter
	private Handler handler = new Handler(){
		public void handleMessage(Message msg){
			newsLv.setAdapter(new NewsAdapter());
		}
	};
	
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
		
		// android 4.0 ��UI�߳�������������ᱨ���쳣��android.os.NetworkOnMainThreadException
		new Thread(new Runnable(){
			@Override
			public void run() {
				String result = getResultForUrl(Constant.URL);
				data = getDataFromResult(result);
				Message message = Message.obtain();// �ȳ������ã������ò�������new Message()
				handler.sendMessage(message);
			}
		}).start();
		// �첽��ʱ����Ҫ���ݼ�����ɵ�ʱ�����Adapter
		newsLv.setAdapter(new NewsAdapter());
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
			NewsBean newsBean = data.get(position);
			// holder.newsImage.setImageBitmap(bm)
			//imageview --- imageUrl ���а�
			holder.newsImage.setImageResource(R.drawable.new_list_defaulting);
			holder.newsImage.setTag(newsBean.getPicSmall());
			
			new MyAsyncTask(holder.newsImage).execute(newsBean.getPicSmall());
//			new ImageLoader().loadImageFromUrl(holder.newsImage, newsBean.getPicSmall());
//			Log.d("lu", newsBean.getPicSmall());
			holder.nameTv.setText(newsBean.getName());
			holder.desTv.setText(newsBean.getDescription());
			return convertView;
		}
		
		class ViewHolder {
			public ImageView newsImage;
			public TextView nameTv;
			public TextView desTv;
		}
		
		class MyAsyncTask extends AsyncTask<String, Void, Bitmap>{
			private ImageView imageView;
			private String url;
			
			public MyAsyncTask(ImageView imageView){
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
//				Log.d("async", url);
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
				if(result!=null && this.imageView.getTag().equals(url))
					this.imageView.setImageBitmap(result);
			}
		}
	}
	
	
	/**
	 * �������Ǽ���ͼƬ
	 */
	class ImageLoader{
		public ImageView imageView;
		public String url;
		
		private Handler mhandle = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.obj != null && imageView.getTag().equals(url)) {
					imageView.setImageBitmap((Bitmap) msg.obj);
				}
			};
		};
		/**
		 * @param imageView:��Ҫ���õ�ImageView
		 * @param url����Ӧ��url
		 */
		public void loadImageFromUrl(final ImageView imageView,
				final String url){
			this.imageView = imageView;
			this.url = url;
			new Thread(new Runnable(){
				@Override
				public void run() {
					InputStream is = null;
					URL mUrl;
					try {
						Bitmap bitmap=null;
						// ����LruCache����û�����Ǵ洢bitmap
						if (lruCache.get(url) == null) {
							// ��λ��ͼƬ��Դ��λ��
							mUrl = new URL(url);
							// ��ȡͼƬ��Դ��������
							is = mUrl.openStream();// 6��
							SystemClock.sleep(500);
							// ͨ���������������ת����bitmap
							bitmap = BitmapFactory.decodeStream(is);
							lruCache.put(url, bitmap);
						}else{// ����LruCache���������Ǵ洢bitmap
							bitmap=lruCache.get(url);
						}
						// ��������»�����쳣
						// imageView.setImageBitmap(bitmap);
						// android.view.ViewRootImpl$CalledFromWrongThreadException:
						// Only the original thread that created a view
						// hierarchy
						// can touch its views.
						Message msg = Message.obtain();
						msg.obj = bitmap;
						mhandle.sendMessage(msg);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
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
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * @param result
	 * @return �Ѵӻ�ȡ��result ת���� List<NewsBean>
	 */
	public List<NewsBean> getDataFromResult(String result){
//		try {
//			JSONObject obj = new JSONObject(result);
//			JSONArray array = obj.getJSONArray("data");
//			for(int i = 0; i<array.length(); i++){
//				JSONObject jobj = (JSONObject) array.get(i);
//				String name = jobj.getString("name");
//				String picSmall = jobj.getString("picSmall");
//				String description = jobj.getString("description");
//				NewsBean newsBean = new NewsBean(name, picSmall, description);
//				data.add(newsBean);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		// ��gson
		NewsCatalogBean newsCatalogBean = gson.fromJson(result,
				NewsCatalogBean.class);
//		return newsCatalogBean.data;
		return data;
	}
}
