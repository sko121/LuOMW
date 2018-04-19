package com.example.omw.view;

import android.content.Context;
import android.widget.RelativeLayout;

public abstract class BaseView extends RelativeLayout{
	protected Context myContext;
	
	public BaseView(Context context) {
		super(context);
		this.myContext = context;
		
		initView();
		initListener();
		initData();
	}

	private void initData() {
	}

	public abstract void initListener() ;

	public abstract void initView() ;

}
