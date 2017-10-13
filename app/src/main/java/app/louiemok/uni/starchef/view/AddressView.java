package app.louiemok.uni.starchef.view;

import java.util.List;

import app.louiemok.uni.starchef.model.Address;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public interface AddressView {
    void onNetworkError(String msg);
    void onSuccess(List<Address> addressList);
    void updateAddressListSuccess(List<Address> as);
    void onUpdateAddressUsedSuccess();
    void onDeleteAddressSuccess(List<Address> addressList);
}
