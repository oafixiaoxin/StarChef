package app.louiemok.uni.starchef.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.louiemok.uni.starchef.BaseActivity;
import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.adapter.AddressAdapter;
import app.louiemok.uni.starchef.model.Address;
import app.louiemok.uni.starchef.presenter.GetAddressPresenter;
import app.louiemok.uni.starchef.presenter.GetAddressPresenterImpl;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;
import app.louiemok.uni.starchef.seledefine.YsxRecyclerViewDivider;


public class AddressActivity extends BaseActivity implements View.OnClickListener, AddressView{

    YsxNavigationBar ysxNavigationBar;
    TextView tv_add_address;
    RecyclerView rv_address;
    AddressAdapter addressAdapter;

    List<Address> ls = null;

    boolean editable = false;

    private GetAddressPresenter getAddressPresenter;

    private Handler handler = new Handler(){
        public void handleMessage ( Message message ) {
            switch (message.what) {
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
    }

    @Override
    public void onResume () {
        super.onResume();
        if ( ysxNavigationBar == null ) {
            initYsxNavigationBar();
        }
        getAddressPresenter = new GetAddressPresenterImpl(this);
        initElements();
        getAddressPresenter.getAddressList(getSharedPreferences("login", MODE_PRIVATE).getString
                ("uid", ""));
    }

    @Override
    public void onDestroy () {
        clearEle();
        getAddressPresenter.onDestroy();
        super.onDestroy();
    }

    private void initElements () {
        tv_add_address = (TextView)findViewById(R.id.tv_add_address);
        tv_add_address.setOnClickListener(this);
    }

    private void clearEle () {
        ysxNavigationBar = null;
        tv_add_address = null;
        rv_address = null;
    }

    private void initYsxNavigationBar () {
        ysxNavigationBar = (YsxNavigationBar)findViewById(R.id.nav);
        ysxNavigationBar.setLeftSrc(R.drawable.arrow_left);
        ysxNavigationBar.setTitle("收货地址列表");
        ysxNavigationBar.hideRightSrc();
        ysxNavigationBar.showRightText("编辑");
        ysxNavigationBar.setRightTextColor(R.color.colorOrange);
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
                    editable = true;
                    for ( int i = 0 ; i < ls.size() ; i++ ) {
                        ls.get(i).setIsselected(1);
                    }
                }
                if ( ysxNavigationBar.getRightText().equals("取消") ) {
                    editable = false;
                    for ( int i = 0 ; i < ls.size() ; i++ ) {
                        ls.get(i).setIsselected(0);
                        if ( ls.get(i).getIsuesd() == 1 ) {
                            getAddressPresenter.updateAddressUsed(getSharedPreferences("login",
                                    MODE_PRIVATE).getString("uid", ""), ls.get(i).getId());
                        }
                    }
                }
                addressAdapter.notifyDataSetChanged();
                ysxNavigationBar.setRightText(ysxNavigationBar.getRightText().equals("编辑")
                        ?"取消":"编辑");
                tv_add_address.setText(tv_add_address.getText().equals("新增地址")?"删除地址":"新增地址");
            }
        });
    }

    @Override
    public void onClick ( View view ) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_add_address:
                if ( tv_add_address.getText().equals("新增地址") ) {
                    intent = new Intent(AddressActivity.this, AddAddressActivity.class);
                    AddressActivity.this.startActivity(intent);
                }
                else if ( tv_add_address.getText().equals("删除地址") ) {
                    getAddressPresenter.deleteAddress(getSharedPreferences("login", MODE_PRIVATE)
                            .getString("uid", ""), ls);
                }
                break;
        }
    }

    @Override
    public void onSuccess(final List<Address> addressList) {
        ls = addressList;
        rv_address = (RecyclerView)findViewById(R.id.rv_address);
        addressAdapter = new AddressAdapter(AddressActivity.this, ls);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_address.setLayoutManager(linearLayoutManager);
        rv_address.addItemDecoration(new YsxRecyclerViewDivider(this, LinearLayout.VERTICAL,
                dip2px(this, 1), ContextCompat.getColor(this, R.color.colorGray)));
        rv_address.setAdapter(addressAdapter);

        addressAdapter.setOnItemClickListener(new AddressAdapter.MyItemClickListener() {
            @Override
            public void choose(View view, int position) {
                Address address = ls.get(position);
                if ( address.getIsselected() != 2 ) {
                    address.setIsselected(2);
                }
                else {
                    address.setIsselected(1);
                }
                addressAdapter.notifyItemChanged(position);
            }

            @Override
            public void select(View view, int position) {
                if ( editable ) {
                    for (Address ad : ls) {
                        ad.setIsuesd(0);
                    }
                    Address address = ls.get(position);
                    address.setIsuesd(1);
                    addressAdapter.notifyDataSetChanged();
                }
                else {
                    Intent intent = new Intent(AddressActivity.this, AddAddressActivity.class);
                    intent.putExtra("type", 1);
                    intent.putExtra("addressid", ls.get(position).getId());
                    AddressActivity.this.startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onNetworkError(String msg) {
        Toast.makeText(AddressActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateAddressUsedSuccess () {

    }

    @Override
    public void onDeleteAddressSuccess ( List<Address> addressList ) {
        Toast.makeText(AddressActivity.this, "删除地址成功", Toast.LENGTH_SHORT).show();
        getAddressPresenter.getAddressList(getSharedPreferences("login", MODE_PRIVATE).getString
                ("uid", ""));
    }

    @Override
    public void updateAddressListSuccess (  List<Address> addressList ) {
        ls.clear();
        ls = addressList;
        addressAdapter.notifyDataSetChanged();
    }
}
