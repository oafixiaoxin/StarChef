package app.louiemok.uni.starchef.model.loginmodel;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import app.louiemok.uni.starchef.model.User;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class LoginModelImpl implements LoginModel {
    private AsyncHttpClient client = null;
    private final static String LOGIN_BY_ACCOUNT_URL =
            "http://119.23.254.5/apistarchef/public/index.php/api/v1/userLoginByAccount";
    private final static String LOGIN_BY_CODE_URL =
             "http://119.23.254.5/apistarchef/public/index.php/api/v1/userLoginByCode";

    @Override
    public void loginByAccount ( String userName, String password, final OnLoginFinishedListener
            listener ) {
        boolean error = false;
        if ( TextUtils.isEmpty(userName) ) {
            listener.onUsernameError("帐号不能为空");
            error = true;
        }
        if ( TextUtils.isEmpty(password) ) {
            listener.onPasswordError("密码不能为空");
            error = true;
        }
        if ( !error ) {
            client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("phone", userName);
            params.put("password", password);
            client.post(LOGIN_BY_ACCOUNT_URL, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess ( int status, Header[] headers, JSONObject response ) {
                    try {
                        if ( status == 200 ) {
                            if ( response.getString("responseCode").equals("1") ) {
                                Log.e("data", response.getString("responseBody"));
                                JSONObject object = response.getJSONObject("responseBody");
                                User.Builder builder = new User.Builder();
                                User user = builder.uid(object.getString("uid"))
                                        .account(object.getString("account"))
                                        .password(object.getString("password"))
                                        .nickname(object.getString("nickname"))
                                        .age(object.getInt("age"))
                                        .sex(object.getInt("sex"))
                                        .avatar(object.getString("avatar"))
                                        .qrcode(object.getString("qrcode"))
                                        .addressid(object.getInt("addressid"))
                                        .createtime(object.getString("createtime"))
                                        .lastlogintime(object.getString("lastlogintime"))
                                        .isactive(object.getInt("isactive"))
                                        .point(object.getInt("point"))
                                        .star(object.getInt("star"))
                                        .phone(object.getString("phone"))
                                        .build();
                                listener.onSuccess(user);
                            }
                            else {
                                listener.onNetworkError(response.getString("responseMsg"));
                            }
                        }
                        else {
                            listener.onNetworkError("网络错误");
                        }
                    }
                    catch ( Exception ex ) {
                        ex.printStackTrace();
                        listener.onNetworkError(ex.getMessage());
                    }
                }

                @Override
                public void onFailure ( int status, Header[] headers, String bytes, Throwable throwable) {
                    throwable.printStackTrace();
                    listener.onNetworkError(throwable.getMessage());
                }
            });
        }
    }

    @Override
    public void loginByCode ( String phone, final OnLoginFinishedListener listener ) {
        boolean error = false;
        if ( TextUtils.isEmpty(phone) ) {
            error = true;
            listener.onPhoneError("手机号码不能为空");
        }
        if ( !error ) {
            client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("phone", phone);
            client.post(LOGIN_BY_CODE_URL, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess ( int status, Header[] headers, JSONObject response ) {
                    try{
                        if ( status == 200 ) {
                            if ( response.getString("responseCode").equals("1") ) {
                                JSONObject object = response.getJSONObject("responseBody");
                                User.Builder builder = new User.Builder();
                                User user = builder.uid(object.getString("uid"))
                                        .account(object.getString("account"))
                                        .password(object.getString("password"))
                                        .nickname(object.getString("nickname"))
                                        .age(object.getInt("age"))
                                        .sex(object.getInt("sex"))
                                        .avatar(object.getString("avatar"))
                                        .qrcode(object.getString("qrcode"))
                                        .addressid(object.getInt("addressid"))
                                        .createtime(object.getString("createtime"))
                                        .lastlogintime(object.getString("lastlogintime"))
                                        .isactive(object.getInt("isactive"))
                                        .point(object.getInt("point"))
                                        .star(object.getInt("star"))
                                        .phone(object.getString("phone"))
                                        .build();
                                listener.onSuccess(user);
                            }
                            else {
                                listener.onNetworkError(response.getString("responseMsg"));
                            }
                        }
                        else {
                            listener.onNetworkError("网络错误");
                        }
                    }
                    catch ( Exception ex ) {
                        ex.printStackTrace();
                        listener.onNetworkError(ex.getMessage());
                    }
                }

                @Override
                public void onFailure ( int status, Header[] headers, String bytes, Throwable
                        throwable ) {
                    throwable.printStackTrace();
                    listener.onNetworkError(throwable.getMessage());
                }
            });
        }
    }
}
