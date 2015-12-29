package com.qiu.houdeplay;

import android.os.Build;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.qiu.houdeplay.fragment.FragmentFactory;
import com.qiu.houdeplay.tools.UiUtils;

public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ViewPager mViewPager;
    private PagerTabStrip pager_tab_strip;
    private String[] tab_names;  // 标签的名字


    @Override
    protected void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        /*
         *	1）显示Navigation Drawer的 Activity 对象
			2） DrawerLayout 对象
			3）一个用来指示Navigation Drawer的 drawable资源
			4）一个用来描述打开Navigation Drawer的文本 (用于支持可访问性)。
			5）一个用来描述关闭Navigation Drawer的文本(用于支持可访问性).
		 */
        drawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, R.mipmap.ic_drawer_am, R.string.open_drawer,
                R.string.close_drawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                Toast.makeText(getApplicationContext(), "抽屉关闭了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                Toast.makeText(getApplicationContext(), "抽屉打开了", Toast.LENGTH_SHORT).show();
            }

        };
        mDrawerLayout.setDrawerListener(drawerToggle);
        //  让开关和actionbar建立关系
        drawerToggle.syncState();
    }

    @Override
    protected void init() {
        tab_names = UiUtils.getStringArray(R.array.tab_names);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        pager_tab_strip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
    }

    @Override
    protected void initView() {
        //  设置标签下划线的颜色
        pager_tab_strip.setTabIndicatorColor(getResources().getColor(R.color.indicatorcolor));
        mViewPager.setAdapter(new MainAdpater(getSupportFragmentManager()));
        //sendBroadcast(new Intent("com.itheima.google.killall"));
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                FragmentFactory.createFragment(position).show();
            }
        });
    }


    private class MainAdpater extends FragmentStatePagerAdapter {
        public MainAdpater(FragmentManager fm) {
            super(fm);
        }

        // 每个条目返回的fragment
        //  0
        @Override
        public Fragment getItem(int position) {
            //  通过Fragment工厂  生产Fragment
            return FragmentFactory.createFragment(position);
        }

        // 一共有几个条目
        @Override
        public int getCount() {
            return tab_names.length;
        }

        // 返回每个条目的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return tab_names[position];
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        if (Build.VERSION.SDK_INT > 11) {
            SearchView searchView =
                    (SearchView) menu.findItem(R.id.action_search).getActionView();
            searchView.setOnQueryTextListener(this);//设置监听

        }
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "搜索点击了", Toast.LENGTH_SHORT);
        }
        return drawerToggle.onOptionsItemSelected(item) | super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d(TAG, "onQueryTextSubmit " + query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d(TAG, "onQueryTextChange " + newText);
        return true;
    }
}
