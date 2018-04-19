package com.example.omw.fragment;

import com.example.omw.R;
import com.example.omw.R.id;
import com.example.omw.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFragment extends Fragment {
	private TextView contentTv;

	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_content, container,
				false);
		contentTv = (TextView) view.findViewById(R.id.content_tv);
		return view;
	}
	
	/**
	 * …Ë÷√TextView Œƒ±æ
	 */
	public void setContent(String content) {
		if (contentTv != null) {
			contentTv.setText(content);
		}
	}
}
