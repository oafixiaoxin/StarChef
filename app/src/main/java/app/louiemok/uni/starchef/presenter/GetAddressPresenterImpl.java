package app.louiemok.uni.starchef.presenter;

import java.util.List;

import app.louiemok.uni.starchef.model.Address;
import app.louiemok.uni.starchef.model.getaddress.GetAddressModel;
import app.louiemok.uni.starchef.model.getaddress.GetAddressModelImpl;
import app.louiemok.uni.starchef.model.getaddress.OnGetAddressFinishedListener;
import app.louiemok.uni.starchef.view.AddressView;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public class GetAddressPresenterImpl implements GetAddressPresenter, OnGetAddressFinishedListener {
    private AddressView addressView;
    private GetAddressModel getAddressModel;

    public GetAddressPresenterImpl ( AddressView addressView ) {
        this.addressView = addressView;
        this.getAddressModel = new GetAddressModelImpl();
    }

    @Override
    public void getAddressList ( String uid ) {
        getAddressModel.getAddressList(uid, this);
    }

    @Override
    public void getAddressInfo ( String uid, int addressId ) {
        getAddressModel.getAddressInfo(uid, addressId, this);
    }

    @Override
    public void updateAddressUsed ( String uid, int addressId ) {
        getAddressModel.updateAddressUsed(uid, addressId, this);
    }

    @Override
    public void deleteAddress ( String uid, List<Address> addressList ) {
        getAddressModel.deleteAddress(uid, addressList, this);
    }

    @Override
    public void onDestroy () {
        addressView = null;
    }

    @Override
    public void onNetworkError(String msg) {
        addressView.onNetworkError(msg);
    }

    @Override
    public void onSuccess(List<Address> addressList) {
        addressView.onSuccess(addressList);
    }

    @Override
    public void onUpdateAddressUsedSuccess() {
        addressView.onUpdateAddressUsedSuccess();
    }

    @Override
    public void onDeleteAddressSuccess (List<Address> ads) {
        addressView.onDeleteAddressSuccess(ads);
    }

    @Override
    public void updateAddressList( String uid ) {
        getAddressModel.getAddressList(uid, this);
    }

    @Override
    public void onUpdateAddressListSuccess ( List<Address> as ) {
        addressView.updateAddressListSuccess(as);
    }
}
