package com.example.omw;

import com.example.omw.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Activity36 extends Activity{
	private AutoCompleteTextView autoTv;
	public String[] fruits = { "apple", "banana", "grape", "orange",
			"watermelon", "pear", "dragon fruit" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auto_tv);
		autoTv = (AutoCompleteTextView) findViewById(R.id.auto_tv);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				R.layout.auto_style, fruits);
		autoTv.setAdapter(adapter);
	}
}
