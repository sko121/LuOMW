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

	//1.����spinner�ؼ�
	private Spinner spinner;
	//2.��Դ����
	private List<String> namesContainer = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner);
		//3.�ҵ�spinner
		spinner = (Spinner) findViewById(R.id.spinner);
		//4.��ʼ�����ݣ�assets/student.txt
		initData();
		// 4 ����һ�������ArrayAdapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_dropdown_item_1line,namesContainer);
		//����������
		spinner.setAdapter(adapter);
		
		//���ü�����
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
		//��ȡassets�ļ��й�����
		AssetManager assetManager = this.getAssets();
		
		InputStream in = null;
		BufferedReader br = null;
		
		try {
			// ͨ��AssetManager��ȡ�ļ���student.txt��������
			in = assetManager.open("student.txt");
			// ��InputStream ת�� BufferedReader
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
