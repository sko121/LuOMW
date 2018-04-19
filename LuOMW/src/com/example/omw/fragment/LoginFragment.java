package com.example.omw.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.omw.Activity93;
import com.example.omw.R;

public class LoginFragment extends Fragment implements OnClickListener{
	private Button login;
	private EditText etPhone;
	private EditText etPassword;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_login, container, false);
		login = (Button) view.findViewById(R.id.login);
		etPhone = (EditText) view.findViewById(R.id.phone);
		etPassword = (EditText) view.findViewById(R.id.password);
		
		login.setOnClickListener(this);
		return view;
	}
	
	@Override
	public void onClick(View v) {
		String phone = etPhone.getText().toString();
		String password = etPassword.getText().toString();
		
		if("10086".equals(phone) && "1111".equals(password)){
			// 跳转到LoginResultFragment--->中转站Activity
			if(getActivity() instanceof Activity93){
				Activity93 activity93 = (Activity93) getActivity();
				LoginResultFragment loginResultFragment = new LoginResultFragment();
				Bundle bundle = new Bundle();
				bundle.putString("loginResult", "慢慢来，比较快");
				loginResultFragment.setArguments(bundle);
				activity93.switchFragment(loginResultFragment, bundle);
			}
		}
	}

}
