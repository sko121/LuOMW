package com.example.omw;

import com.example.omw.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Activity30 extends ActionBarActivity {

	private ListView lv;
	// 2 ׼������Դ
	public String[] musics = { "��«��", "�˲���", "����Ь", "ǧ���һ��", "����������", "�𹿰�",
			"����", "������Ļ�", "ˮ��", "����", "��İ���", "����", "�ؼҵ�·", "����һ����" };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv);
		lv = (ListView) findViewById(R.id.lv);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity30.this, 
				android.R.layout.simple_dropdown_item_1line, musics);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String music = musics[position];
				Toast.makeText(Activity30.this, music, Toast.LENGTH_SHORT).show();
			}
		});
		
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(Activity30.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
				return true;
			}
		});
	}
}
