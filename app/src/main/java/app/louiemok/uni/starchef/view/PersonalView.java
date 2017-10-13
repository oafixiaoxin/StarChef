package app.louiemok.uni.starchef.view;

import app.louiemok.uni.starchef.model.User;

/**
 * Created by Administrator on 2017/8/30 0030.
 */

public interface PersonalView {

    void onUserIdError();

    void onNetworkError( String errorMsg );

    void updateUserInfo( User user );

}
