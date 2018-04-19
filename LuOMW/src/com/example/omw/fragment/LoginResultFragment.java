package com.example.omw.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omw.R;

public class LoginResultFragment extends Fragment{
	
	private TextView tv;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.login, container, false);
		tv = (TextView) view.findViewById(R.id.tv);
		
		Bundle bundle = getArguments();
		if(bundle != null){
			tv.setText(bundle.getString("loginResult"));
		}
		return view;
	}
	
	public void setContent(String result){
		if(tv != null){
			tv.setText(result);
		}
	}
}
