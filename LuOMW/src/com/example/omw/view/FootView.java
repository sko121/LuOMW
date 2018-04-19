package com.example.omw.view;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.omw.R;

public class FootView extends RelativeLayout{
	
	private Context context;

	public FootView(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	private void initView() {
		View.inflate(context, R.layout.view_foot_refresh, this);
	}

}
