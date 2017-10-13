package app.louiemok.uni.starchef.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.louiemok.uni.starchef.BaseActivity;
import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.model.User;
import app.louiemok.uni.starchef.presenter.LoginPresenter;
import app.louiemok.uni.starchef.presenter.LoginPresenterImpl;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;

public class LoginByAccountActivity extends BaseActivity implements LoginView, View
        .OnClickListener, TextWatcher{

    YsxNavigationBar ysxNavigationBar;
    EditText et_account;
    EditText et_password;
    TextView tv_login;
    TextView tv_forgetpwd;

    private final static int UPDATE_LOGIN_ACTIVE = 1;
    private final static int UPDATE_LOGIN_GRAY = 0;

    private LoginPresenter loginPresenter;

    private Handler handler = new Handler() {
        public void handleMessage ( Message message ) {
            switch (message.what) {
                case UPDATE_LOGIN_ACTIVE:
                    tv_login.setBackgroundResource(R.drawable.orange_solid_10dp);
                    tv_login.setClickable(true);
                    break;
                case UPDATE_LOGIN_GRAY:
                    tv_login.setBackgroundResource(R.drawable.light_orange_solid_10dp);
                    tv_login.setClickable(false);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by_account);
    }

    @Override
    public void onResume () {
        super.onResume();
        if ( ysxNavigationBar == null ) {
            initYsxNavigationBar();
        }
        initElements();
        loginPresenter = new LoginPresenterImpl(this);
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
        loginPresenter.onDestroy();
        clearEle();
    }

    private void initYsxNavigationBar () {
        ysxNavigationBar = (YsxNavigationBar)findViewById(R.id.nav);
        ysxNavigationBar.setLeftSrc(R.drawable.arrow_left);
        ysxNavigationBar.setTitle("账号密码登录");
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

    private void initElements () {
        et_account = (EditText)findViewById(R.id.et_account);
        et_account.addTextChangedListener(this);
        et_password = (EditText)findViewById(R.id.et_password);
        et_password.addTextChangedListener(this);
        tv_login = (TextView)findViewById(R.id.tv_login);
        tv_login.setOnClickListener(this);
        tv_forgetpwd = (TextView)findViewById(R.id.tv_forgetpwd);
        tv_forgetpwd.setOnClickListener(this);
    }

    private void clearEle () {
        ysxNavigationBar = null;
        et_account = null;
        et_password = null;
        tv_login = null;
        tv_forgetpwd = null;
    }

    @Override
    public void showLoadingView () {

    }

    @Override
    public void hideLoadingView () {

    }

    @Override
    public void setUsernameError ( String msg ) {
        Toast.makeText(LoginByAccountActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPasswordError ( String msg ) {
        Toast.makeText(LoginByAccountActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void next(User user) {
        Context ct = LoginByAccountActivity.this;
        SharedPreferences preferences = ct.getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("phone", user.getPhone());
        editor.putString("avatar", user.getAvatar());
        editor.putString("nickname", user.getNickname());
        editor.putInt("sex", user.getSex());
        editor.putString("qrcode", user.getQrcode());
        editor.commit();
        finishActivity(LoginActivity.class);
        finish();
    }

    @Override
    public void onNetworkError(String msg) {
        Toast.makeText(LoginByAccountActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPhoneError(String msg) {
        Toast.makeText(LoginByAccountActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick ( View v ) {
        switch (v.getId()) {
            case R.id.tv_login:
                loginPresenter.loginByAccount(et_account.getText().toString(), et_password
                        .getText().toString());
                break;
            case R.id.tv_forgetpwd:
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
        if ( et_account.getText().length() > 0 && et_password.length() > 0 ) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = UPDATE_LOGIN_ACTIVE;
                    handler.sendMessage(message);
                }
            }).start();
        }
        else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = UPDATE_LOGIN_GRAY;
                    handler.sendMessage(message);
                }
            }).start();
        }
    }

    @Override
    public void afterTextChanged(Editable var1) {

    }
}
