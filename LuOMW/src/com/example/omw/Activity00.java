package com.example.omw;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Activity00 extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkbox_main);
		
		CheckBox cb1 = (CheckBox) findViewById(R.id.cb1);
		CheckBox cb2 = (CheckBox) findViewById(R.id.cb2);
		CheckBox cb3 = (CheckBox) findViewById(R.id.cb3);
		
	}

	public void sendMessage(View v){
		CheckBox cb = (CheckBox) v;
		switch (v.getId()) {
		case R.id.cb1:
			if(cb.isChecked()){
				Toast.makeText(getApplicationContext(), "Œ“œ≤ª∂≥‘∑π", Toast.LENGTH_SHORT).show();
			} else{
				Toast.makeText(getApplicationContext(), "Œ“≤ªœ≤ª∂≥‘∑π", Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}
	}
}
