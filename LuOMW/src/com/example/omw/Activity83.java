package com.example.omw;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;

public class Activity83 extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_popup_menu);
	}

	@SuppressLint("NewApi")
	public void show(View v) {
		PopupMenu popupMenu = new PopupMenu(this, v);
		MenuInflater inflater = popupMenu.getMenuInflater();
		Menu menu = popupMenu.getMenu();
		// 把menu文件夹下的菜单文件解析到menu中
		inflater.inflate(R.menu.popup_menu, menu);
		popupMenu.show();
	}
}
