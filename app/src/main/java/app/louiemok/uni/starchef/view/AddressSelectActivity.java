package app.louiemok.uni.starchef.view;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.adapter.ProCityAreaAdapter;
import app.louiemok.uni.starchef.model.ProCityArea;
import app.louiemok.uni.starchef.presenter.GetProCityAreaPresenter;
import app.louiemok.uni.starchef.presenter.GetProCityAreaPresenterImpl;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;
import app.louiemok.uni.starchef.seledefine.YsxRecyclerViewDivider;

public class AddressSelectActivity extends Activity implements AddressSelectView {

    YsxNavigationBar ysxNavigationBar;
    RecyclerView rv_pro_city_area;
    ProCityAreaAdapter proCityAreaAdapter;

    GetProCityAreaPresenter getProCityAreaPresenter;

    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_select);
    }

    @Override
    public void onResume () {
        super.onResume();
        Log.e("DialogAct", "onResume");

        getProCityAreaPresenter = new GetProCityAreaPresenterImpl(this);

        Bundle bundle = getIntent().getExtras();
        type = bundle.getInt("type");
        int pid = bundle.getInt("pid");

        String title;
        if ( type == 1 ) {
            title = "选择省";
        }
        else if ( type == 2  ) {
            title = "选择市";
        }
        else {
            title = "选择区";
        }
        initYsxNavitagionBar(title);

        getProCityAreaPresenter.getProCityArea(type, pid);

    }

    @Override
    public void onDestroy () {
        rv_pro_city_area = null;
        getProCityAreaPresenter.onDestroy();
        super.onDestroy();
        Log.e("DialogAct", "onDestroy");
    }

    @Override
    public void onStop () {
        super.onStop();
        Log.e("DialogAct", "onStop");
    }

    @Override
    public void onPause () {
        super.onPause();
        Log.e("DialogAct", "onPause");
    }

    @Override
    public void onNetworkError ( String msg ) {
        Toast.makeText(AddressSelectActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess ( final List<ProCityArea> ls ) {
        rv_pro_city_area = findViewById(R.id.rv_pro_city_area);
        proCityAreaAdapter = new ProCityAreaAdapter(AddressSelectActivity.this, ls);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_pro_city_area.setLayoutManager(linearLayoutManager);
        rv_pro_city_area.addItemDecoration(new YsxRecyclerViewDivider(this, LinearLayout
                .VERTICAL, dip2px(this, 1), ContextCompat.getColor(this, R.color.colorGray)));
        rv_pro_city_area.setAdapter(proCityAreaAdapter);

        proCityAreaAdapter.setOnItemClickListener(new ProCityAreaAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("id", ls.get(position).getId());
                bundle.putString("title", ls.get(position).getTitle());
                intent.putExtras(bundle);
                setResult(200+type, intent);
                finish();
            }
        });

    }

    private void initYsxNavitagionBar ( String t ) {
        ysxNavigationBar = findViewById(R.id.nav);
        ysxNavigationBar.hideLeftSrc();
        ysxNavigationBar.setTitle(t);
        ysxNavigationBar.hideRightText();
        ysxNavigationBar.hideRightSrc();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
