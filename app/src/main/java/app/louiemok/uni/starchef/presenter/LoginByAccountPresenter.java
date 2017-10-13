package app.louiemok.uni.starchef.presenter;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public interface LoginByAccountPresenter {
    void loginByAccount(String account, String pwd);
    void onDestroy();
}
