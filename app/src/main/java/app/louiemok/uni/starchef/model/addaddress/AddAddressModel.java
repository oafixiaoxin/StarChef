package app.louiemok.uni.starchef.model.addaddress;

import com.loopj.android.http.RequestParams;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public interface AddAddressModel {
    void addAddress(RequestParams params, OnAddAddressFinishedListener listener);
    void getAddress(String uid, int addressid, OnAddAddressFinishedListener listener);
    void editAddress(RequestParams params, OnAddAddressFinishedListener listener);
}
