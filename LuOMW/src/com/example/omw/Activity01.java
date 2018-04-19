package com.example.omw;

import com.example.omw.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Activity01 extends ActionBarActivity {
	private ImageView img1,img2;
	//����ʯͷ��
	private int[] actions = new int[]{
		R.drawable.icon_jiandao, R.drawable.icon_shitou, R.drawable.icon_bu
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview_main);
		initImg();
	}
	
	private void initImg() {
		img1 = (ImageView) findViewById(R.id.img1);
		img2 = (ImageView) findViewById(R.id.img2);
	}
	
	public int choiceAction(){
		return (int) (Math.random() * 3);
	}

	public void returnResult(View v){
//		Log.d("lu", String.valueOf(choiceAction()));
		int img1a = choiceAction();
		int img2a = choiceAction();
		img1.setImageResource(actions[img1a]);
		img2.setImageResource(actions[img2a]);
		
		if((img1a - img2a == -1) || (img1a - img2a == 2)){
			Toast.makeText(this, "�ҷ�Ӯ", Toast.LENGTH_SHORT).show();
		} else if(img1a == img2a){
			Toast.makeText(this, "ƽ��", Toast.LENGTH_SHORT).show();
		} else{
			Toast.makeText(this, "�׷�Ӯ", Toast.LENGTH_SHORT).show();
		}
	}
}
