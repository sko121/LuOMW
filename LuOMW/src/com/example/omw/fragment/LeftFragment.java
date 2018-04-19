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

	public String[] musics = { "葫芦娃", "伤不起", "滑板鞋", "千年等一回", "法海不懂爱", "金箍棒",
			"征服", "听妈妈的话", "水手", "朋友", "真的爱你", "红日", "回家的路", "爱你一万年" };

	@Override
	public View onCreateView(LayoutInflater inflater,
			 @Nullable ViewGroup container,  Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_lv, container, false);
		lv = (ListView) view.findViewById(R.id.lv);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_dropdown_item_1line, musics);
		// 4 ListView 设置适配器
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				Toast.makeText(getActivity(), "你好,提前庆祝中秋", 0).show();
				Activity92 activity92 = (Activity92) getActivity();
				activity92.setFragmentContent(musics[position]);
			}
		});
		return view;
	}
}
