package com.example.omw.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.Lu.omw.utils.SDCardUtils;
import com.example.omw.Constant;

import android.app.IntentService;
import android.content.Intent;

public class DownLoadService extends IntentService {

	public DownLoadService(String name) {
		super(name);
	}

	public DownLoadService() {
		this("");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		InputStream is = null;
		try {
			URL url = new URL(Constant.IMAGE_ULR);
			is = url.openStream();
			SDCardUtils
					.saveFileToSdCard(getApplicationContext(), is, "dog.png");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
