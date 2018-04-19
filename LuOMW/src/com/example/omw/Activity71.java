package com.example.omw;

import com.Lu.omw.utils.PatternUtils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity71 extends Activity{
	private TextView et_phone;
	private Button btn_phone;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call_phone);
		
		initView();
		initState();
		initListener();
	}

	private void initListener() {
		btn_phone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String phone = et_phone.getText().toString();
				if(TextUtils.isEmpty(phone)){
					Toast.makeText(getApplicationContext(), "哥们别捣乱",
							Toast.LENGTH_SHORT).show();
					return;
				}
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:" + phone));
				Activity71.this.startActivity(intent);
			}
		});
		et_phone.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// 校验手机格式，假如符合设置可用状态
				if (PatternUtils.isMobile(s.toString())) {
					btn_phone.setEnabled(true);
				} else {
					btn_phone.setEnabled(false);
				}
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
	}

	private void initState() {
		//设置按钮停用
		btn_phone.setEnabled(false);
	}

	private void initView() {
		et_phone = (TextView) findViewById(R.id.et_phone);
		btn_phone = (Button) findViewById(R.id.btn_phone);
	}
}
