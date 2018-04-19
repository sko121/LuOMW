package com.Lu.omw.utils;

import com.example.omw.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author robin 自定义Toast
 */
public class MToastUtil {

	/**
	 * @param context
	 *            :上下文
	 * @param name
	 *            ：需要显示的名字 显示的方法
	 */
	public static void showToast(Context context, String name) {
		// 创建Toast 对象
		Toast toast = new Toast(context);
		// 拿到布局填充器
		LayoutInflater inflater = LayoutInflater.from(context);
		// 把布局文件转化成View
		View view = inflater.inflate(R.layout.view_lv_item, null);
		// 给toast 设置自定义的view
		toast.setView(view);

		// 在view中找到title 的TextView
		TextView titleTv = (TextView) view.findViewById(R.id.title);
		titleTv.setText(name);
		// 设置toast位置
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	/**
	 * @param context
	 * @param content
	 * 自定义底部的Toast
	 */
	public static void showBottomToast(Context context, String content) {
		Toast toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.show();
	}
}
