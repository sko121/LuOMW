package com.example.omw;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.widget.Toast;

public class Activity80 extends Activity implements OnMenuItemClickListener{

	private static String[] items = {"item1", "item2", "item3", "item4"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qqframe);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//添加菜单
		MenuItem item = menu.add("hello");
		//给菜单添加事件
		item.setOnMenuItemClickListener(this);
		// 第一个参数：groupId ，第二参数 ItemId 第三参数 order(排序)， 第四参数 标题
		menu.add(0, 1, 4, "A");
		menu.add(0, 2, 3, "B");
		menu.add(0, 3, 2, "C");
		menu.add(0, 4, 1, "D");
		//添加子菜单
		SubMenu sMenu = menu.addSubMenu("sub");
		for(int i = 0; i<items.length; i++){
			sMenu.add(items[i]);
		}
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onMenuItemClick(MenuItem item) {
		Toast.makeText(getApplicationContext(), "I'm a menu item", Toast.LENGTH_SHORT).show();
		return false;
	}
}
