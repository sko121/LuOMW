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
		//onClick�¼�����һ�����ַ�ʽ��findviewbyidͨ��idʵ�����������button��
		//Ȼ��ͨ������button��listener�������󣬲�ͬʱʵ�ֽӿ�
		//OnClickListenter��OnClick�������������ַ�ʽ�Ĵ��������࣬������java�У�
		//��������˼�룬������ϣ�ģ�黯���ﲻ��������ѵ���һ�𣬱Ƚ�ӷ�ס�
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "male", Toast.LENGTH_SHORT).show();
			}
		});
	}
	//onClick�¼����������ڷ�ʽ���֣�����ֻ��Ҫ��TestOnClickActivity ������
    //һ����Ա����onClick_Event()����������ļ��м�������ʹ���������ɡ�
	//����ṹ�򵥣�������������Ҳ�����٣�����ͨ��xml�ļ��Ŀ������ԣ������˹��̵Ŀ�ά���ԣ�
	//ģ�黯��һ����ǿ��
	public void onClickMethod2(View v){
		Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_SHORT).show();
	}
}
