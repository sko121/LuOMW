package com.example.omw;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.widget.Toast;

public class Activity81 extends Activity{

	private static String[] items = {"item1", "item2", "item3", "item4"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qqframe);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//解析menu下的文件到menu
		getMenuInflater().inflate(R.menu.option_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	//item被选中时会回调
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item1:
			Toast.makeText(this, item.getTitle(), 0).show();
			break;
		case R.id.item2:
			Toast.makeText(this, item.getTitle(), 0).show();
			break;
		case R.id.item3:
			Toast.makeText(this, item.getTitle(), 0).show();
			break;
		case R.id.item4:
			Toast.makeText(this, item.getTitle(), 0).show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
