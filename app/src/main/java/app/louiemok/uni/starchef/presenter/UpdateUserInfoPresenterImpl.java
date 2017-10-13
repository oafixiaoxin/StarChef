package app.louiemok.uni.starchef.presenter;

import app.louiemok.uni.starchef.model.updateuserinfo.OnUpdateUserInfoListener;
import app.louiemok.uni.starchef.model.User;
import app.louiemok.uni.starchef.model.updateuserinfo.UserModel;
import app.louiemok.uni.starchef.model.updateuserinfo.UserModelImpl;
import app.louiemok.uni.starchef.view.PersonalView;

/**
 * Created by Administrator on 2017/8/30 0030.
 */

public class UpdateUserInfoPresenterImpl implements UpdateUserInfoPresenter, OnUpdateUserInfoListener {
    private PersonalView personalView;
    private UserModel userModel;

    public UpdateUserInfoPresenterImpl ( PersonalView personalView ) {
        this.personalView = personalView;
        userModel = new UserModelImpl();
    }

    @Override
    public void updateUserInfo ( int userid) {
        userModel.getUserInfo(userid, this);
    }

    @Override
    public void onDestroy () {
        personalView = null;
    }

    @Override
    public void onUserIdError () {
        if ( personalView != null ) {
            personalView.onUserIdError();
        }
    }

    @Override
    public void onNetworkError ( String msg ) {
        if ( personalView != null ) {
            personalView.onNetworkError(msg);
        }
    }

    @Override
    public void onSuccess ( User user ) {
        if ( personalView != null ) {
            personalView.updateUserInfo(user);
        }
    }
}
