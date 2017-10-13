package app.louiemok.uni.starchef.model.updateuserinfo;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import app.louiemok.uni.starchef.model.User;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2017/8/30 0030.
 */

public class UserModelImpl implements UserModel {
    AsyncHttpClient client = null;
    static String url = "http://119.23.254.5/blog/public/index.php/api2/v1/getUserInfo/";

    @Override
    public void getUserInfo (int userid, final OnUpdateUserInfoListener listener ) {
        client = new AsyncHttpClient();
        client.get(url+userid, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess ( int status, Header[] headers, JSONObject response ) {
                try {
                    if ( status == 200 ) {
                        if ( response.getString("responseCode").equals("1") ) {
                            JSONObject object = response.getJSONObject("responseBody");
//                            User.Builder builder = new User.Builder();
//                            User user = builder.id(object
//                                    .getInt("id"))
//                                    .account(object.getString("account"))
//                                    .password(object.getString("password"))
//                                    .name(object.getString("name"))
//                                    .gender(object.getInt("gender"))
//                                    .note(object.getString("note"))
//                                    .filename(object.getString("filename"))
//                                    .build();
//                            listener.onSuccess(user);
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
                    listener.onNetworkError(ex.getMessage());
                }
            }

            @Override
            public void onFailure ( int status, Header[] headers, String bytes, Throwable
                    throwable ) {
                listener.onNetworkError(throwable.getMessage());
            }
        });
    }
}
