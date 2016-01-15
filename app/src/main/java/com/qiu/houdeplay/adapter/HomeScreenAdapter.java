package com.qiu.houdeplay.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/1/15.
 */
public class HomeScreenAdapter extends FragmentStatePagerAdapter {

    private String[] tab_names;
    private List<Fragment> screens;

    public HomeScreenAdapter(FragmentManager fm, String[] tabs, List<Fragment> screems) {
        super(fm);
        this.tab_names = tabs;
        this.screens = screems;
    }


    @Override
    public Fragment getItem(int position) {
        return screens.get(position);
    }

    @Override
    public int getCount() {
        return screens.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
            return tab_names[position];
    }
}
