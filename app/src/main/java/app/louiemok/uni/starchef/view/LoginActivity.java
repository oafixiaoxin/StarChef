package app.louiemok.uni.starchef.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.louiemok.uni.starchef.BaseActivity;
import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.model.InternationalArea;
import app.louiemok.uni.starchef.model.User;
import app.louiemok.uni.starchef.presenter.LoginPresenter;
import app.louiemok.uni.starchef.presenter.LoginPresenterImpl;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener, TextWatcher{

    public static final int UPDATE_LOGIN_TEXTVIEW_ACTIVE = 1;
    public static final int UPDATE_LOGIN_TEXTVIEW_GRAY = 2;
    public static final int ASYNC_LOGIN = 3;

    YsxNavigationBar ysxNavigationBar;
    LinearLayout ll_phone_area;
    LinearLayout ll_wechat_login;
    LinearLayout ll_qq_login;
    LinearLayout ll_sina_login;
    EditText et_phone;
    EditText et_verify_code;
    TextView btn_verify_code;
    TextView tv_login;
    TextView tv_loginwithproblems;
    TextView tv_normal_login;
    TextView tv_protocal;
    EventHandler eventHandler;

    boolean canLogin = false;

    LoginPresenter loginPresenter;
    private final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60*1000, 1000);

    static String TAG = "LoginActivity";

    private Handler handler = new Handler() {
        public void handleMessage ( Message message ) {
            switch (message.what) {
                case UPDATE_LOGIN_TEXTVIEW_ACTIVE:
                    tv_login.setBackgroundResource(R.drawable.orange_solid_10dp);
                    break;
                case UPDATE_LOGIN_TEXTVIEW_GRAY:
                    tv_login.setBackgroundResource(R.drawable.light_orange_solid_10dp);
                    break;
                case ASYNC_LOGIN:
                    if ( canLogin ) {
                        loginPresenter.loginByCode(et_phone.getText().toString());
                    }
                    break;
                default:
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initYsxNavigationBar();
        initElements();
        initMobSDK();

        loginPresenter = new LoginPresenterImpl(this);

    }

    @Override
    public void onResume () {
        super.onResume();
    }

    private void initElements () {
        ll_phone_area = (LinearLayout)findViewById(R.id.ll_phone_area);
        ll_phone_area.setOnClickListener(this);
        ll_wechat_login = (LinearLayout)findViewById(R.id.ll_wechat_login);
        ll_wechat_login.setOnClickListener(this);
        ll_qq_login = (LinearLayout)findViewById(R.id.ll_qq_login);
        ll_qq_login.setOnClickListener(this);
        ll_sina_login = (LinearLayout)findViewById(R.id.ll_sina_login);
        ll_sina_login.setOnClickListener(this);
        et_phone = (EditText)findViewById(R.id.et_phone);
        et_phone.addTextChangedListener(this);
        et_verify_code = (EditText)findViewById(R.id.et_verify_code);
        et_verify_code.addTextChangedListener(this);
        btn_verify_code = (TextView)findViewById(R.id.btn_verify_code);
        btn_verify_code.setOnClickListener(this);
        tv_login = (TextView)findViewById(R.id.tv_login);
        tv_login.setOnClickListener(this);
        tv_loginwithproblems = (TextView)findViewById(R.id.tv_loginwithproblems);
        tv_loginwithproblems.setOnClickListener(this);
        tv_normal_login = (TextView)findViewById(R.id.tv_normal_login);
        tv_normal_login.setOnClickListener(this);
        tv_protocal = (TextView)findViewById(R.id.tv_protocal);
        tv_protocal.setOnClickListener(this);
    }

    private void initYsxNavigationBar () {
        ysxNavigationBar = (YsxNavigationBar)findViewById(R.id.nav);
        ysxNavigationBar.setLeftSrc(R.drawable.cancel);
        ysxNavigationBar.setTitle("手机号快捷登录");
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

    @Override
    protected void onDestroy () {
        super.onDestroy();
        loginPresenter.onDestroy();
        handler = null;
        canLogin = false;
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    @Override
    public void showLoadingView () {
        Log.e(TAG, "showLoadingView");
    }

    @Override
    public void hideLoadingView () {
        Log.e(TAG, "hideLoadingView");
    }

    @Override
    public void setUsernameError ( String msg ) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPasswordError ( String msg ) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkError ( String msg ) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPhoneError ( String msg ) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void next ( User user ) {
        Context ct = LoginActivity.this;
        SharedPreferences preferences = ct.getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("uid", user.getUid());
        editor.putString("phone", user.getPhone());
        editor.putString("avatar", user.getAvatar());
        editor.putString("nickname", user.getNickname());
        editor.putInt("sex", user.getSex());
        editor.putString("qrcode", user.getQrcode());
        editor.commit();
        finish();
    }

    public void onClick ( View view ) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_login:
                SMSSDK.submitVerificationCode("86", et_phone.getText().toString(),
                        et_verify_code.getText().toString());
                break;
            case R.id.btn_verify_code:
//                SMSSDK.getSupportedCountries();
                myCountDownTimer.start();
                SMSSDK.getVerificationCode("86", et_phone.getText().toString());
                break;
            case R.id.tv_normal_login:
                intent = new Intent(LoginActivity.this, LoginByAccountActivity.class);
                LoginActivity.this.startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {

    }

    @Override
    public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {
        if ( et_phone.getText().length() > 0 && et_verify_code.length() > 0 ) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = UPDATE_LOGIN_TEXTVIEW_ACTIVE;
                    handler.sendMessage(message);
                }
            }).start();
            canLogin = true;
        }
        if ( var1.length() == 0 ) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = UPDATE_LOGIN_TEXTVIEW_GRAY;
                    handler.sendMessage(message);
                }
            }).start();
            canLogin = false;
        }
    }

    @Override
    public void afterTextChanged(Editable var1) {

    }

    private void initMobSDK () {
        eventHandler = new EventHandler() {
            public void afterEvent ( int event, int result, Object data ) {
                if ( result == SMSSDK.RESULT_COMPLETE ) {
                    if ( event == SMSSDK.EVENT_GET_VERIFICATION_CODE ) {
                        //获取验证码成功
                        Log.e("getCode", data.toString());
                    }
                    else if ( event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE ) {
                        //提交验证码成功
                        Log.e("data", data.toString());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Message message = new Message();
                                message.what = ASYNC_LOGIN;
                                handler.sendMessage(message);
                            }
                        }).start();
                    }
                    else if ( event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES ) {
                        //返回支持发送验证码的国家列表

                    }
                }
                else {
                    ((Throwable)data).printStackTrace();
                    Log.e("th", ((Throwable) data).getMessage());
                }
            }
        };
        SMSSDK.registerEventHandler(eventHandler);
    }

    private class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer ( long millisInFuture, long countDownInterval ) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick ( long l ) {
            btn_verify_code.setClickable(false);
            btn_verify_code.setBackgroundResource(R.drawable.lightgray_5dp_border);
            btn_verify_code.setTextColor(getResources().getColor(R.color.colorWhite));
            btn_verify_code.setText("重新发送("+l/1000+"s)");
        }

        //计时完毕的方法
        @Override
        public void onFinish () {
            btn_verify_code.setText("获取验证码");
            btn_verify_code.setClickable(true);
            btn_verify_code.setBackgroundResource(R.drawable.white_5dp_border);
            btn_verify_code.setTextColor(getResources().getColor(R.color.textColor));
        }
    }

}
