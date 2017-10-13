package app.louiemok.uni.starchef.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import app.louiemok.uni.starchef.BaseActivity;
import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.adapter.SkillRecommandInSideAdapter;
import app.louiemok.uni.starchef.adapter.TodayNewAdapter;
import app.louiemok.uni.starchef.seledefine.GridViewInScrollView;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;

public class SkillRecommandActivity extends BaseActivity {

    YsxNavigationBar ysxNavigationBar;
    ListView lv_skillrecommand_inside;
    SkillRecommandInSideAdapter skillRecommandInSideAdapter;
    GridViewInScrollView gv_todaynew;
    TodayNewAdapter todayNewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_recommand);
        initYsxNavigationBar();
        initListView();
        initGridView();
    }

    private void initYsxNavigationBar () {
        ysxNavigationBar = (YsxNavigationBar)findViewById(R.id.nav);
        ysxNavigationBar.setLeftSrc(R.drawable.arrow_left);
        ysxNavigationBar.setTitle("技巧专区");
        ysxNavigationBar.hideRightText();
        ysxNavigationBar.hideRightSrc();
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

    private void initListView () {
        lv_skillrecommand_inside = (ListView)findViewById(R.id.lv_skillrecommand_inside);
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for ( int i = 0 ; i < 5 ; i++ ) {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", "i"+i);
            list.add(map);
        }
        skillRecommandInSideAdapter = new SkillRecommandInSideAdapter(this, list);
        lv_skillrecommand_inside.setAdapter(skillRecommandInSideAdapter);
        setListViewHeightBasedOnChild(lv_skillrecommand_inside, false);
    }

    private void initGridView () {
        gv_todaynew = (GridViewInScrollView)findViewById(R.id.gv_todaynew);
        todayNewAdapter = new TodayNewAdapter(this, new ArrayList<HashMap<String, String>>());
        gv_todaynew.setAdapter(todayNewAdapter);
    }

    private void setListViewHeightBasedOnChild (ListView ls, boolean isbottom) {
        ListAdapter listAdapter = ls.getAdapter();
        if ( listAdapter == null ) {
            return;
        }

        int totalHeight = 0;
        for ( int i = 0 ; i < listAdapter.getCount() ; i++ ) {
            View listItem = listAdapter.getView(i, null, ls);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = ls.getLayoutParams();
        if ( isbottom ) {
            params.height = totalHeight+dip2px(this, 56);
        }
        else {
            params.height = totalHeight + (ls.getDividerHeight() * (listAdapter.getCount() - 1));
        }
        ls.setLayoutParams(params);
    }

}
