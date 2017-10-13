package app.louiemok.uni.starchef.view;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import app.louiemok.uni.starchef.BaseActivity;
import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.adapter.DynamicInsideAdapter;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;

public class DynamicActivity extends BaseActivity {

    YsxNavigationBar ysxNavigationBar;
    ListView lv_dynamic;
    DynamicInsideAdapter dynamicInsideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        initYsxNavigationBar();
        initListView();
    }

    @Override
    public void onResume () {
        super.onResume();
    }

    private void initYsxNavigationBar () {
        ysxNavigationBar = (YsxNavigationBar)findViewById(R.id.nav);
        ysxNavigationBar.setLeftSrc(R.drawable.arrow_left);
        ysxNavigationBar.setTitle("精彩动态");
        ysxNavigationBar.hideRightSrc();
        ysxNavigationBar.setRightText("发布");
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
        lv_dynamic = (ListView)findViewById(R.id.lv_dynamic);
        dynamicInsideAdapter = new DynamicInsideAdapter(this, new ArrayList<HashMap<String,
                String>>());
        lv_dynamic.setAdapter(dynamicInsideAdapter);
    }

}
