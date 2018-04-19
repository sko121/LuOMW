package com.example.omw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.Lu.omw.bean.GiftBean;
import com.Lu.omw.utils.FileUtils;
import com.example.omw.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity35 extends Activity{

	private GridView gv;
	private List<GiftBean> gifts = new ArrayList<GiftBean>();
	
	private LayoutInflater inflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gridview);
		inflater = LayoutInflater.from(this);
		gv = (GridView) findViewById(R.id.gift_gv);
//		initData();
		gifts = FileUtils.readDataFromAssets(this, GiftBean.class, "gift.txt");
		gv.setAdapter(new GiftAdapter());
	}

//	已加入Utils豪华午餐
//	private void initData() {
//		InputStream in = null;
//		BufferedReader br = null;
//		try {
//			in = getAssets().open("gift.txt");
//			br = new BufferedReader(new InputStreamReader(in, "gbk"));
//			String temp = null;
//			while ((temp = br.readLine()) != null) {
//				GiftBean gift = new GiftBean(R.drawable.gift_lipin, temp);
//				gifts.add(gift);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (in != null) {
//				try {
//					in.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if (br != null) {
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	class GiftAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return gifts.size();
		}

		@Override
		public Object getItem(int position) {
			return gifts.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if(convertView == null){
				convertView = inflater.inflate(R.layout.view_gv_item, 
						parent, false);
				holder = new ViewHolder();
				holder.giftImage = (ImageView) convertView.findViewById(R.id.gift_image);
				holder.giftTv = (TextView) convertView.findViewById(R.id.gift_tv);
				convertView.setTag(holder);
			} else{
				holder = (ViewHolder) convertView.getTag();
			}
			GiftBean gift = gifts.get(position);
			holder.giftTv.setText(gift.getGiftName());
			return convertView;
		}
		
	}
	class ViewHolder {
		public ImageView giftImage;
		public TextView giftTv;
	}
}
