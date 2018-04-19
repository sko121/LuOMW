package com.Lu.omw.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.example.omw.R;

import android.content.Context;
import android.util.Log;

public class FileUtils {

	/**
	 * 在Assets目录下读取指定文件，并产生List集合
	 * @param context
	 * @param clazz
	 * @param fileName
	 * @return
	 */
	public static <T> List<T> readDataFromAssets(Context context, 
			Class<T> clazz, String fileName){
		InputStream is = null;
		BufferedReader br = null;
		List<T> data = new ArrayList<T>();
		try {
			is = context.getAssets().open(fileName);
			br = new BufferedReader(new InputStreamReader(is, "gbk"));
			String temp = null;
			while ((temp = br.readLine()) != null) {
				T t = clazz.newInstance();
				Method[] methods = clazz.getDeclaredMethods();
				for(int i = 0; i<methods.length; i++){
					Method method = methods[i];
//					if("setId".equals(method.getName())){
//						method.invoke(t, i);
//					}
					if("setGiftName".equals(method.getName())){
						method.invoke(t, temp);
					}
					if ("setPicName".equals(method.getName())) {
						method.invoke(t, R.drawable.gift_lipin);
					}
					//==============================================
					if("setParams".equals(method.getName())){
						method.invoke(t, temp);
					}
					//==============================================
				}
				data.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(is, br);
		}
//		Log.d("lu", data.toString());
		return data;
	}

	public static void close(InputStream is, BufferedReader br) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
