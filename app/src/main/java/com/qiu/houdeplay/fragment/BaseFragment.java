package com.qiu.houdeplay.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.qiu.houdeplay.R;
import com.qiu.houdeplay.tools.ViewUtils;

/**
 * Created by Administrator on 2015/12/29.
 */
public abstract class BaseFragment extends Fragment {
    public static final int STATE_UNKOWN = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_SUCCESS = 4;
    public int state = STATE_UNKOWN;

    private View loadingView;// 加载中的界面
    private View errorView;// 错误界面
    private View emptyView;// 空界面
    private View successView;// 加载成功的界面
    private FrameLayout frameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (frameLayout == null) {  // 之前的frameLayout 已经记录了一个爹了  爹是之前的ViewPager
            frameLayout = new FrameLayout(getActivity());
            init(); // 在FrameLayout中 添加4种不同的界面
        } else {
            ViewUtils.removeParent(frameLayout);// 移除frameLayout之前的爹
        }
//        show();// 根据服务器的数据 切换状态
        //  先干掉之前的爹
        return frameLayout;  //  拿到当前viewPager 添加这个framelayout
    }

    // 在FrameLayout中 添加几种不同的界面
    private void init() {
        loadingView = createLoadingView(); // 创建了加载中的界面
        if (loadingView != null) {
            frameLayout.addView(loadingView, new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }
        errorView = createErrorView(); // 加载错误界面
        if (errorView != null) {
            frameLayout.addView(errorView, new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }
        emptyView = createEmptyView(); // 加载空的界面
        if (emptyView != null) {
            frameLayout.addView(emptyView, new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }
        showPage();// 根据不同的状态显示不同的界面
    }

    // 根据不同的状态显示不同的界面
    private void showPage() {
        if (loadingView != null) {
            loadingView.setVisibility(state == STATE_UNKOWN
                    || state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if (errorView != null) {
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE
                    : View.INVISIBLE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE
                    : View.INVISIBLE);
        }
        if (state == STATE_SUCCESS) {
            successView = createSuccessView();
            if (successView != null) {
                frameLayout.addView(successView, new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                successView.setVisibility(View.VISIBLE);
            }
        } else {
            if (successView != null) {
                successView.setVisibility(View.INVISIBLE);
            }
        }
    }

    public enum LoadResult {
        error(2), empty(3), success(4);

        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    protected abstract View createSuccessView();

    /* 创建了空的界面 */
    private View createEmptyView() {
        View view = View.inflate(getActivity(), R.layout.loadpage_empty, null);
        return view;
    }

    /* 创建了错误界面 */
    private View createErrorView() {
        View view = View.inflate(getActivity(), R.layout.loadpage_error, null);
        Button page_bt = (Button) view.findViewById(R.id.page_bt);
        page_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                show();
            }
        });
        return view;
    }

    /* 创建加载中的界面 */
    private View createLoadingView() {
        View view = View
                .inflate(getActivity(), R.layout.loadpage_loading, null);
        return view;
    }

    // 根据服务器的数据 切换状态
    public void show() {
        if (state == STATE_ERROR || state == STATE_EMPTY) {
            state = STATE_LOADING;
        }
        // 请求服务器 获取服务器上数据 进行判断
        // 请求服务器 返回一个结果
        new Thread() {
            public void run() {
                SystemClock.sleep(2000);
                final LoadResult result = load();
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (result != null) {
                                state = result.getValue();
                                showPage(); // 状态改变了,重新判断当前应该显示哪个界面
                            }
                        }
                    });
                }
            }
        }.start();
        showPage();
    }

    protected abstract LoadResult load();
}
