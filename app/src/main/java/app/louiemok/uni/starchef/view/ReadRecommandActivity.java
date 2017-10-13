package app.louiemok.uni.starchef.view;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.adapter.ReadRecommandOutSideAdapter;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;

public class ReadRecommandActivity extends AppCompatActivity {

    YsxNavigationBar ysxNavigationBar;
    ListView lv_readrecommand_inside;
    ReadRecommandOutSideAdapter readRecommandOutSideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_recommand);
        initYsxNavigationBar();
        initListView();
    }

    private void initYsxNavigationBar () {
        ysxNavigationBar = (YsxNavigationBar)findViewById(R.id.nav);
        ysxNavigationBar.setLeftSrc(R.drawable.arrow_left);
        ysxNavigationBar.setTitle("阅读中心");
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

    private void initListView () {
        lv_readrecommand_inside = (ListView)findViewById(R.id.lv_readrecommand_inside);
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for ( int i = 0 ; i < 5 ; i++ ) {
            HashMap<String ,String> map = new HashMap<>();
            map.put("title", "i"+i);
            list.add(map);
        }
        readRecommandOutSideAdapter = new ReadRecommandOutSideAdapter(this, list);
        lv_readrecommand_inside.setAdapter(readRecommandOutSideAdapter);
    }

}
