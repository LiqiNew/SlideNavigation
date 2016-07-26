package com.liqi.slidenavigation.test.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liqi.slidenavigation.R;
import com.liqi.slidenavigation.base.BaseFragment;

public class TestFragmentOne extends BaseFragment {
	private TextView fragment_text;
	private TestMainActivity activity;
	@Override
	public int setLiayoutId() {
		return R.layout.test_fragment_one;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fragment_text = (TextView) sparseArray.get(R.id.fragment_text);
		fragment_text.setText("页面001");
		activity=(TestMainActivity) getActivity();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_text:
			Toast.makeText(activity, fragment_text.getText().toString(), Toast.LENGTH_SHORT).show();
			break;
		}
	}

	@Override
	public void onDestroyNew() {

	}
}
