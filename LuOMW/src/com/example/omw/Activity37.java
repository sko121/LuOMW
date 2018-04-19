package com.example.omw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.Lu.omw.bean.ListViewMultyItemBean;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Activity37 extends Activity{
	private ListView lv;
	private LayoutInflater inflater;
	List<ListViewMultyItemBean> container = new ArrayList<ListViewMultyItemBean>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lvmulty);
		initData();
		
		inflater = LayoutInflater.from(this);
		lv = (ListView) findViewById(R.id.lv);
		
		lv.setAdapter(new ListViewMulAdapter());
	}
	
	private void initData() {
		ListViewMultyItemBean bean;
		//获取assets文件夹管理者
		AssetManager assetManager = this.getAssets();
		
		InputStream in = null;
		BufferedReader br = null;
		
		try {
			// 读student.txt
			// 通过AssetManager获取文件名student.txt的输入流
			in = assetManager.open("student.txt");
			// 把InputStream 转化 BufferedReader
			br = new BufferedReader(new InputStreamReader(in,"gbk"));
			
			String temp;
			while((temp = br.readLine())!= null){
				bean = new ListViewMultyItemBean();
//				Log.d("lu", temp);
				bean.setTitle(temp);
				bean.setContent("Android-1506");
				bean.setImgs(new int[]{R.drawable.qq});
				container.add(bean);
			}
//			Log.d("lu", String.valueOf(bean.getImgs().length)); //1
			
			// 读gift.txt
			in = assetManager.open("gift.txt");
			br = new BufferedReader(new InputStreamReader(in,"gbk"));
			temp = null;
			while((temp = br.readLine())!= null){
				bean = new ListViewMultyItemBean();
//				bean.setTitle("This is your gift ~");
				bean.setTitle(temp);
				bean.setContent(null);
				bean.setImgs(new int[]{R.drawable.qq, R.drawable.weixin, R.drawable.sina});
				container.add(bean);
			}
			
			//洗牌
			Collections.shuffle(container);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class ListViewMulAdapter extends BaseAdapter{

		@Override
		public int getViewTypeCount() {
			return 2;
		}
		
		@Override
		public int getItemViewType(int position) {
			ListViewMultyItemBean bean = container.get(position);
			if (bean.getImgs().length == 1) {
				// 第一种ListView 的item显示的type
				return 0;
			} else if (bean.getImgs().length == 3) {
				// 第二种ListView 的item显示的type
				return 1;
			}
			return super.getItemViewType(position);
		}
		
		@Override
		public int getCount() {
			return container.size();
		}

		@Override
		public Object getItem(int position) {
			return container.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ListViewMultyItemBean bean = null;
			int type = getItemViewType(position);
			switch (type) {
			case 0:
				PersonHolder pholder;
				if(convertView == null){
					pholder = new PersonHolder();
					convertView = inflater.inflate(R.layout.activity_simple_demo,
							parent, false);
					pholder.img = (ImageView) convertView.findViewById(R.id.img);
					pholder.title = (TextView) convertView.findViewById(R.id.title);
					pholder.content = (TextView) convertView.findViewById(R.id.content);
					convertView.setTag(pholder);
				} else{
					pholder = (PersonHolder) convertView.getTag();
				}
				bean = container.get(position);
				pholder.img.setImageResource(bean.getImgs()[0]);
				pholder.title.setText(bean.getTitle());
				pholder.content.setText(bean.getContent());
				break;
				
			case 1:
				GiftHolder gholder;
				if(convertView == null){
					gholder = new GiftHolder();
					convertView = inflater.inflate(R.layout.activity_lvmulty_item2,
							parent, false);
					gholder.img1 = (ImageView) convertView.findViewById(R.id.img1);
					gholder.img2 = (ImageView) convertView.findViewById(R.id.img2);
					gholder.img3 = (ImageView) convertView.findViewById(R.id.img3);
					gholder.tv = (TextView) convertView.findViewById(R.id.tv);
					convertView.setTag(gholder);
				} else{
					gholder = (GiftHolder) convertView.getTag();
				}
				bean = container.get(position);
				gholder.img1.setImageResource(bean.getImgs()[0]);
				gholder.img2.setImageResource(bean.getImgs()[1]);
				gholder.img3.setImageResource(bean.getImgs()[2]);
				gholder.tv.setText(bean.getTitle());
				break;

			default:
				break;
			}
			return convertView;
			
		}
		
		class PersonHolder{
			ImageView img;
			TextView title, content;
		}
		class GiftHolder{
			ImageView img1, img2, img3;
			TextView tv;
		}
		
	}
}
