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
			//接收数据第一种方式
//			String phone = intent.getStringExtra("phone");
//			String password = intent.getStringExtra("password");
			
			//接收数据第二种方式
//			Bundle bundle = intent.getExtras();
//			String phone = bundle.getString("phone");
			
			//接收数据第三种方式
//			User user = (User) intent.getSerializableExtra("user");
//			String name = user.getName();
			
			//接收数据第四种方式 （注意：必须在清单文件AndroidManifest.xml中注册MyApplication）
			MyApplication app = (MyApplication) getApplicationContext();
			User user = app.getUser();
			String name = user.getName();
			
			tv.setText(name + "到此一游");
		}
	}
}
