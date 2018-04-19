package com.example.omw;

import com.example.omw.weiget.RectView;

import android.app.Activity;
import android.os.Bundle;

public class Activity151 extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new RectView(this));
	}

}
