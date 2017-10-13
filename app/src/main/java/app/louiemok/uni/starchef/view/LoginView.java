package app.louiemok.uni.starchef.view;

import app.louiemok.uni.starchef.model.User;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public interface LoginView {

    void showLoadingView();

    void hideLoadingView();

    void setUsernameError( String msg );

    void setPasswordError( String msg );

    void onNetworkError( String msg );

    void onPhoneError ( String msg );

    void next( User user );
}
