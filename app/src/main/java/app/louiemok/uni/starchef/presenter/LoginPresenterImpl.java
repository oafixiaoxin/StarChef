package app.louiemok.uni.starchef.presenter;

import app.louiemok.uni.starchef.model.User;
import app.louiemok.uni.starchef.model.loginmodel.LoginModel;
import app.louiemok.uni.starchef.model.loginmodel.LoginModelImpl;
import app.louiemok.uni.starchef.model.loginmodel.OnLoginFinishedListener;
import app.louiemok.uni.starchef.view.LoginView;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener {
    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenterImpl ( LoginView loginView ) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl();
    }

    @Override
    public void loginByAccount( String userName, String pwd ) {
        if ( loginView != null ) {
            loginView.showLoadingView();
        }
        loginModel.loginByAccount(userName, pwd, this);
    }

    @Override
    public void loginByCode ( String phone ) {
        if ( loginView != null ) {
            loginView.showLoadingView();
        }
        loginModel.loginByCode(phone, this);
    }

    @Override
    public void onDestroy () {
        loginView = null;
    }

    @Override
    public void onUsernameError ( String msg ) {
        if ( loginView != null ) {
            loginView.setUsernameError(msg);
            loginView.hideLoadingView();
        }
    }

    @Override
    public void onPasswordError ( String msg ) {
        if ( loginView != null ) {
            loginView.setPasswordError(msg);
            loginView.hideLoadingView();
        }
    }

    @Override
    public void onNetworkError ( String msg ) {
        if ( loginView != null ) {
            loginView.onNetworkError(msg);
            loginView.hideLoadingView();
        }
    }

    @Override
    public void onPhoneError ( String msg ) {
        if ( loginView != null ) {
            loginView.onPhoneError(msg);
            loginView.hideLoadingView();
        }
    }

    @Override
    public void onSuccess ( User user ) {
        if ( loginView != null ) {
            loginView.next(user);
        }
    }
}
