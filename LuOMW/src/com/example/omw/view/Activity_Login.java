package com.example.omw.view;

import com.example.omw.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity_Login extends BaseView{
	
	private Button btn;
	
	public Activity_Login(Context context) {
		super(context);
	}

	@Override
	public void initListener() {
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(myContext, "Ã»±À°É~", 0).show();
			}
		});
	}

	@Override
	public void initView() {
		View view = View.inflate(myContext, R.layout.activity_login, this);
		btn = (Button) view.findViewById(R.id.login);
	}
	

}
