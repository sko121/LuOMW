package com.example.omw;

import com.example.omw.R;
import com.example.omw.R.id;
import com.example.omw.R.layout;
import com.example.omw.fragment.ContentFragment;
import com.example.omw.fragment.LeftFragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class Activity92 extends FragmentActivity{
	
	private FragmentManager fm;
	private FragmentTransaction ft;
	private LeftFragment leftFragment;
	private ContentFragment contentFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dy_fragment);
		
		fm = getSupportFragmentManager();
		leftFragment = new LeftFragment();
		contentFragment = new ContentFragment();
		
		ft = fm.beginTransaction();
		ft.add(R.id.fl_nav, leftFragment, "leftFragment");
		ft.add(R.id.fl_content, contentFragment, "contentFragment");
		ft.commit();// 异步：The commit does not happen immediately
		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				//延迟再获取，否则将得到null
//				SystemClock.sleep(300);
//				leftFragment = (LeftFragment) fm.findFragmentByTag("leftFragment");
//				contentFragment = (ContentFragment) fm.findFragmentByTag("contentFragment");
//			}
//		}).start();
	}
	
	//中转站
	public void setFragmentContent(String content){
		if(content != null){
			contentFragment.setContent(content);
		}
	}
}
