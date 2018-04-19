package com.example.omw;

import com.example.omw.fragment.LoginFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class Activity93 extends FragmentActivity{

	private FragmentManager fm;
	private FragmentTransaction ft;
	private LoginFragment loginFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_container);
		
		fm = getSupportFragmentManager();
		loginFragment = new LoginFragment();
		ft = fm.beginTransaction();
		ft.add(R.id.container, loginFragment, loginFragment.getClass().getSimpleName());
		//把事务操作添加到返回栈中
//		ft.addToBackStack(null);
		ft.commit();
	}
	
	public void switchFragment(Fragment f, Bundle bundle){
		if(f != null){
			//重新开启一次事务
			ft = fm.beginTransaction();
			ft.replace(R.id.container, f, f.getClass().getSimpleName());
			ft.addToBackStack(null);
			ft.commit();
		}
		
	}
}
