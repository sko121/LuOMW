package com.Lu.omw.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import android.util.Log;

import com.Lu.omw.bean.NewsCatalogBean;
import com.google.gson.Gson;

public class NetUtils {
	/**
	 * @param result
	 * @return 把从获取的result 转换成 List<NewsBean>
	 */
	public static List<NewsCatalogBean.NewsBean> getDataFromResult(Gson gson,String result) {
		if(gson==null){
			throw new RuntimeException("gson must not be null");
		}
		NewsCatalogBean newsCatalogBean = gson.fromJson(result,
				NewsCatalogBean.class);
		return newsCatalogBean.data;
	}
	
	/**
	 * @param url
	 * @return 从网络上的url获取数据转换String类型
	 */
	public static String getResultFromUrl(String url) {
		InputStream is = null;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			// 定位网络的资源
			URL mUrl = new URL(url);
			// 获取连接对象
			URLConnection connection = mUrl.openConnection();
			// 获取网络流对象
			is = connection.getInputStream();
			// 拼接网络字符串
			br = new BufferedReader(new InputStreamReader(is));
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
		} catch (Exception e) {
			Log.d("lu", e.getMessage());
		} finally {
			close(is, br);
		}
		return sb.toString();
	}

	/**
	 * @param is
	 * @param br
	 * 关闭 流
	 */
	private static void close(InputStream is, BufferedReader br) {
		try {
			if (is != null) {
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (br != null) {
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
