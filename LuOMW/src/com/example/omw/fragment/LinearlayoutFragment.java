package com.example.omw.fragment;

import com.example.omw.view.LinearlayoutView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LinearlayoutFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return new LinearlayoutView(getActivity());
	}
}
