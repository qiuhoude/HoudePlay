package com.qiu.houdeplay.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.qiu.houdeplay.R;
import com.qiu.houdeplay.adapter.HomeScreenAdapter;
import com.qiu.houdeplay.base.view.BaseLceActivity;
import com.qiu.houdeplay.presenter.HomePresenter;
import com.qiu.houdeplay.ui.views.HomeView;
import com.qiu.houdeplay.utils.UiUtils;

import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseLceActivity<FrameLayout, List<Fragment>, HomeView, HomePresenter>
        implements HomeView, SearchView.OnQueryTextListener {
    @Bind(R.id.pager_tab_strip)
    PagerTabStrip pagerTabStrip;
    @Bind(R.id.vp)
    ViewPager mViewPager;
    @Bind(R.id.dl)
    DrawerLayout mDrawerLayout;
//    @Bind(R.id.toolbar)
    Toolbar toolbar;


    private HomeScreenAdapter adapter;
    private String[] tabsName;
    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabsName = getResources().getStringArray(R.array.tab_names);
        initToolBar();
        initView();
        loadData(false);
    }


    protected void initToolBar() {
        toolbar.setTitle(UiUtils.getResource().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(toggle);
        //让开关和actionbar建立关系
        toggle.syncState();

    }

    protected void initView() {
        //  设置标签下划线的颜色
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.indicatorcolor));
    }


    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return "xxx";
    }

    @NonNull
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void setData(List<Fragment> data) {
        adapter = new HomeScreenAdapter(getSupportFragmentManager(), tabsName, data);
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().loadScreem(pullToRefresh);
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
//        Log.d(TAG, "onQueryTextSubmit " + query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
//        Log.d(TAG, "onQueryTextChange " + newText);
        return true;
    }
}
