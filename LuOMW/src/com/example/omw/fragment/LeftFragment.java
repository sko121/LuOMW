package com.example.omw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.omw.Activity92;
import com.example.omw.R;

public class LeftFragment extends Fragment {
	private ListView lv;

	public String[] musics = { "��«��", "�˲���", "����Ь", "ǧ���һ��", "����������", "�𹿰�",
			"����", "������Ļ�", "ˮ��", "����", "��İ���", "����", "�ؼҵ�·", "����һ����" };

	@Override
	public View onCreateView(LayoutInflater inflater,
			 @Nullable ViewGroup container,  Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_lv, container, false);
		lv = (ListView) view.findViewById(R.id.lv);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_dropdown_item_1line, musics);
		// 4 ListView ����������
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				Toast.makeText(getActivity(), "���,��ǰ��ף����", 0).show();
				Activity92 activity92 = (Activity92) getActivity();
				activity92.setFragmentContent(musics[position]);
			}
		});
		return view;
	}
}
