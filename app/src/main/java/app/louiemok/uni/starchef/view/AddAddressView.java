package app.louiemok.uni.starchef.view;

import app.louiemok.uni.starchef.model.Address;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public interface AddAddressView {
    void onNetworkError(String msg);
    void onSuccess(String msg);
    void onGetInfoSuccess(Address address);
}
