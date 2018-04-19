package com.example.omw;

import com.example.omw.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity10 extends Activity{
	private Button calculate, gravitytest, qqframe, relative, relative0,
		relative2, relative3, relative4, textviewdemo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_main);
		initButton();
	}

	private void initButton() {
		calculate = (Button) findViewById(R.id.calculate);
		gravitytest = (Button) findViewById(R.id.gravitytest);
		qqframe = (Button) findViewById(R.id.qqframe);
		relative = (Button) findViewById(R.id.relative);
		relative0 = (Button) findViewById(R.id.relative0);
		relative2 = (Button) findViewById(R.id.relative2);
		relative3 = (Button) findViewById(R.id.relative3);
		relative4 = (Button) findViewById(R.id.relative4);
		textviewdemo = (Button) findViewById(R.id.textviewdemo);
	}
	
	public void calculate(View v){
		setContentView(R.layout.calculate);
	}
	public void gravitytest(View v){
		setContentView(R.layout.gravitytest);
	}
	public void qqframe(View v){
		setContentView(R.layout.qqframe);
	}
	public void relative(View v){
		setContentView(R.layout.relative);
	}
	public void relative0(View v){
		setContentView(R.layout.relative0);
	}
	public void relative2(View v){
		setContentView(R.layout.relative2);
	}
	public void relative3(View v){
		setContentView(R.layout.relative3);
	}
	public void relative4(View v){
		setContentView(R.layout.relative4);
	}
	public void textviewdemo(View v){
		setContentView(R.layout.textviewdemo);
	}
}
