package app.louiemok.uni.starchef.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    YsxNavigationBar ysxNavigationBar;
    LinearLayout ll_information;
    LinearLayout ll_location;
    LinearLayout ll_safe;
    LinearLayout ll_message;
    LinearLayout ll_feedback;
    LinearLayout ll_common;
    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @Override
    public void onResume () {
        super.onResume();
        initYsxNavigationBar();
        initElements();
    }

    public void initYsxNavigationBar () {
        ysxNavigationBar = (YsxNavigationBar)findViewById(R.id.nav);
        ysxNavigationBar.setTitle("更多");
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
        ll_information = (LinearLayout)findViewById(R.id.ll_information);
        ll_location = (LinearLayout)findViewById(R.id.ll_location);
        ll_safe = (LinearLayout)findViewById(R.id.ll_safe);
        ll_message = (LinearLayout)findViewById(R.id.ll_message);
        ll_feedback = (LinearLayout)findViewById(R.id.ll_feedback);
        ll_common = (LinearLayout)findViewById(R.id.ll_common);
        btn_logout = (Button)findViewById(R.id.btn_logout);

        ll_information.setOnClickListener(this);
        ll_location.setOnClickListener(this);
        ll_safe.setOnClickListener(this);
        ll_message.setOnClickListener(this);
        ll_feedback.setOnClickListener(this);
        ll_common.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
    }

    public void onClick ( View view ) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_information:
                intent = new Intent(SettingActivity.this, PersonalInfoActivity.class);
                SettingActivity.this.startActivity(intent);
                break;
            case R.id.ll_location:
                intent = new Intent(SettingActivity.this, AddressActivity.class);
                SettingActivity.this.startActivity(intent);
                break;
            case R.id.ll_safe:
                break;
            case R.id.ll_message:
                break;
            case R.id.ll_feedback:
                break;
            case R.id.ll_common:
                break;
            case R.id.btn_logout:
                logoutDialog();
                break;
            default:
                break;
        }
    }

    private void logoutDialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出该账号吗?");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                finish();
            }
        });
        builder.create().show();
    }
}
