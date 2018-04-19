package com.example.omw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.omw.R;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Activity33 extends Activity{

	//1.声明spinner控件
	private Spinner spinner;
	//2.资源容器
	private List<String> namesContainer = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner);
		//3.找到spinner
		spinner = (Spinner) findViewById(R.id.spinner);
		//4.初始化数据：assets/student.txt
		initData();
		// 4 创建一个数组的ArrayAdapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_dropdown_item_1line,namesContainer);
		//设置适配器
		spinner.setAdapter(adapter);
		
		//设置监听器
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), namesContainer.get(position), 0).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	private void initData() {
		//获取assets文件夹管理者
		AssetManager assetManager = this.getAssets();
		
		InputStream in = null;
		BufferedReader br = null;
		
		try {
			// 通过AssetManager获取文件名student.txt的输入流
			in = assetManager.open("student.txt");
			// 把InputStream 转化 BufferedReader
			br = new BufferedReader(new InputStreamReader(in,"gbk"));
			
			String temp;
			while((temp = br.readLine())!= null){
				Log.d("lu", temp);
				namesContainer.add(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
