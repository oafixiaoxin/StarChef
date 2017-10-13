package app.louiemok.uni.starchef.model.getaddress;

import java.util.List;

import app.louiemok.uni.starchef.model.Address;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public interface OnGetAddressFinishedListener {
    void onNetworkError(String msg);
    void onSuccess(List<Address> addressList);
    void onUpdateAddressUsedSuccess();
    void onDeleteAddressSuccess(List<Address> ads);
    void onUpdateAddressListSuccess(List<Address> addressList);
}
