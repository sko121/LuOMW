package com.example.omw;

import com.example.omw.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Activity03 extends ActionBarActivity implements OnClickListener{

	//����ʵ�ֵ���¼��ķ���
	//����button
	Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick_main);  //activity_main
        
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b2.setOnClickListener(new MyOnclickListener());
        
        // ��һ��   ʹ�������ڲ�����ʵ��
        b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//��˾
				Toast.makeText(getApplicationContext(), "button�������", Toast.LENGTH_SHORT).show();
			}
		});
    }
    
    //�ڶ��֣���Ա�ڲ���
    class MyOnclickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.b1:
				
				break;

			case R.id.b2:
				Toast.makeText(getApplicationContext(), "�ұ����ˣ�", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
			Toast.makeText(getApplicationContext(), "�ұ����ˣ�", Toast.LENGTH_SHORT).show();
		}
    	
    }

 // �����ּ�����ʽ��  ����ȥʵ��OnClickLinstener�ӿ�
 		// 1��mainacitivty ʵ�� �ӿ�
 		// ��onclick������ʵ��
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.b1:
			Toast.makeText(getApplicationContext(), "button�������", Toast.LENGTH_SHORT).show();
			break;
		case R.id.b2:
			Toast.makeText(getApplicationContext(), "button�������", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
	
	// �����ַ�ʽ����xml�����button�������onclick����   ����ֵ  
	public void sendMessage(View v){
		Toast.makeText(getApplicationContext(), "aaaa", Toast.LENGTH_LONG).show();
	}

}
