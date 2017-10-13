package app.louiemok.uni.starchef.presenter;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public interface LoginPresenter {
    void loginByAccount(String userName, String password);
    void loginByCode(String phone);
    void onDestroy();
}
