package com.example.omw.view;

import com.Lu.omw.utils.DisplayUtils;
import com.example.omw.R;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author robin 自定义对话框
 */
public class CustomDialog extends Dialog implements
		android.view.View.OnClickListener {
	private Context context;
	private TextView contentTv;
	private Button positiveBtn;
	private Button negativeBtn;

	private OnMClcikListener onClickListener;

	public void setOnClickListener(OnMClcikListener onClickListener) {
		this.onClickListener = onClickListener;
	}

	public CustomDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
		initView();
		initListener();
	}

	/**
	 * 初始化监听器
	 */
	private void initListener() {
		positiveBtn.setOnClickListener(this);
		negativeBtn.setOnClickListener(this);
	}

	/**
	 * 初始化View
	 */
	private void initView() {
		View view = View.inflate(context, R.layout.view_custom_dialog, null);
		contentTv = (TextView) view.findViewById(R.id.dialog_content_tv);
		positiveBtn = (Button) view.findViewById(R.id.dialog_positive_btn);
		negativeBtn = (Button) view.findViewById(R.id.dialog_negative_btn);
		// 对话框的layout设置参数
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				DisplayUtils.dip2px(context, 300), DisplayUtils.dip2px(context,150));
		// px:像素
		// pi:像素密度
		this.setContentView(view, params);
	}

	/**
	 * @param content
	 *            设置对话框的文本
	 */
	public void setContent(String content) {
		if (contentTv != null && content != null) {
			contentTv.setText(content);
		}
	}
	
	/**
	 * @param positiveContent
	 *  设置positive按钮的文本
	 */
	public void setPositiveContent(String positiveStr){
		if(positiveStr != null){
			positiveBtn.setText(positiveStr);
		}
	}

	/**
	 * @param nagetiveContent
	 * 设置negative按钮的文本
	 */
	public void setNegativeContent(String nagetiveContent){
		if(negativeBtn!=null){
			negativeBtn.setText(nagetiveContent);
		}
	}
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.dialog_positive_btn:
			if (onClickListener != null) {
				onClickListener.onPositiveListener(v);
			}
			// 销毁对话框
			CustomDialog.this.dismiss();
			break;
		case R.id.dialog_negative_btn:
			if (onClickListener != null) {
				onClickListener.onNegativeListener(v);
			}
			// 销毁对话框
			CustomDialog.this.dismiss();
			break;

		default:
			break;
		}
	}

	public interface OnMClcikListener {
		public void onPositiveListener(View view);

		public void onNegativeListener(View view);
	}

}
