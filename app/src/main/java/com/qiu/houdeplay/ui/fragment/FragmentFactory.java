package com.qiu.houdeplay.ui.fragment;


import com.qiu.houdeplay.base.BaseFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/22.
 */
public class FragmentFactory {
    private static Map<Integer, BaseFragment> mFragments = new HashMap<Integer, BaseFragment>();

    public static BaseFragment createFragment(int position) {
        BaseFragment BaseFragment = null;
        BaseFragment = mFragments.get(position);  //在集合中取出来Fragment
        if (BaseFragment == null) {  //如果再集合中没有取出来 需要重新创建
            if (position == 0) {
                BaseFragment = new HomeFragment();
            } else if (position == 1) {
                BaseFragment = new AppFragment();
            } else if (position == 2) {
                BaseFragment = new GameFragment();
            } else if (position == 3) {
                BaseFragment = new SubjectFragment();
            } else if (position == 4) {
                BaseFragment = new CategoryFragment();
            } else if (position == 5) {
                BaseFragment = new TopFragment();
            }
            if (BaseFragment != null) {
                mFragments.put(position, BaseFragment);// 把创建好的Fragment存放到集合中缓存起来
            }
        }
        return BaseFragment;

    }
}
