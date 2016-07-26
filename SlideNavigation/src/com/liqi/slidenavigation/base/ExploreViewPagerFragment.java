package com.liqi.slidenavigation.base;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;

/**
 * 给ViewPeager每个页面赋值
 * 
 * @author Liqi
 * 
 */
public class ExploreViewPagerFragment extends BaseViewPagerFragment {
	public final byte TYPE_ONE = 0x0;
	public final byte TYPE_TWO = 0x1;
	public final byte TYPE_THREE = 0x2;
	// 存储fragment对象的集合
	private ArrayList<ThisViewpagerFragmentObj> fragmentObjList;

	public static ExploreViewPagerFragment newInstance() {
		return new ExploreViewPagerFragment();
	}

	private ExploreViewPagerFragment() {
		if (null == fragmentObjList)
			fragmentObjList = new ArrayList<ThisViewpagerFragmentObj>();

	}

	/**
	 * 设置当前那个页面显示
	 * 
	 * @param index
	 *            页面索引值
	 * @return
	 */
	public ExploreViewPagerFragment setViewpagerIndxe(int index) {
		this.index = index;
		return this;
	}

	/**
	 * 设置viewpager一次性加载几个对象
	 * 
	 * @param cacheLimit
	 * @return
	 */
	public ExploreViewPagerFragment setViewpagerCacheLimit(int cacheLimit) {
		this.cacheLimit = cacheLimit;
		return this;
	}

	/**
	 * 设置滑块默认图片和滑动时的图片
	 * 
	 * @param backgroundId
	 *            默认图片
	 * @param slidingBlock
	 *            滑动图片
	 * @return
	 */
	public ExploreViewPagerFragment setSlidingTabStripImage(int backgroundId,
			int slidingBlock) {
		this.backgroundId = backgroundId;
		this.slidingBlock = slidingBlock;
		return this;
	}

	/**
	 * 设置滑块字体颜色选择器
	 * 
	 * @param colorSelect
	 * @return
	 */
	public ExploreViewPagerFragment setTextColorSelect(int colorSelect) {
		this.textColroSelect = colorSelect;
		return this;
	}

	/**
	 * 设置标题底部是否需要跟着手势滑动效果
	 * 
	 * @param slidingTag
	 * @return
	 */
	public ExploreViewPagerFragment setSlidingTag(boolean slidingTag) {
		this.slidingTag = slidingTag;
		return this;
	}

	/**
	 * 设置是否title字体选中后是否需要变大效果
	 * 
	 * @param slidingTag
	 * @return
	 */
	public ExploreViewPagerFragment setTextSizeTag(boolean textSizeTag) {
		this.textSizeTag = textSizeTag;
		return this;
	}

	/**
	 * 此对象实例化之后就初始化ViewPager片段页面
	 */
	@Override
	protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
		if (!fragmentObjList.isEmpty()) {
			for (int i = 0; i < fragmentObjList.size(); i++) {
				ThisViewpagerFragmentObj fragmentObj = fragmentObjList.get(i);
				Bundle bundle = new Bundle();
				bundle.putInt(i + "", i);
				adapter.addTab(fragmentObj.titleName,
						fragmentObj.clazz.getName(), fragmentObj.clazz, bundle);
			}
		} else
			System.out
					.println("ExploreViewPagerFragment对象中fragmentObjList展示集合没有赋值对象,请调用setFragmentObjList()赋值");
	}

	/**
	 * 根据需要的数据设置要展示fragment存储对象集合
	 * 
	 * @param context
	 * @param arrayId
	 *            arrayxml文件标题名字数组ID
	 * @param clazzList
	 *            fragment.class对象集合
	 * @return
	 */
	public ExploreViewPagerFragment setFragmentObjList(Context context,
			int arrayId, ArrayList<Class<?>> clazzList) {
		if (null != clazzList && !clazzList.isEmpty()) {

			String[] title = context.getResources().getStringArray(arrayId);
			for (int i = 0; i < clazzList.size(); i++) {
				// 限制的fragment对象不能大于标题名字的数组
				if (i < title.length) {
					ThisViewpagerFragmentObj fragmentObj = new ThisViewpagerFragmentObj();
					fragmentObj.clazz = clazzList.get(i);
					fragmentObj.titleName = title[i];
					fragmentObjList.add(fragmentObj);
				}
			}
		}
		return this;
	}

	/**
	 * fragment存储定义对象
	 * 
	 * @author Liqi
	 * 
	 */
	class ThisViewpagerFragmentObj {
		String titleName;
		Class<?> clazz;
	}
}
