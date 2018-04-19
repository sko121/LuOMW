package com.Lu.omw.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class SDCardUtils {

	public static boolean saveFileToSdCard(Context context, InputStream is,
			String fileName) {
		// 获取Sdcard 外部路径
		String path = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		Log.d("sdcardPath:", path);
		// 判断SdCard 是否挂载
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			BufferedInputStream bis = null;
			FileOutputStream fos = null;
			try {
				bis = new BufferedInputStream(is);
				File file = new File(path, fileName);
				fos = new FileOutputStream(file);
				byte[] buffer = new byte[1024 * 8];
				int temp = 0;
				while ((temp = bis.read(buffer)) != -1) {
					fos.write(buffer, 0, temp);
				}
				Log.d("download", "succeed");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		return false;
	}
}
