package com.liqi.slidenavigation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.TextView;


import com.liqi.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * ViewPager页面切换赋值适配器
 *
 * @author LiQi
 */
public class ViewPageFragmentAdapter extends FragmentPagerAdapter implements
        ViewPager.OnPageChangeListener {

    private final ArrayList<ViewPageInfo> MTABS = new ArrayList<ViewPageInfo>();
    // 设置title颜色选择器ID
    public int mTextColroSelect;
    public int mTextSize;
    protected PagerSlidingTabStrip mPagerStrip;
    private Context mContext;
    private ViewPager mViewPager;
    private OnViewPageAdapterPageSelectedListener mPageSelectedListener;

    public ViewPageFragmentAdapter(FragmentManager fm,
                                   PagerSlidingTabStrip pageStrip, ViewPager pager) {
        super(fm);
        mContext = pager.getContext();
        mPagerStrip = pageStrip;
        mViewPager = pager;
        mViewPager.setAdapter(this);
        mPagerStrip.setViewPager(mViewPager);
        mPagerStrip.setOnPageChangeListener(this);
    }

    public void addTab(String title, @NonNull BaseFragment baseFragment) {
        ViewPageInfo info = new ViewPageInfo(title, baseFragment);
        MTABS.add(info);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        for (ViewPageInfo viewPageInfo : MTABS) {
            TextView v = (TextView) LayoutInflater.from(mContext).inflate(
                    R.layout.sliding_tab_item, null);
            v.setText(viewPageInfo.mTitle);
            v.setTextSize(mTextSize);
            v.setTextColor(mContext.getResources().getColorStateList(mTextColroSelect));
            // 需要在此处动态添加标题图片
            mPagerStrip.addTab(v);
        }
    }

    public void clear() {
        if (!MTABS.isEmpty()) {
            MTABS.clear();
        }
        mContext = null;
        mPagerStrip = null;
        mViewPager = null;
    }

    @Override
    public int getCount() {
        return MTABS.size();
    }

    @Override
    public BaseFragment getItem(int position) {
        ViewPageInfo info = MTABS.get(position);
        info.mBaseFragment.setData(position);
        return info.mBaseFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return MTABS.get(position).mTitle;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //Log.e("ViewPaer切换", "ViewPaer切换001"+state);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        //Log.e("ViewPaer切换", "ViewPaer切换002"+position);
    }

    @Override
    public void onPageSelected(int position) {
        ViewPageInfo viewPageInfo = MTABS.get(position);
        viewPageInfo.mBaseFragment.onShow();
        if (null != mPageSelectedListener) {
            mPageSelectedListener.onPageSelected(viewPageInfo);
        }
        //Log.e("ViewPaer切换", "ViewPaer切换003"+position);
    }

    public ViewPageInfo getInitListIndexData() {
        if (!MTABS.isEmpty()) {
            return MTABS.get(0);
        }
        return null;
    }

    public void setPageSelectedListener(OnViewPageAdapterPageSelectedListener pageSelectedListener) {
        mPageSelectedListener = pageSelectedListener;
    }

    public interface OnViewPageAdapterPageSelectedListener {
        void onPageSelected(ViewPageInfo viewPageInfo);
    }
}