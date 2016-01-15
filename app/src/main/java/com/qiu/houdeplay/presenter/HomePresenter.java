package com.qiu.houdeplay.presenter;

import android.support.v4.app.Fragment;

import com.qiu.houdeplay.base.presenter.BaseRxLcePresenter;
import com.qiu.houdeplay.model.domain.HomeCase;
import com.qiu.houdeplay.ui.views.HomeView;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/1/15.
 */
public class HomePresenter extends BaseRxLcePresenter<HomeView, List<Fragment>> {

    private HomeCase homeCase;
    private EventBus eventBus;

    public HomePresenter() {
        this.homeCase = new HomeCase();
        this.eventBus = EventBus.getDefault();
    }

    public void loadScreem(boolean pullToRefresh) {
        subscribe(homeCase.getHomeScreem(), pullToRefresh);
    }
}
