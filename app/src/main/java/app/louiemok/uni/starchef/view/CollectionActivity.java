package app.louiemok.uni.starchef.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.louiemok.uni.starchef.BaseActivity;
import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.adapter.CollectionAdapter;
import app.louiemok.uni.starchef.model.Collection;
import app.louiemok.uni.starchef.presenter.GetCollectionPresenter;
import app.louiemok.uni.starchef.presenter.GetCollectionPresenterImpl;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;

public class CollectionActivity extends BaseActivity implements CollectionView, View.OnClickListener {

    private YsxNavigationBar ysxNavigationBar = null;
    private TextView tv_all;
    private TextView tv_shop;
    private TextView tv_cupon;
    private TextView tv_comment;
    private TextView tv_video;
    private ListView lv_collection;

    CollectionAdapter collectionAdapter;
    GetCollectionPresenter getCollectionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
    }

    @Override
    public void onResume () {
        super.onResume();
        if ( ysxNavigationBar == null ) {
            initYsxNavigationBar();
        }
        initElements();
        getCollectionPresenter = new GetCollectionPresenterImpl(this);
        getCollectionPresenter.getCollection(getSharedPreferences("login", MODE_PRIVATE)
                .getString("uid", ""), "shop");
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
        getCollectionPresenter.onDestroy();
        ysxNavigationBar = null;
    }

    @Override
    public void onGetCollectionSuccess ( List<Collection> ls ) {
        initListView(ls);
    }

    @Override
    public void onDeleteCollectionSuccess () {

    }

    @Override
    public void onNetworkError ( String msg ) {
        Toast.makeText(CollectionActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick ( View v ) {
        switch (v.getId()) {
            case R.id.tv_all:
                changeSegment(0);
                break;
            case R.id.tv_shop:
                changeSegment(1);
                break;
            case R.id.tv_cupon:
                changeSegment(2);
                break;
            case R.id.tv_comment:
                changeSegment(3);
                break;
            case R.id.tv_video:
                changeSegment(4);
                break;
        }
    }

    private void initYsxNavigationBar () {
        ysxNavigationBar = (YsxNavigationBar)findViewById(R.id.nav);
        ysxNavigationBar.setLeftSrc(R.drawable.arrow_left);
        ysxNavigationBar.setTitle("我的收藏");
        ysxNavigationBar.setRightText("编辑");
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

    private void initElements () {
        tv_all = (TextView)findViewById(R.id.tv_all);
        tv_all.setOnClickListener(this);
        tv_shop = (TextView)findViewById(R.id.tv_shop);
        tv_shop.setOnClickListener(this);
        TextPaint tp = tv_shop.getPaint();
        tp.setFakeBoldText(true);
        tv_cupon = (TextView)findViewById(R.id.tv_cupon);
        tv_cupon.setOnClickListener(this);
        tv_comment = (TextView)findViewById(R.id.tv_comment);
        tv_comment.setOnClickListener(this);
        tv_video = (TextView)findViewById(R.id.tv_video);
        tv_video.setOnClickListener(this);
        lv_collection = (ListView)findViewById(R.id.lv_collection);
    }

    private void initListView (List<Collection> list) {
        collectionAdapter = new CollectionAdapter(this, list);
        lv_collection.setAdapter(collectionAdapter);
    }

    private void changeSegment ( int index ) {
        TextView[] tvs = {tv_all, tv_shop, tv_cupon, tv_comment, tv_video};
        for ( TextView tv : tvs ) {
            tv.setTextColor(getResources().getColor(R.color.textColor));
            TextPaint tp = tv.getPaint();
            tp.setFakeBoldText(false);
            tv.setBackgroundResource(R.drawable.bottom_gray_0p5dp);
        }
        tvs[index].setBackgroundResource(R.drawable.bottom_orange_main_gray);
        tvs[index].setTextColor(getResources().getColor(R.color.bottomPersonalColor));
        TextPaint textPaint = tvs[index].getPaint();
        textPaint.setFakeBoldText(true);
    }
}
