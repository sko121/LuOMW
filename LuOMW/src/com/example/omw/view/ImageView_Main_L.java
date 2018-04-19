package com.example.omw.view;

import com.example.omw.R;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ImageView_Main_L extends BaseView{

	private Button btn;
	
	public ImageView_Main_L(Context context) {
		super(context);
	}

	@Override
	public void initListener() {
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(myContext, "不会出结果的~", 0).show();
			}
		});
	}

	@Override
	public void initView() {
		View view = View.inflate(myContext, R.layout.imageview_main, this);
		btn = (Button) view.findViewById(R.id.b1);
	}

}
