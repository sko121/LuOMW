package com.example.omw;

import java.util.ArrayList;
import java.util.List;

import com.example.omw.view.Activity_Login;
import com.example.omw.view.Activity_Lv_Head_L;
import com.example.omw.view.ImageView_Main_L;
import com.example.omw.view.LinearlayoutView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Activity60 extends Activity implements OnClickListener{
	private ViewPager vp;
	private List<View> viewContainer = new ArrayList<View>();
	private Button me_btn, news_btn, money_btn, freind_btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager);
		
		vp = (ViewPager) findViewById(R.id.viewPager);
		initData();
		initState();
		initListener();
		
		vp.setAdapter(new MyPagerAdapter());
		
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				changeStateByPosition(position);
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				
			}
		});
	}
	
	private void initState() {
		me_btn.setSelected(true);
	}

	private void initListener() {
		me_btn.setOnClickListener(this);
		news_btn.setOnClickListener(this);
		money_btn.setOnClickListener(this);
		freind_btn.setOnClickListener(this);
	}

	/**
	 * 添加四个演示的View
	 */
	private void initData() {
		me_btn = (Button) findViewById(R.id.me_btn);
		news_btn = (Button) findViewById(R.id.news_btn);
		money_btn = (Button) findViewById(R.id.money_btn);
		freind_btn = (Button) findViewById(R.id.friend_btn);
//		viewContainer.add(View.inflate(this, R.layout.activity_lv_head, null));
//		viewContainer.add(View.inflate(this, R.layout.calculate, null));
//		viewContainer.add(View.inflate(this, R.layout.imageview_main, null));
//		viewContainer.add(View.inflate(this, R.layout.activity_login, null));
		
		//通过将布局实体化，从而可以在ViewPager中实现每个布局里面的功能，且方便以后对这个布局的调用
		Activity_Lv_Head_L activity_lv_head = new Activity_Lv_Head_L(this);
		LinearlayoutView calculate = new LinearlayoutView(this);
		ImageView_Main_L iamgeview_main = new ImageView_Main_L(this);
		Activity_Login activity_login = new Activity_Login(this);
		viewContainer.add(activity_lv_head);
		viewContainer.add(calculate);
		viewContainer.add(iamgeview_main);
		viewContainer.add(activity_login);
	}
	
	class MyPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return viewContainer.size();
		}

		@Override
		public boolean isViewFromObject(View v, Object o) {
			return v == o;
		}
		
		// 把view添加到ViewGroup
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(viewContainer.get(position), 0);
			return viewContainer.get(position);
		}
		
		// 从ViewGroup里面移除
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			//必须去掉父类方法
//			super.destroyItem(container, position, object);
			container.removeView(viewContainer.get(position));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.me_btn:
			//滑动翻页
			vp.setCurrentItem(0, false);
			//按钮翻页
			changeStateByPosition(0);
			break;
		case R.id.news_btn:
			vp.setCurrentItem(1, false);
			changeStateByPosition(1);
			break;
		case R.id.money_btn:
			vp.setCurrentItem(2, false);
			changeStateByPosition(2);
			break;
		case R.id.friend_btn:
			vp.setCurrentItem(3, false);
			changeStateByPosition(3);
			break;

		default:
			break;
		}
	}
	
	public void changeStateByPosition(int position) {
		switch (position) {
		case 0:
			setBtnState(true, false, false, false);
			break;
		case 1:
			setBtnState(false, true, false, false);
			break;
		case 2:
			setBtnState(false, false, true, false);
			break;
		case 3:
			setBtnState(false, false, false, true);
			break;
		default:
			break;
		}
}

	/**
	 * @param me_btnState
	 * @param news_btnState
	 * @param money_btnState
	 * @param freind_btnState
	 *            设置按钮的状态
	 */
	private void setBtnState(boolean me_btnState, boolean news_btnState,
			boolean money_btnState, boolean freind_btnState) {
		me_btn.setSelected(me_btnState);
		news_btn.setSelected(news_btnState);
		money_btn.setSelected(money_btnState);
		freind_btn.setSelected(freind_btnState);
	}
}