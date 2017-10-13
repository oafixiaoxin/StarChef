package app.louiemok.uni.starchef.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.louiemok.uni.starchef.BaseActivity;
import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;

public class PaymentActivity extends BaseActivity implements View.OnClickListener {

    YsxNavigationBar ysxNavigationBar;
    EditText et_price;
    TextView tv_sure_pay;
    View v_tick;
    RelativeLayout rl_unjoin;
    RelativeLayout rl_tousecupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    @Override
    public void onResume () {
        super.onResume();
        initYsxNavigationBar();
        initElements();
    }

    private void initElements () {
        et_price = (EditText)findViewById(R.id.et_price);
        tv_sure_pay = (TextView)findViewById(R.id.tv_sure_pay);
        tv_sure_pay.setOnClickListener(this);
        et_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_sure_pay.setBackgroundResource(charSequence.length()>0?R.drawable.orange_solid_10dp:R
                        .drawable.light_orange_solid_10dp);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        v_tick = (View)findViewById(R.id.v_tick);
        rl_unjoin = (RelativeLayout)findViewById(R.id.rl_unjoin);
        v_tick.setOnClickListener(this);
        rl_tousecupon = (RelativeLayout)findViewById(R.id.rl_tousecupon);
        rl_tousecupon.setOnClickListener(this);
    }

    private void initYsxNavigationBar () {
        ysxNavigationBar = (YsxNavigationBar)findViewById(R.id.nav);
        ysxNavigationBar.setLeftSrc(R.drawable.arrow_left);
        ysxNavigationBar.setTitle("肯德基汽车穿梭餐厅");
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

    public void onClick ( View view ) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_sure_pay:
                Toast.makeText(PaymentActivity.this, TextUtils.isEmpty(et_price.getText())?"can " +
                        "not pay":"can pay", Toast
                        .LENGTH_SHORT).show();
                break;
            case R.id.v_tick:
                v_tick.setBackgroundResource(rl_unjoin.getVisibility()==View.VISIBLE?R.drawable
                        .white_5dp_border:R.drawable.tick_orange);
                rl_unjoin.setVisibility(rl_unjoin.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);
                break;
            case R.id.rl_tousecupon:
                intent = new Intent(PaymentActivity.this, UseCuponActivity.class);
                PaymentActivity.this.startActivity(intent);
                break;
            default:
                break;
        }
    }

}
