package com.example.omw;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.Lu.omw.bean.User;
import com.example.omw.R;

public class Activity21 extends ActionBarActivity {

	private EditText etPhone,etPassword;
	private CheckBox cb;
	private SharedPreferences sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		sp = getSharedPreferences("login", Context.MODE_PRIVATE);
		initView();
		initState();
	}

	private void initState() {
		String phone = sp.getString("phone", "");
		String password = sp.getString("password", "");
		if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)){
			return;
		}
		etPhone.setText(phone);
		etPassword.setText(password);
	}

	private void initView() {
		etPhone = (EditText) findViewById(R.id.phone);
		etPassword = (EditText) findViewById(R.id.password);
		cb = (CheckBox) findViewById(R.id.cb);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v){
		
		String phone = etPhone.getText().toString();
		String password = etPassword.getText().toString();
		
		
		if(TextUtils.isEmpty(phone)){
			Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
			return;
		} 
		if(TextUtils.isEmpty(password)){
			Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
			return;
		} 
		if("10086".equals(phone) && "1111".equals(password)){
			
			Intent intent = new Intent();
			
			//Activity间传递数据方式一
//			intent.putExtra("phone", phone);
//			intent.putExtra("password", password);
			
			//Activity间传递数据二
//			Bundle bundle = new Bundle();
//			bundle.putString("phone", phone);
//			intent.putExtras(bundle);
			
			//Activity间传递数据三
//			User user = new User("lu", phone, password);
//			intent.putExtra("user", user);
			
			//Activity间传递数据四
			User user = new User("卢", phone, password);
			MyApplication app = (MyApplication) getApplicationContext();
			app.setUser(user);
			
			intent.setClass(this, CheckLoginActivity.class);
			startActivity(intent);
		} else{
			Toast.makeText(this, "号码或密码错误", Toast.LENGTH_SHORT).show();
		}
		
		if(cb.isChecked()){
			Editor editor = sp.edit();
			editor.putString("phone", phone);
			editor.putString("password", password);
			editor.commit();
		} else{
			Editor editor = sp.edit();
			editor.clear();
			editor.commit();
		}
	}
}
