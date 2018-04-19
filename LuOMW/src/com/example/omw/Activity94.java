package com.example.omw;

import java.util.ArrayList;
import java.util.List;

import com.example.omw.fragment.Activity_Login_Fragment;
import com.example.omw.fragment.Activity_Lv_Head_L_Fragment;
import com.example.omw.fragment.ImageView_Main_L_Fragment;
import com.example.omw.fragment.LinearlayoutFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity94 extends FragmentActivity implements OnClickListener {

	private List<Fragment> fragmentContainer = new ArrayList<Fragment>();
	private FragmentManager fm;
	private ViewPager vp;
	private Button me_btn;
	private Button news_btn;
	private Button money_btn;
	private Button friend_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager);

		vp = (ViewPager) findViewById(R.id.viewPager);
		fm = getSupportFragmentManager();
		initView();
		initState();
		initListener();

		vp.setAdapter(new MyPagerAdapter(fm));

		// vp 设置监听器
		// Callback interface for responding to changing state of the selected
		// page.
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			// This method will be invoked when a new page becomes selected.
			@Override
			public void onPageSelected(int position) {
				Log.d("lu", "onPageSelected:" + "position:" + position);
				changeStateByPosition(position);
			}

			// This method will be invoked when the current page is scrolled
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				Log.d("lu", "onPageScrolled:" + "position:" + position
						+ "---positionOffset:" + positionOffset
						+ "---positionOffsetPixels:" + positionOffsetPixels);
				// changeStateByPosition(position);
			}

			// Called when the scroll state changes.
			@Override
			public void onPageScrollStateChanged(int state) {
				Log.d("lu", "state:" + state);
			}
		});
	}

	private void initListener() {
		me_btn.setOnClickListener(this);
		news_btn.setOnClickListener(this);
		money_btn.setOnClickListener(this);
		friend_btn.setOnClickListener(this);
	}

	private void initState() {
		me_btn.setSelected(true);
	}

	private void initView() {
		me_btn = (Button) findViewById(R.id.me_btn);
		news_btn = (Button) findViewById(R.id.news_btn);
		money_btn = (Button) findViewById(R.id.money_btn);
		friend_btn = (Button) findViewById(R.id.friend_btn);
		Activity_Lv_Head_L_Fragment f1 = new Activity_Lv_Head_L_Fragment();
		LinearlayoutFragment f2 = new LinearlayoutFragment();
		ImageView_Main_L_Fragment f3 = new ImageView_Main_L_Fragment();
		Activity_Login_Fragment f4 = new Activity_Login_Fragment();
		fragmentContainer.add(f1);
		fragmentContainer.add(f2);
		fragmentContainer.add(f3);
		fragmentContainer.add(f4);
	}

	class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return fragmentContainer.get(position);
		}

		@Override
		public int getCount() {
			return fragmentContainer.size();
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

	private void setBtnState(boolean me_btnState, boolean news_btnState,
			boolean money_btnState, boolean freind_btnState) {
		me_btn.setSelected(me_btnState);
		news_btn.setSelected(news_btnState);
		money_btn.setSelected(money_btnState);
		friend_btn.setSelected(freind_btnState);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.me_btn:
			// 第二个参数的描述：smoothScroll True to smoothly scroll to the new item,
			// false to transition immediately
			vp.setCurrentItem(0, false);// 设置ViewPager 的当前页
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
}
