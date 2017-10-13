package app.louiemok.uni.starchef.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

import app.louiemok.uni.starchef.BaseActivity;
import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.model.Address;
import app.louiemok.uni.starchef.presenter.AddAddressPresenter;
import app.louiemok.uni.starchef.presenter.AddAddressPresenterImpl;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;

public class AddAddressActivity extends BaseActivity implements View.OnClickListener, AddAddressView {

    YsxNavigationBar ysxNavigationBar = null;
    EditText et_address_contact_name;
    EditText et_address_addr;
    EditText et_address_contact_phone;
    EditText et_address_contact_mailcode;
    TextView tv_province;
    TextView tv_city;
    TextView tv_area;
    TextView tv_add_address;

    int provinceId;
    int cityId;
    int areaId;
    int type;
    int addressid;

    private AddAddressPresenter addAddressPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
    }

    @Override
    public void onResume () {
        super.onResume();
        type = getIntent().getIntExtra("type", 0);
        addressid = getIntent().getIntExtra("addressid", 0);
        if ( ysxNavigationBar == null ) {
            initYsxNavigationBar();
        }
        addAddressPresenter = new AddAddressPresenterImpl(this);
        initElements();

        if ( type != 0 && addressid != 0 ) {
            addAddressPresenter.getAddress(getSharedPreferences("login", MODE_PRIVATE).getString
                    ("uid", ""), addressid);
        }

    }

    @Override
    public void onDestroy () {
        clearEle();
        type = 0;
        addressid = 0;
        addAddressPresenter.onDestroy();
        super.onDestroy();
    }

    private void initElements () {
        et_address_contact_name = (EditText)findViewById(R.id.et_address_contact_name);
        et_address_addr = (EditText)findViewById(R.id.et_address_addr);
        et_address_contact_phone = (EditText)findViewById(R.id.et_address_contact_phone);
        et_address_contact_mailcode = (EditText)findViewById(R.id.et_address_contact_mailcode);
        tv_province = (TextView)findViewById(R.id.tv_province);
        tv_province.setOnClickListener(this);
        tv_city = (TextView)findViewById(R.id.tv_city);
        tv_city.setOnClickListener(this);
        tv_area = (TextView)findViewById(R.id.tv_area);
        tv_area.setOnClickListener(this);
        tv_add_address = (TextView)findViewById(R.id.tv_add_address);
        tv_add_address.setOnClickListener(this);
    }

    private void clearEle () {
        ysxNavigationBar = null;
    }

    private void initYsxNavigationBar () {
        ysxNavigationBar = (YsxNavigationBar)findViewById(R.id.nav);
        ysxNavigationBar.setLeftSrc(R.drawable.arrow_left);
        ysxNavigationBar.setTitle(type==0?"新增收货地址":"编辑收货地址");
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

    @Override
    public void onClick ( View v ) {
        Intent intent;
        Bundle bundle;
        switch (v.getId()) {
            case R.id.tv_province:
                intent = new Intent(AddAddressActivity.this, AddressSelectActivity.class);
                bundle = new Bundle();
                bundle.putInt("type", 1);
                bundle.putInt("pid", 0);
                intent.putExtras(bundle);
                AddAddressActivity.this.startActivityForResult(intent, 101);
                break;
            case R.id.tv_city:
                if ( !TextUtils.isEmpty(tv_province.getText()) ) {
                    intent = new Intent(AddAddressActivity.this, AddressSelectActivity.class);
                    bundle = new Bundle();
                    bundle.putInt("type", 2);
                    bundle.putInt("pid", provinceId);
                    intent.putExtras(bundle);
                    AddAddressActivity.this.startActivityForResult(intent, 102);
                }
                else {
                    Toast.makeText(AddAddressActivity.this, "请选择省", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_area:
                if ( !TextUtils.isEmpty(tv_city.getText()) ) {
                    intent = new Intent(AddAddressActivity.this, AddressSelectActivity.class);
                    bundle = new Bundle();
                    bundle.putInt("type", 3);
                    bundle.putInt("pid", cityId);
                    intent.putExtras(bundle);
                    AddAddressActivity.this.startActivityForResult(intent, 103);
                }
                else {
                    Toast.makeText(AddAddressActivity.this, "请选择市", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_add_address:
                if ( type == 0 ) {
                    if ( optionsCheck() ) {
                        RequestParams params = new RequestParams();
                        params.put("uid", getSharedPreferences("login", MODE_PRIVATE).getString
                                ("uid", ""));
                        params.put("name", et_address_contact_name.getText().toString());
                        params.put("contact", et_address_contact_phone.getText().toString());
                        params.put("address", tv_province.getText()+" "+tv_city.getText()+" "+tv_area
                                .getText()+" "+et_address_addr.getText());
                        if ( !TextUtils.isEmpty(et_address_contact_mailcode.getText()) ) {
                            params.put("mailcode", et_address_contact_mailcode.getText().toString());
                        }
                        addAddressPresenter.addAddress(params);
                    }
                }
                else {
                    if ( optionsCheck() ) {
                        RequestParams params = new RequestParams();
                        params.put("uid", getSharedPreferences("login", MODE_PRIVATE).getString
                                ("uid", ""));
                        params.put("addressid", addressid);
                        params.put("name", et_address_contact_name.getText().toString());
                        params.put("contact", et_address_contact_phone.getText().toString());
                        params.put("address", tv_province.getText()+" "+tv_city.getText()+" "+tv_area
                                .getText()+" "+et_address_addr.getText());
                        if ( !TextUtils.isEmpty(et_address_contact_mailcode.getText()) ) {
                            params.put("mailcode", et_address_contact_mailcode.getText().toString());
                        }
                        addAddressPresenter.editAddress(params);
                    }
                }
                break;
        }
    }

    @Override
    public void onNetworkError ( String msg) {
        Toast.makeText(AddAddressActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess ( String msg ) {
        Toast.makeText(AddAddressActivity.this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onGetInfoSuccess (Address ad) {
        et_address_contact_name.setText(ad.getName());
        et_address_contact_phone.setText(ad.getContact());
        if ( ad.getMailcode() != 0 ) {
            et_address_contact_mailcode.setText(String.valueOf(ad.getMailcode()));
        }
        String[] strs = ad.getAddress().split(" ");
        tv_province.setText(strs[0]);
        tv_city.setText(strs[1]);
        tv_area.setText(strs[2]);
        et_address_addr.setText(strs[3]);
    }

    @Override
    protected void onActivityResult ( int requestCode, int responseCode, Intent data ) {
        if ( data == null ) {
            return;
        }
        Bundle bundle;
        bundle = data.getExtras();
        super.onActivityResult(requestCode, responseCode, data);
        switch (responseCode) {
            case 201:
                tv_province.setText(bundle.getString("title"));
                provinceId = bundle.getInt("id");
                break;
            case 202:
                tv_city.setText(bundle.getString("title"));
                cityId = bundle.getInt("id");
                break;
            case 203:
                tv_area.setText(bundle.getString("title"));
                areaId = bundle.getInt("id");
                break;
        }
    }

    private boolean optionsCheck () {
        if ( TextUtils.isEmpty(et_address_contact_name.getText()) ) {
            Toast.makeText(AddAddressActivity.this, "收货人不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( TextUtils.isEmpty(tv_province.getText()) ) {
            Toast.makeText(AddAddressActivity.this, "省不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( TextUtils.isEmpty(tv_city.getText()) ) {
            Toast.makeText(AddAddressActivity.this, "市不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( TextUtils.isEmpty(tv_area.getText()) ) {
            Toast.makeText(AddAddressActivity.this, "区不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( TextUtils.isEmpty(et_address_addr.getText()) ) {
            Toast.makeText(AddAddressActivity.this, "地址不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( TextUtils.isEmpty(et_address_contact_phone.getText()) ) {
            Toast.makeText(AddAddressActivity.this, "电话不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
