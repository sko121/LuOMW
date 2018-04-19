package com.example.omw.view;

import com.example.omw.R;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Calculate_L
 */
public class LinearlayoutView extends BaseView{

	private Button btn;
	
	public LinearlayoutView(Context context) {
		super(context);
	}

	@Override
	public void initListener() {
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(myContext, "~", 0).show();
			}
		});
	}

	@Override
	public void initView() {
		View view = View.inflate(myContext, R.layout.calculate, this);
		btn = (Button) view.findViewById(R.id.eq);
	}

}
