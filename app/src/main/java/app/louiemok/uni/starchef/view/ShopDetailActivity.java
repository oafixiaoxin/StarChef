package app.louiemok.uni.starchef.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.louiemok.uni.starchef.BaseActivity;
import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.adapter.NetCommandAdapter;
import app.louiemok.uni.starchef.adapter.ShopConfigAdapter;
import app.louiemok.uni.starchef.model.Shop;
import app.louiemok.uni.starchef.model.Voucher;
import app.louiemok.uni.starchef.presenter.GetShopInfoPresenter;
import app.louiemok.uni.starchef.presenter.GetShopInfoPresenterImpl;
import app.louiemok.uni.starchef.seledefine.GridViewInScrollView;
import app.louiemok.uni.starchef.seledefine.StarView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class ShopDetailActivity extends BaseActivity implements View.OnClickListener, ShopDetailView{

    ActionBar actionBar;
    StarView ll_star_view;
    ListView lv_net_comment;
    NetCommandAdapter netCommandAdapter;
    Button btn_tocupon;
    LinearLayout ll_cupon_cell;
    RelativeLayout rl_phone_call;
    TextView tv_shop_detail_name;
    TextView tv_shop_detail_type;
    TextView tv_shop_detail_address;
    TextView tv_cupon_count;
    TextView tv_cupon_title;
    TextView tv_cupon_sold;
    TextView tv_chu;
    GridViewInScrollView gv_shop_config;
    ShopConfigAdapter shopConfigAdapter;
    ScrollView sv_shop_detail;
    LinearLayout ll_to_comment;

    GetShopInfoPresenter presenter;

    String shopid;
    String shopContact;
    String shopName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onResume () {
        super.onResume();

        presenter = new GetShopInfoPresenterImpl(this);
        shopid = getIntent().getStringExtra("shopid");

        initNetCommandView();
        initElements();

        btn_tocupon = (Button)findViewById(R.id.btn_tocupon);
        btn_tocupon.setOnClickListener(this);

        presenter.getShopInfo(shopid);

    }

    @Override
    public void onDestroy () {
        super.onDestroy();
        presenter.onDestroy();
        ll_star_view = null;
    }

    @Override
    public boolean onCreateOptionsMenu ( Menu menu ) {
        getMenuInflater().inflate(R.menu.subscribe_shop_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected ( MenuItem menuItem ) {
        switch (menuItem.getItemId()) {
            case R.id.sendtoother:
                Log.e("shopDetailActivity", "sendtoother");
                break;
            case R.id.star_collect:
                Log.e("shopDetailActivity", "star_collect");
                break;
            case R.id.dot:
                Log.e("shopDetailActivity", "dot");
                break;
            default:
                finish();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void initStarView ( int star ) {
        ll_star_view = (StarView)findViewById(R.id.ll_star_view);
        ll_star_view.removeAllViews();
        ll_star_view.setStar(star, R.drawable.star1_normal, R.drawable.star1_cover, 20, 10);
    }

    private void initNetCommandView () {
        ArrayList<HashMap<String, String>> ls = new ArrayList<>();
        lv_net_comment = (ListView)findViewById(R.id.lv_net_comment);
        netCommandAdapter = new NetCommandAdapter(this, ls);
        lv_net_comment.setAdapter(netCommandAdapter);

        setListViewHeightBasedOnChild(lv_net_comment);
    }

    private void initElements () {
        tv_shop_detail_name = (TextView)findViewById(R.id.tv_shop_detail_name);
        tv_shop_detail_type = (TextView)findViewById(R.id.tv_shop_detail_type);
        tv_shop_detail_address = (TextView)findViewById(R.id.tv_shop_detail_address);
        tv_shop_detail_address.setOnClickListener(this);
        tv_chu = (TextView)findViewById(R.id.tv_chu);
        ll_cupon_cell = (LinearLayout)findViewById(R.id.ll_cupon_cell);
        gv_shop_config = (GridViewInScrollView)findViewById(R.id.gv_shop_config);
        sv_shop_detail = (ScrollView)findViewById(R.id.sv_shop_detail);
        rl_phone_call = (RelativeLayout)findViewById(R.id.rl_phone_call);
        rl_phone_call.setOnClickListener(this);
        ll_to_comment = (LinearLayout)findViewById(R.id.ll_to_comment);
        ll_to_comment.setOnClickListener(this);
    }

    private void setListViewHeightBasedOnChild ( ListView ls ) {
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
        params.height = totalHeight + (ls.getDividerHeight() * (listAdapter.getCount() - 1));
        ls.setLayoutParams(params);
    }

    public void onClick ( View view ) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_tocupon:
                intent = new Intent(this, PaymentActivity.class);
                ShopDetailActivity.this.startActivity(intent);
                break;
            case R.id.tv_shop_detail_address:
                intent = new Intent(ShopDetailActivity.this, ShopLocationActivity.class);
                intent.putExtra("shopname", tv_shop_detail_name.getText().toString());
                ShopDetailActivity.this.startActivity(intent);
                break;
            case R.id.rl_phone_call:
                toPhoneCall(shopContact);
                break;
            case R.id.ll_to_comment:
                intent = new Intent(ShopDetailActivity.this, AddCommentActivity.class);
                intent.putExtra("title", shopName);
                intent.putExtra("id", shopid);
                ShopDetailActivity.this.startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void initCuponView ( List<Voucher> voucherList ) {
        if ( voucherList.size() != 0 ) {
            tv_chu.setVisibility(View.VISIBLE);
        }
        ll_cupon_cell.removeAllViews();
        for ( final Voucher voucher : voucherList ) {
            View view  = LayoutInflater.from(this).inflate(R.layout.cupon_cell, null, false);
            tv_cupon_count = view.findViewById(R.id.tv_cupon_count);
            tv_cupon_title = view.findViewById(R.id.tv_cupon_title);
            tv_cupon_sold = view.findViewById(R.id.tv_cupon_sold);
            tv_cupon_count.setText("代金券 1张");
            tv_cupon_title.setText(voucher.getTitle());
            tv_cupon_sold.setText("已售"+voucher.getSold());
            ll_cupon_cell.addView(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ShopDetailActivity.this, BuyCuponActivity.class);
                    intent.putExtra("cuponid", voucher.getId());
                    startActivity(intent);
                }
            });

        }
    }

    private void initShopConfigView ( String configStr ) {
        String[] configAry = configStr.split(",");
        shopConfigAdapter = new ShopConfigAdapter(this, configAry);
        gv_shop_config.setAdapter(shopConfigAdapter);
    }

    @Override
    public void onNetworkError ( String msg ) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess ( Shop shop, List<Voucher> voucherList ) {
        shopName = shop.getName();
        tv_shop_detail_name.setText(shop.getName());
        tv_shop_detail_type.setText(shop.getType());
        tv_shop_detail_address.setText(shop.getAddress());
        shopContact = shop.getContact();
        initStarView(shop.getStar());
        initCuponView(voucherList);
        initShopConfigView(shop.getConfig());
    }

    private void toPhoneCall ( String phoneNum ) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNum));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
