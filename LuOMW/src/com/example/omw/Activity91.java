package com.example.omw;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class Activity91 extends FragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lifecycle_fragment);
		Log.d("lu", "Activity91:onCreate");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d("lu", "Activity81:onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("lu", "Activity81:onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d("lu", "Activity81:onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d("lu", "Activity81:onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("lu","Activity81:onDestroy");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("lu", "Activity81:onRestart");
	}
}
