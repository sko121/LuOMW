package com.example.omw.view;

import com.example.omw.R;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDialogView extends LinearLayout implements OnClickListener{
	
	private Context context;
	private TextView contentTv;
	private Button positiveBtn;
	private Button negativeBtn;
	private MyOnClickListener onClickListener;
	
	public void setOnClickListener(MyOnClickListener onClickListener){
		this.onClickListener  =onClickListener;
	}
	
	public CustomDialogView(Context context) {
		super(context);
		this.context = context;
		initView();
		initListener();
	}
	
	/**
	 * 初始化view
	 */
	private void initView() {
		View view = View.inflate(context, R.layout.view_custom_dialog, this);
		contentTv = (TextView) view.findViewById(R.id.dialog_content_tv);
		positiveBtn = (Button) view.findViewById(R.id.dialog_positive_btn);
		negativeBtn = (Button) view.findViewById(R.id.dialog_negative_btn);
	}
	
	/**
	 * 初始化监听器
	 */
	private void initListener(){
		positiveBtn.setOnClickListener(this);
		negativeBtn.setOnClickListener(this);
	}
	
	/**
	 * @param content
	 * 设置对话框的文本
	 */
	public void setContent(String content) {
		if (contentTv != null && content != null) {
			contentTv.setText(content);
		}
	}
	
	@Override
	public void onClick(View v) {
		int id=v.getId();
		switch (id) {
		case R.id.dialog_positive_btn:
			Toast.makeText(getContext(), "正", 0).show();
			if(onClickListener!=null){
				onClickListener.onPositiveListener(v);
			}
			break;
		case R.id.dialog_negative_btn:
			Toast.makeText(getContext(), "负", 0).show();
			if(onClickListener!=null){
				onClickListener.onNegativeListener(v);
			}
			break;

		default:
			break;
		}
	}

	public interface MyOnClickListener{
		public void onPositiveListener(View v);
		public void onNegativeListener(View v);
	}

}
