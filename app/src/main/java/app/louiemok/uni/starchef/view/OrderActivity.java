package app.louiemok.uni.starchef.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import app.louiemok.uni.starchef.BaseActivity;
import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.adapter.OrderAdapter;
import app.louiemok.uni.starchef.seledefine.RecyclerViewWithEmptyView;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;
import app.louiemok.uni.starchef.seledefine.YsxRecyclerViewDivider;

import android.R.attr.*;
import android.widget.LinearLayout;

public class OrderActivity extends BaseActivity {

    YsxNavigationBar ysxNavigationBar;
    RecyclerViewWithEmptyView rv_order;
    OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }

    @Override
    public void onResume () {
        super.onResume();
        initYsxNavigationBar();
        initElements();
    }

    public void initYsxNavigationBar () {
        ysxNavigationBar = (YsxNavigationBar)findViewById(R.id.nav);
        ysxNavigationBar.setTitle("我的订单");
        ysxNavigationBar.hideRightText();
        ysxNavigationBar.showRightSrc(R.drawable.magnifer);
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

    public void initElements () {
        ArrayList<HashMap<String, String>> ls = new ArrayList<>();
        for ( int i = 0 ; i < 3 ; i++ ) {
            HashMap<String, String> map = new HashMap<>();
            map.put("test", String.valueOf(i));
            ls.add(map);
        }
        rv_order = (RecyclerViewWithEmptyView)findViewById(R.id.rv_order);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_order.setLayoutManager(layoutManager);
        rv_order.addItemDecoration(new YsxRecyclerViewDivider(this,
                LinearLayout.VERTICAL, dip2px(this, 15), ContextCompat.getColor(this, R.color.colorGray)));
        orderAdapter = new OrderAdapter(this, ls);
        View view = findViewById(R.id.order_empty_view);
        rv_order.setEmptyView(view);
        rv_order.setAdapter(orderAdapter);
    }

}
