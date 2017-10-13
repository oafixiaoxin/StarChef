package app.louiemok.uni.starchef.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import app.louiemok.uni.starchef.BaseActivity;
import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;

public class UseCuponActivity extends BaseActivity {

    YsxNavigationBar ysxNavigationBar;
    EditText et_cupon_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_cupon);
    }

    @Override
    public void onResume () {
        super.onResume();
        initYsxNavigationBar();
        initElements();
    }

    private void initElements () {
        et_cupon_code = (EditText)findViewById(R.id.et_cupon_code);
    }

    private void initYsxNavigationBar () {
        ysxNavigationBar = (YsxNavigationBar)findViewById(R.id.nav);
        ysxNavigationBar.setLeftSrc(R.drawable.arrow_left);
        ysxNavigationBar.setTitle("使用优惠");
        ysxNavigationBar.setRightText("使用");
        ysxNavigationBar.hideRightSrc();
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
                if ( TextUtils.isEmpty(et_cupon_code.getText()) ) {
                    Toast.makeText(UseCuponActivity.this, "请输入优惠码", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
