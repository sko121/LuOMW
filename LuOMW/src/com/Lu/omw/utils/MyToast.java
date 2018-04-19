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
	 * @param context: ������
	 * @param name: ��Ҫ��ʾ������
	 */
	public static void showToast(Context context, String name){
		//����Toast����
		Toast toast = new Toast(context);
		//�õ����������
		LayoutInflater inflater = LayoutInflater.from(context);
		//�Ѳ����ļ�ת����View
		View v = inflater.inflate(R.layout.mytoast_main, null);
		//��Toast�������Զ���õ�view
		toast.setView(v);
		
		//��view�и�title��ֵ
		TextView title = (TextView) v.findViewById(R.id.title);
		title.setText(name);
		
		//����Toastλ��
		toast.setGravity(Gravity.CENTER, 0, 0);
		
		toast.show();
	}
}
