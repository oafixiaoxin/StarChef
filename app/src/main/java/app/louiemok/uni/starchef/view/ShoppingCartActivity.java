package app.louiemok.uni.starchef.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.adapter.ShoppingCartAdapter;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;

public class ShoppingCartActivity extends AppCompatActivity {

    YsxNavigationBar ysxNavigationBar;
    ListView lv_shoppingcart;
    ShoppingCartAdapter shoppingCartAdapter;
    LinearLayout ll_bottom;
    LinearLayout ll_bottom_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
    }

    @Override
    public void onResume () {
        super.onResume();
        initYsxNavigationBar();
        initElements();
    }

    private void initYsxNavigationBar () {
        ysxNavigationBar = (YsxNavigationBar)findViewById(R.id.nav);
        ysxNavigationBar.setTitle("购物车");
        ysxNavigationBar.hideRightSrc();
        ysxNavigationBar.showRightText("编辑");
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
                if ( ysxNavigationBar.getRightText().equals("编辑") ) {
                    ysxNavigationBar.setRightText("完成");
                    ll_bottom.setVisibility(View.GONE);
                    ll_bottom_1.setVisibility(View.VISIBLE);
                }
                else if ( ysxNavigationBar.getRightText().equals("完成") ) {
                    ysxNavigationBar.setRightText("编辑");
                    ll_bottom_1.setVisibility(View.GONE);
                    ll_bottom.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initElements () {
        lv_shoppingcart = (ListView)findViewById(R.id.lv_shoppingcart);
        ArrayList<HashMap<String, String>> ls = new ArrayList<>();
        shoppingCartAdapter = new ShoppingCartAdapter(this, ls);
        lv_shoppingcart.setAdapter(shoppingCartAdapter);

        ll_bottom = (LinearLayout)findViewById(R.id.ll_bottom);
        ll_bottom_1 = (LinearLayout)findViewById(R.id.ll_bottom_1);
    }

}
