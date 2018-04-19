package com.example.omw.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.omw.R;

public class PopupView extends BaseView implements OnClickListener {
	private TextView topTv;
	private TextView deleteTv;

	public PopupView(Context context) {
		super(context);
	}

	@Override
	public void initView() {
		View view = View.inflate(myContext, R.layout.view_popup_window, this);
		topTv = (TextView) view.findViewById(R.id.tv_top);
		deleteTv = (TextView) view.findViewById(R.id.tv_delete);
	}
	
	@Override
	public void initListener() {
		topTv.setOnClickListener(this);
		deleteTv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_top:
			// 订阅
			if (onPopupClickListener != null) {
				onPopupClickListener.onPopupClickTop(v);
			}
			break;

		case R.id.tv_delete:
			// 订阅
			if (onPopupClickListener != null) {
				onPopupClickListener.onPopupClickDelete(v);
			}
			break;

		default:
			break;
		}
	}

	/**
	 * @author robin 定一个接口
	 */
	public interface OnPopupClickListener {
		public void onPopupClickTop(View v);

		public void onPopupClickDelete(View v);
	}

	public OnPopupClickListener onPopupClickListener;

	public void setOnPopupClickListener(
			OnPopupClickListener onPopupClickListener) {
		this.onPopupClickListener = onPopupClickListener;
	}

}
