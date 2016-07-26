package com.liqi.slidenavigation.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Fragment基类
 * 
 * 自动给设置Clickable属性控件设置点击事件
 * @author Liqi
 * 
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {
	private View view;
	protected SparseArray<Object> sparseArray;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (null == view) {
			view = inflater.inflate(setLiayoutId(), container, false);

			if (null == sparseArray)
				sparseArray = new SparseArray<Object>();

			pollFindWidget(this.view);
		}
		return view;
	}

	/**
	 * 轮询找到View布局里面的控件
	 * 
	 * @param view
	 */
	private void pollFindWidget(View view) {
		if (!(view instanceof ViewGroup)) {
			judgeWdgetId(view, view.getId());
		} else {
			ViewGroup viewGroup = (ViewGroup) view;
			int childCount = viewGroup.getChildCount();
			if (childCount > 0) {
				for (int j = 0; j < childCount; j++) {
					View childAt = viewGroup.getChildAt(j);
					int id = childAt.getId();
					// 布局轮询获取控件
					if (childAt instanceof ViewGroup) {
						judgeWdgetId(childAt, id);
						pollFindWidget(childAt);
					} else
						judgeWdgetId(childAt, id);
				}
			} else
				judgeWdgetId(view, view.getId());
		}
	}

	/**
	 * 判断控件是否赋值ID，赋值ID就存储进SparseArray集合里面并设置点击事件
	 * 
	 * @param childAt
	 *            控件
	 * @param id
	 *            控件ID
	 */
	private void judgeWdgetId(View childAt, int id) {
		if (id > 0) {
			// 那些控件不能设置点击事件，把它排除掉
			if (!(childAt instanceof ListView)
					&& !(childAt instanceof GridView)
					&& !(childAt instanceof ViewPager)
					&& !(childAt instanceof HorizontalScrollView)
					&& !(childAt instanceof ScrollView)
					&& !(childAt instanceof WebView))
				if (childAt.isClickable())
					childAt.setOnClickListener(this);

			sparseArray.put(id, childAt);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (null != view) {
			ViewGroup group = (ViewGroup) view.getParent();
			if (null != group) {
				group.removeView(view);
			}
		}
		if (null != sparseArray && sparseArray.size() > 0)
			sparseArray.clear();
		onDestroyNew();
	}

	/**
	 * fragment销毁方法回调
	 */
	public abstract void onDestroyNew();

	/**
	 * 赋值fragment布局ID
	 * 
	 * @return
	 */
	public abstract int setLiayoutId();

	/**
	 * fragment页面显示调用的方法
	 */
	public void onShow() {

	};

	/**
	 * 此页面控件赋值的点击事件，如果需要点击事件，就重写此方法
	 */
	@Override
	public void onClick(View v) {

	}
}
