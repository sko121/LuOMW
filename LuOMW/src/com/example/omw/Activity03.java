package com.example.omw;

import com.example.omw.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Activity03 extends ActionBarActivity implements OnClickListener{

	//四种实现点击事件的方法
	//声明button
	Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick_main);  //activity_main
        
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b2.setOnClickListener(new MyOnclickListener());
        
        // 第一种   使用匿名内部类来实现
        b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//吐司
				Toast.makeText(getApplicationContext(), "button被点击了", Toast.LENGTH_SHORT).show();
			}
		});
    }
    
    //第二种，成员内部类
    class MyOnclickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.b1:
				
				break;

			case R.id.b2:
				Toast.makeText(getApplicationContext(), "我被点了！", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
			Toast.makeText(getApplicationContext(), "我被点了！", Toast.LENGTH_SHORT).show();
		}
    	
    }

 // 第三种监听方式：  本类去实现OnClickLinstener接口
 		// 1、mainacitivty 实现 接口
 		// 在onclick方法内实现
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.b1:
			Toast.makeText(getApplicationContext(), "button被点击了", Toast.LENGTH_SHORT).show();
			break;
		case R.id.b2:
			Toast.makeText(getApplicationContext(), "button被点击了", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
	
	// 第四种方式；在xml里面的button里面添加onclick属性   属性值  
	public void sendMessage(View v){
		Toast.makeText(getApplicationContext(), "aaaa", Toast.LENGTH_LONG).show();
	}

}
