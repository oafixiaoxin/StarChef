package app.louiemok.uni.starchef.presenter;

import com.loopj.android.http.RequestParams;

import app.louiemok.uni.starchef.model.Address;
import app.louiemok.uni.starchef.model.addaddress.AddAddressModel;
import app.louiemok.uni.starchef.model.addaddress.AddAddressModelImpl;
import app.louiemok.uni.starchef.model.addaddress.OnAddAddressFinishedListener;
import app.louiemok.uni.starchef.view.AddAddressView;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class AddAddressPresenterImpl implements AddAddressPresenter, OnAddAddressFinishedListener {

    private AddAddressModel addAddressModel;

    private AddAddressView addAddressView;

    public AddAddressPresenterImpl ( AddAddressView addAddressView ) {
        this.addAddressView = addAddressView;
        addAddressModel = new AddAddressModelImpl();
    }

    @Override
    public void addAddress(RequestParams params) {
        addAddressModel.addAddress(params, this);
    }

    @Override
    public void getAddress(String uid, int addressid) {
        addAddressModel.getAddress(uid, addressid, this);
    }

    @Override
    public void editAddress ( RequestParams params ) {
        addAddressModel.editAddress(params, this);
    }

    @Override
    public void onDestroy() {
        addAddressView = null;
    }

    @Override
    public void onSuccess(String msg) {
        addAddressView.onSuccess(msg);
    }

    @Override
    public void onNetworkError(String msg) {
        addAddressView.onNetworkError(msg);
    }

    @Override
    public void getAddressInfoSuccess(Address address) {
        addAddressView.onGetInfoSuccess(address);
    }
}
