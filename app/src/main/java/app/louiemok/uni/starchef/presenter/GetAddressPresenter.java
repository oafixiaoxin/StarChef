package app.louiemok.uni.starchef.presenter;

import java.util.List;

import app.louiemok.uni.starchef.model.Address;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public interface GetAddressPresenter {
    void getAddressList(String uid);
    void getAddressInfo(String uid, int addressId);
    void updateAddressUsed(String uid, int addressId);
    void deleteAddress(String uid, List<Address> addressList);
    void updateAddressList(String uid);
    void onDestroy();
}
