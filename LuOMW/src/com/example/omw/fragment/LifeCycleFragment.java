package com.example.omw.fragment;

import com.example.omw.R;
import com.example.omw.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LifeCycleFragment extends Fragment {
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d("lu","LifeCycleFragment:onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("lu","LifeCycleFragment:onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.d("lu","LifeCycleFragment:onCreateView");
		return inflater.inflate(R.layout.fragment_content, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		Log.d("lu","LifeCycleFragment:onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		Log.d("lu","LifeCycleFragment:onStart");
		super.onStart();
	}

	@Override
	public void onResume() {
		Log.d("lu","LifeCycleFragment:onResume");
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.d("lu","LifeCycleFragment:onPause");
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.d("lu","LifeCycleFragment:onStop");
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		Log.d("lu","LifeCycleFragment:onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		Log.d("lu","LifeCycleFragment:onDestroy");
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		Log.d("lu","LifeCycleFragment:onDetach");
		super.onDetach();
	}
}
