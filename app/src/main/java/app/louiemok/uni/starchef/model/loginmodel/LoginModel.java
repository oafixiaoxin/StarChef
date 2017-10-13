package app.louiemok.uni.starchef.model.loginmodel;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public interface LoginModel {
    void loginByAccount(String userName, String password, OnLoginFinishedListener listener);
    void loginByCode(String phone, OnLoginFinishedListener listener);
}
