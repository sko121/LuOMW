package com.example.omw;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Activity41 extends Activity{
	private ImageView asyncImg;
	private ProgressBar asyncPb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.async_loadimg);
		
		asyncImg = (ImageView) findViewById(R.id.img);
		asyncPb = (ProgressBar) findViewById(R.id.pb);
		
		new MAsyncTask().execute(Constant.IMAGEURL);
		
	}
	//Params:需要传入的参数类型
	//Progress:更新进度的类型
	//Result:处理结果的类型
	//Void:代表类型为空
	class MAsyncTask extends AsyncTask<String, Void, Bitmap>{

		@Override
		protected void onPreExecute() {
			asyncPb.setVisibility(View.VISIBLE);
		}
		
		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			URL mUrl;
			Bitmap bitmap = null;
			try {
				mUrl = new URL(Constant.IMAGEURL);
				InputStream in = mUrl.openStream();
				SystemClock.sleep(200);
				bitmap = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bitmap;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			asyncPb.setVisibility(View.GONE);
			asyncImg.setImageBitmap(result);
		}
	}
}
