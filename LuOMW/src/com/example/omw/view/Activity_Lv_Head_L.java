package com.example.omw.view;

import com.example.omw.R;

import android.content.Context;
import android.view.View;

public class Activity_Lv_Head_L extends BaseView{

	public Activity_Lv_Head_L(Context context) {
		super(context);
	}

	@Override
	public void initListener() {
		
	}

	@Override
	public void initView() {
		View view = View.inflate(myContext, R.layout.activity_lv_head, this);
	}

}
