package app.louiemok.uni.starchef.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;

import app.louiemok.uni.starchef.BaseActivity;
import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.adapter.TodayMainAdapter;
import app.louiemok.uni.starchef.adapter.TodayNewAdapter;
import app.louiemok.uni.starchef.seledefine.GridViewInScrollView;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;

public class DailyFoodActivity extends BaseActivity {

    YsxNavigationBar ysxNavigationBar;
    GridViewInScrollView gv_todaysoup;
    TodayNewAdapter todayNewAdapter;
    RecyclerView rv_today_main;
    TodayMainAdapter todayMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_food);
        initYsxNavigationBar();
        initTodaySoup();
    }

    private void initYsxNavigationBar () {
        ysxNavigationBar = (YsxNavigationBar)findViewById(R.id.nav);
        ysxNavigationBar.setLeftSrc(R.drawable.arrow_left);
        ysxNavigationBar.setTitle("每日美食");
        ysxNavigationBar.hideRightSrc();
        ysxNavigationBar.hideRightText();
        ysxNavigationBar.setClickCallback(new YsxNavigationBar.ClickCallback() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightBtnClick() {

            }

            @Override
            public void onRightTextClick() {

            }
        });
    }

    private void initTodaySoup () {
        gv_todaysoup = (GridViewInScrollView)findViewById(R.id.gv_todaysoup);
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for ( int i = 0 ; i < 2 ; i++ ) {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", "i"+i);
            list.add(map);
        }
        todayNewAdapter = new TodayNewAdapter(this, list);
        gv_todaysoup.setAdapter(todayNewAdapter);
    }

    private void initGridView () {
        rv_today_main = (RecyclerView)findViewById(R.id.rv_today_main);

        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for ( int i = 0 ; i < 7 ; i++ ) {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", "i"+i);
            list.add(map);
        }
        todayMainAdapter = new TodayMainAdapter(this, list);

        rv_today_main.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rv_today_main.setAdapter(todayMainAdapter);
    }

}
