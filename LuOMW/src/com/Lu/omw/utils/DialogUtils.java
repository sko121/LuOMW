package com.Lu.omw.utils;

import android.content.Context;
import android.text.TextUtils;

import com.example.omw.R;
import com.example.omw.view.CustomDialog;

/**
 * @author robin 对话框的工具类
 */
public class DialogUtils {
	
	private static CustomDialog dialog;
	/**
	 * @param context
	 * @param content:对话框显示的文本
	 * @param onMClickListner：对话框回调接口
	 * 显示默认对话框
	 */
	public static void showDialog(Context context, String content,
			CustomDialog.OnMClcikListener onMClickListner) {
//		dialog=new CustomDialog(context, R.style.myDialogTheme);
//		dialog.setContent(content);
//		dialog.setOnClickListener(onMClickListner);
//		dialog.show();
		showDialog(context, content, null, null, onMClickListner);
	}

	/**
	 * @param context
	 * @param content
	 * @param positiveStr
	 * @param negativeStr
	 * @param onMClickListner
	 *            显示对话框
	 */
	public static void showDialog(Context context, String content,
			String positiveStr, String negativeStr,
			CustomDialog.OnMClcikListener onMClickListner) {
		dialog = new CustomDialog(context, R.style.myDialogTheme);
		dialog.setContent(content);
		if (!TextUtils.isEmpty(positiveStr)) {
			dialog.setPositiveContent(positiveStr);
		}
		if (!TextUtils.isEmpty(negativeStr)) {
			dialog.setNegativeContent(negativeStr);
		}
		dialog.setOnClickListener(onMClickListner);
		dialog.show();
	}

}
