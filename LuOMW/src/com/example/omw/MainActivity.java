package com.example.omw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

import com.example.omw.R;

public class MainActivity extends ActionBarActivity {
	// 1 ����ExpandableListView
	private ExpandableListView listView;
	// 3 ����group�ļ���
	private List<String> group = new ArrayList<String>();
	// 4 ����children �ļ���
	private List<List<String>> children = new ArrayList<List<String>>();
	
	private LayoutInflater inflater;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		inflater = LayoutInflater.from(this);
		// 2 �ҵ�ExpandableListView ����
		listView = (ExpandableListView) findViewById(R.id.ExpandableListView);
		// 5 �������:group ����
		String[] groupStr = getResources().getStringArray(R.array.List);
		group = Arrays.asList(groupStr);
		// 6 �������:child ����
		for(int i = 0; i<group.size(); i++){
			int arrId = getResources().getIdentifier(
					group.get(i), "array", getPackageName());
			children.add(Arrays.asList(getResources().getStringArray(arrId)));
		}
		
		// 7 ����Adapter ����Adapter
		listView.setAdapter(new MyExpandableAdapter());
		// 8 ��Ӽ���:children
		listView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				String className = getPackageName() + ".Activity" 
					+ groupPosition + childPosition;
				try {
					Class clazz = Class.forName(className);
					Intent intent = new Intent(MainActivity.this, clazz);
					MainActivity.this.startActivity(intent);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				return false;
			}
		});
	}
	
	class MyExpandableAdapter extends BaseExpandableListAdapter{
		
		@Override
		public int getGroupCount() {
			return group.size();
		}
		
		@Override
		public int getChildrenCount(int groupPosition) {
			return children.get(groupPosition).size();
		}
		
		@Override
		public Object getGroup(int groupPosition) {
			return group.get(groupPosition);
		}
		
		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return children.get(groupPosition).get(childPosition);
		}
		
		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}
		
		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}
		
		@Override
		public boolean hasStableIds() {
			return true;
		}
		
		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			if(convertView == null){
				convertView = inflater.inflate(R.layout.view_nav_group,
						parent, false);
			}
			TextView tv = (TextView) convertView.findViewById(R.id.group_tv);
			tv.setText(group.get(groupPosition));
			return convertView;
		}
		
		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			if(convertView == null){
				convertView = inflater.inflate(R.layout.view_nav_child, 
						parent, false);
			}
			TextView tv = (TextView) convertView.findViewById(R.id.child_tv);
			tv.setText(children.get(groupPosition).get(childPosition));
			return convertView;
		}
		
		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
		
	}
}
