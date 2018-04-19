package com.example.omw;

import com.Lu.omw.utils.DialogUtils;
import com.example.omw.view.CustomDialog;
import com.example.omw.view.CustomDialogView;
import com.example.omw.view.CustomDialogView.MyOnClickListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Activity50 extends Activity{
	private ListView lv;
	public String[] datas = { "基本对话框", "选择对话框", "单选对话框", "复选对话框", 
			"自定义对话框一", "自定义对话框二" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
		lv = (ListView) findViewById(R.id.lv);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), 
				android.R.layout.simple_dropdown_item_1line, datas);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				alert(position);
			}

		});
		lv.setAdapter(adapter);
	}

	protected void alert(int position) {
		switch (position) {
		case 0:
			// 演示基本对话框
			alertBaseDialog();
			break;
		case 1:
			// 选择对话框
			alertSelectedDialog();
			break;
		case 2:
			// 单选对话框
			alertSingleDialog();
			break;
		case 3:
			// 多选对话框
			alertMultiChoiceDialog();
			break;
		case 4:
			// 自定义对话框
			alertCustomDialog();
			break;
		case 5:
			// 自定义对话框二
			alertCustom2Dialog();
			break;
		default:
			break;
		}
	}

	/**
	 * 自定义对话框
	 */
	private void alertCustom2Dialog() {
		DialogUtils.showDialog(this, "大家好才是真的好", new CustomDialog.OnMClcikListener() {
			
			@Override
			public void onPositiveListener(View view) {
				Toast.makeText(getApplicationContext(), "真的，大家很好", 0).show();
			}
			
			@Override
			public void onNegativeListener(View view) {
				Toast.makeText(getApplicationContext(), "真的，大家很好,你不信嘛？", 0).show();
			}
		});
	}

	private void alertCustomDialog() {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		CustomDialogView dialogView = new CustomDialogView(this);
		dialogView.setContent("R U sure to lieve?");
		b.setView(dialogView);
		final AlertDialog dialog = b.show();
		dialogView.setOnClickListener(new MyOnClickListener() {
			
			@Override
			public void onPositiveListener(View v) {
				dialog.dismiss();
			}
			
			@Override
			public void onNegativeListener(View v) {
				dialog.dismiss();
			}
		});
	}

	private void alertMultiChoiceDialog() {
		final String[] items = { "double kill~", "threeable kill!", "kuadra kill!!", "PENTA KILL" };
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("I have more~");
		b.setMultiChoiceItems(items, new boolean[]{false,true,false,true}, new OnMultiChoiceClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				if (isChecked) {
					Toast.makeText(getApplicationContext(), items[which], 0).show();
				}
			}
		});
		b.setPositiveButton("positive", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "positive", 0).show();
			}
		});
		b.setNegativeButton("negative", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "negative", 0).show();

			}
		});
		b.show();
	}

	private void alertSingleDialog() {
		final String[] items = { "I'm single..", "Me too..."};
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("I'm a single title");
		b.setSingleChoiceItems(items, 0, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), items[which], 0).show();
			}
		});
		b.setPositiveButton("positive", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "positive", 0).show();
			}
		});
		b.setNegativeButton("negative", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "negative", 0).show();

			}
		});
		b.show();
	}

	private void alertSelectedDialog() {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("I'm title");
		b.setMessage("I'm selectedDialog");
		b.setPositiveButton("positive", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "positive", 0).show();
			}
		});
		b.setNegativeButton("negative", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "negative", 0).show();

			}
		});
		b.show();
	}

	private void alertBaseDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("I'm title");
		builder.setIcon(R.drawable.qq);
		builder.setMessage("I'm message");
		builder.show();
	}
}
