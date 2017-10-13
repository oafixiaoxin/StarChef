package app.louiemok.uni.starchef.model.loginmodel;

import app.louiemok.uni.starchef.model.User;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public interface OnLoginFinishedListener {
    void onUsernameError( String msg );
    void onPasswordError( String msg );
    void onPhoneError( String msg );
    void onNetworkError( String msg );
    void onSuccess( User user);
}
