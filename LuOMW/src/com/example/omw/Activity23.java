package com.example.omw;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity23 extends Activity implements OnClickListener{
	Button btn1, btn2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_install_apk);
		initView();
		initListener();
	}

	private void initListener() {
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
	}

	private void initView() {
		btn1 = (Button) findViewById(R.id.btn_install);
		btn2 = (Button) findViewById(R.id.btn_uninstall);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent;
		
		switch (id) {
		case R.id.btn_install:
			intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
			intent.setData(Uri.fromFile(prepareApk("Android_week8_d1_mission.apk")));
			startActivity(intent);
			break;
		case R.id.btn_uninstall:
			intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
			intent.setData(Uri.parse("package:com.example.omw"));
			startActivity(intent);
			break;

		default:
			break;
		}
	}
	
	public File prepareApk(String filename) {
		InputStream is = null;
		// 缓存
		byte[] buffer = new byte[1024 * 8];
		FileOutputStream fos = null;
		String path = "";
		try {
			// 获取存储卡的文件夹
			File dir = Environment.getExternalStorageDirectory();
			// 拼接文件名
			path = dir.getCanonicalPath() + "/" + filename;
			is = this.getAssets().open(filename);
			Log.d("lu",path);
			fos = new FileOutputStream(path);
			int length = 0;
			while ((length = is.read(buffer)) != -1) {
				fos.write(buffer, 0, length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new File(path);
	}
}
