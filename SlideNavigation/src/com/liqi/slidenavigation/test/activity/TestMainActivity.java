package com.liqi.slidenavigation.test.activity;

import java.util.ArrayList;

import com.liqi.slidenavigation.R;
import com.liqi.slidenavigation.base.ExploreViewPagerFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * 测试类
 * 
 * @author Liqi
 * 
 */
public class TestMainActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.test_main_activity);
		setExploreShow(this.getSupportFragmentManager());
	}

	/**
	 * FragmentLayout界面赋值
	 */
	private void setExploreShow(FragmentManager mFragmentManager) {
		FragmentTransaction ft = mFragmentManager.beginTransaction();
		ArrayList<Class<?>> clazzList = new ArrayList<Class<?>>();
		clazzList.add(TestFragmentOne.class);
		clazzList.add(TestFragmentTwo.class);
		clazzList.add(TestFragmentThree.class);
		clazzList.add(TestFragmentFour.class);

		clazzList.add(TestFragmentOne.class);
		clazzList.add(TestFragmentTwo.class);
		clazzList.add(TestFragmentThree.class);
		clazzList.add(TestFragmentFour.class);
		ft.replace(
				R.id.main_framen,
				ExploreViewPagerFragment
						.newInstance()
						.setFragmentObjList(this, R.array.explore_title_array,
								clazzList)
						.setViewpagerIndxe(0)
						.setViewpagerCacheLimit(clazzList.size())
						.setSlidingTabStripImage(
								R.drawable.store_title_image_mr,
								R.drawable.store_title_image_xz)
						.setTextColorSelect(R.color.store_selector_slide_title))
				.commit();
	}
}
