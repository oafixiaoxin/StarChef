package app.louiemok.uni.starchef.model.getaddress;

import java.util.List;

import app.louiemok.uni.starchef.model.Address;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public interface GetAddressModel {
    void getAddressList(String uid, OnGetAddressFinishedListener listener);
    void getAddressInfo(String uid, int addressId, OnGetAddressFinishedListener listener);
    void updateAddressUsed(String uid, int addressId, OnGetAddressFinishedListener listener);
    void deleteAddress(String uid, List<Address> addressList, OnGetAddressFinishedListener listener);
}
