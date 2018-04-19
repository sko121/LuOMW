package com.example.omw;

import com.Lu.omw.bean.User;
import com.example.omw.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

public class CheckLoginActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		TextView tv = (TextView) findViewById(R.id.tv);
		
		Intent intent = getIntent();
		
		if(intent != null){
			//�������ݵ�һ�ַ�ʽ
//			String phone = intent.getStringExtra("phone");
//			String password = intent.getStringExtra("password");
			
			//�������ݵڶ��ַ�ʽ
//			Bundle bundle = intent.getExtras();
//			String phone = bundle.getString("phone");
			
			//�������ݵ����ַ�ʽ
//			User user = (User) intent.getSerializableExtra("user");
//			String name = user.getName();
			
			//�������ݵ����ַ�ʽ ��ע�⣺�������嵥�ļ�AndroidManifest.xml��ע��MyApplication��
			MyApplication app = (MyApplication) getApplicationContext();
			User user = app.getUser();
			String name = user.getName();
			
			tv.setText(name + "����һ��");
		}
	}
}
