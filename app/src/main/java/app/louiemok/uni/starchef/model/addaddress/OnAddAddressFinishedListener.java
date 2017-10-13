package app.louiemok.uni.starchef.model.addaddress;

import app.louiemok.uni.starchef.model.Address;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public interface OnAddAddressFinishedListener {
    void onNetworkError(String msg);
    void onSuccess(String msg);
    void getAddressInfoSuccess(Address address);
}
