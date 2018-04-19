package com.Lu.omw.utils;

import com.example.omw.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author robin �Զ���Toast
 */
public class MToastUtil {

	/**
	 * @param context
	 *            :������
	 * @param name
	 *            ����Ҫ��ʾ������ ��ʾ�ķ���
	 */
	public static void showToast(Context context, String name) {
		// ����Toast ����
		Toast toast = new Toast(context);
		// �õ����������
		LayoutInflater inflater = LayoutInflater.from(context);
		// �Ѳ����ļ�ת����View
		View view = inflater.inflate(R.layout.view_lv_item, null);
		// ��toast �����Զ����view
		toast.setView(view);

		// ��view���ҵ�title ��TextView
		TextView titleTv = (TextView) view.findViewById(R.id.title);
		titleTv.setText(name);
		// ����toastλ��
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	/**
	 * @param context
	 * @param content
	 * �Զ���ײ���Toast
	 */
	public static void showBottomToast(Context context, String content) {
		Toast toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.show();
	}
}
