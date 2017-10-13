package app.louiemok.uni.starchef.presenter;

import com.loopj.android.http.RequestParams;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public interface AddAddressPresenter {
    void addAddress(RequestParams params);
    void getAddress(String uid, int addressid);
    void editAddress(RequestParams params);
    void onDestroy();
}
