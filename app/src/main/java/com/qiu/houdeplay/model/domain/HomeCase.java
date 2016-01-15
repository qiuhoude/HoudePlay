package com.qiu.houdeplay.model.domain;

import android.support.v4.app.Fragment;

import com.qiu.houdeplay.ui.fragment.AppFragment;
import com.qiu.houdeplay.ui.fragment.CategoryFragment;
import com.qiu.houdeplay.ui.fragment.GameFragment;
import com.qiu.houdeplay.ui.fragment.HomeFragment;
import com.qiu.houdeplay.ui.fragment.SubjectFragment;
import com.qiu.houdeplay.ui.fragment.TopFragment;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/1/15.
 */
public class HomeCase {

    public Observable<List<Fragment>> getHomeScreem() {
        return Observable.defer(() -> {
            List<Fragment> screems = new ArrayList<Fragment>();
            screems.add(new AppFragment());
            screems.add(new CategoryFragment());
            screems.add(new GameFragment());
            screems.add(new HomeFragment());
            screems.add(new SubjectFragment());
            screems.add(new TopFragment());
            return Observable.just(screems);
        });

    }

}
