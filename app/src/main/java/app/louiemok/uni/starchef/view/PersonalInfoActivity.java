package app.louiemok.uni.starchef.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.seledefine.CircleImageView;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener{

    YsxNavigationBar ysxNavigationBar;
    LinearLayout ll_avatar;
    LinearLayout ll_usernickname;
    LinearLayout ll_sex;
    LinearLayout ll_qrcode;
    CircleImageView iv_avatar;
    ImageView iv_qrcode;
    TextView tv_usernickname;
    TextView tv_sex;
    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
    }

    @Override
    public void onResume () {
        super.onResume();
        initYsxNavigationBar();
        initElements();
    }

    public void initYsxNavigationBar () {
        ysxNavigationBar = (YsxNavigationBar)findViewById(R.id.nav);
        ysxNavigationBar.setTitle("个人资料");
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

    public void initElements () {
        ll_avatar = (LinearLayout)findViewById(R.id.ll_avatar);
        ll_usernickname = (LinearLayout)findViewById(R.id.ll_usernickname);
        ll_sex = (LinearLayout)findViewById(R.id.ll_sex);
        ll_qrcode = (LinearLayout)findViewById(R.id.ll_qrcode);
        iv_avatar = (CircleImageView)findViewById(R.id.iv_avatar);
        iv_qrcode = (ImageView)findViewById(R.id.iv_qrcode);
        tv_usernickname = (TextView)findViewById(R.id.tv_usernickname);
        tv_sex = (TextView)findViewById(R.id.tv_sex);
        btn_logout = (Button)findViewById(R.id.btn_logout);

        ll_avatar.setOnClickListener(this);
        ll_usernickname.setOnClickListener(this);
        ll_sex.setOnClickListener(this);
        ll_qrcode.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
    }

    public void onClick ( View view ) {
        switch (view.getId()) {
            case R.id.ll_avatar:
                break;
            case R.id.ll_usernickname:
                break;
            case R.id.ll_sex:
                break;
            case R.id.ll_qrcode:
                break;
            case R.id.btn_logout:
                break;
            default:
                break;
        }
    }
}
