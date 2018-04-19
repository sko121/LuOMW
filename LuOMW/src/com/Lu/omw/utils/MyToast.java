package com.Lu.omw.utils;

import com.example.omw.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MyToast {

	/**
	 * @param context: 上下文
	 * @param name: 需要显示的名字
	 */
	public static void showToast(Context context, String name){
		//创建Toast对象
		Toast toast = new Toast(context);
		//拿到布局填充器
		LayoutInflater inflater = LayoutInflater.from(context);
		//把布局文件转化成View
		View v = inflater.inflate(R.layout.mytoast_main, null);
		//给Toast设置已自定义好的view
		toast.setView(v);
		
		//在view中给title赋值
		TextView title = (TextView) v.findViewById(R.id.title);
		title.setText(name);
		
		//设置Toast位置
		toast.setGravity(Gravity.CENTER, 0, 0);
		
		toast.show();
	}
}
