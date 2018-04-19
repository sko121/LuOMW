package com.example.omw;

import com.example.omw.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Activity02 extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radio_main);
		RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
		RadioButton b1 = (RadioButton) findViewById(R.id.b1);
		RadioButton b2 = (RadioButton) findViewById(R.id.b2);
		//onClick事件方法一：这种方式用findviewbyid通过id实例化出来这个button，
		//然后通过设置button的listener监听对象，并同时实现接口
		//OnClickListenter的OnClick（）方法。这种方式的代码量不多，但是在java中，
		//面向对象的思想，关于耦合，模块化它达不到。代码堆叠在一起，比较臃肿。
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "male", Toast.LENGTH_SHORT).show();
			}
		});
	}
	//onClick事件方法二：在方式三种，我们只需要在TestOnClickActivity 中增加
    //一个成员函数onClick_Event()，并在组件文件中加入对其的使用描述即可。
	//代码结构简单，清晰，代码量也大大减少，并且通过xml文件的可配置性，增长了工程的可维护性，
	//模块化进一步增强！
	public void onClickMethod2(View v){
		Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_SHORT).show();
	}
}
