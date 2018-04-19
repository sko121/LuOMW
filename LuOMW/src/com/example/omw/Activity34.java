package com.example.omw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Lu.omw.utils.MyToast;
import com.example.omw.R;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

public class Activity34 extends Activity{
	private List<Map<String, Object>> personContainer = new ArrayList<Map<String,Object>>();
	private Spinner spinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner);
		
		spinner = (Spinner) findViewById(R.id.spinner);
		//��ʼ������
		initData();
		//����simpleadapter
		SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), 
				personContainer, R.layout.mytoast_main, 
				new String[] {"title"}, new int[] {R.id.title});
		spinner.setAdapter(adapter);
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
//				Toast.makeText(getApplicationContext(), 
//						personContainer.get(position).get("title").toString(), 
//						0).show();
				//ʹ���Զ���Toast
				MyToast.showToast(getApplicationContext(), 
						personContainer.get(position).get("title").toString());
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
	}
	private void initData() {
		AssetManager assetManager = this.getAssets();
		InputStream in = null;
		BufferedReader br = null;
		try {
			// ͨ��AssetManager��ȡ�ļ���student.txt��������
			in = assetManager.open("student.txt");
			// ��InputStream ת�� BufferedReader
			br = new BufferedReader(new InputStreamReader(in, "gbk"));
			// һ��һ�ж�ȡ�ļ�������
			String temp = null;
			while ((temp = br.readLine()) != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				// L.d(temp);
				// namesContainer.add(temp);
				map.put("title", temp);
				personContainer.add(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// �ر���
			if (in != null) {
				try {
					in.close();
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
}
