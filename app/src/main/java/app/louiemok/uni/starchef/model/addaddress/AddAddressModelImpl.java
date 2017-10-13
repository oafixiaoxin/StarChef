package app.louiemok.uni.starchef.model.addaddress;

import android.util.Log;

import com.google.gson.JsonNull;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import app.louiemok.uni.starchef.model.Address;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class AddAddressModelImpl implements AddAddressModel {

    AsyncHttpClient client = null;

    private final static String url = "http://119.23.254.5/apistarchef/public/index" +
            ".php/api/v1/addAddress";
    private final static String GET_ADDRESS_INFO = "http://119.23.254.5/apistarchef/public/index" +
            ".php/api/v1/getAddressInfo";
    private final static String EDIT_ADDRESS_INFO = "http://119.23.254.5/apistarchef/public/index" +
            ".php/api/v1/editAddressInfo";

    @Override
    public void addAddress(RequestParams params, final OnAddAddressFinishedListener listener) {
        client = new AsyncHttpClient();
        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess ( int status, Header[] headers, JSONObject response ) {
                try {
                    if ( status == 200 ) {
                        if ( response.getString("responseCode").equals("1") ) {
                            listener.onSuccess("新增地址成功");
                        }
                        else {
                            listener.onNetworkError("新增地址失败");
                        }
                    }
                    else {
                        listener.onNetworkError("网络错误");
                    }
                }
                catch ( Exception ex ) {
                    ex.printStackTrace();
                    listener.onNetworkError(ex.getMessage());
                }
            }

            @Override
            public void onFailure ( int status, Header[] headers, String bytes, Throwable
                    throwable ) {
                throwable.printStackTrace();
                listener.onNetworkError(throwable.getMessage());
            }
        });
    }

    @Override
    public void getAddress (String uid, int addressid, final OnAddAddressFinishedListener listener ) {
        client = new AsyncHttpClient();
        client.get(GET_ADDRESS_INFO+"/"+uid+"/"+addressid, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess ( int status, Header[] headers, JSONObject response ) {
                try {
                    if ( status == 200 ) {
                        if ( response.getString("responseCode").equals("1") ) {
                            JSONObject object = response.getJSONObject("responseBody");
                            Address address = new Address();
                            address.setId(object.getInt("id"));
                            address.setUid(object.getString("uid"));
                            address.setName(object.getString("name"));
                            address.setContact(object.getString("contact"));
                            address.setAddress(object.getString("address"));
                            address.setMailcode(object.getInt("mailcode"));
                            address.setIsuesd(object.getInt("isused"));
                            listener.getAddressInfoSuccess(address);
                        }
                    }
                    else {
                        listener.onNetworkError("网络错误");
                    }
                }
                catch ( Exception ex ) {
                    ex.printStackTrace();
                    listener.onNetworkError(ex.getMessage());
                }
            }

            @Override
            public void onFailure ( int status, Header[] headers, String bytes, Throwable throwable) {
                throwable.printStackTrace();
                listener.onNetworkError(throwable.getMessage());
            }
        });
    }

    @Override
    public void editAddress(RequestParams params, final OnAddAddressFinishedListener listener) {
        client = new AsyncHttpClient();
        client.post(EDIT_ADDRESS_INFO, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess ( int status, Header[] headers, JSONObject response ) {
                try {
                    if ( status == 200 ) {
                        if ( response.getString("responseCode").equals("1") ) {
                             listener.onSuccess("编辑地址成功");
                        }
                        else {
                            listener.onNetworkError("编辑地址失败");
                        }
                    }
                    else {
                        listener.onNetworkError("网络错误");
                    }
                }
                catch ( Exception ex ) {
                    ex.printStackTrace();
                    listener.onNetworkError(ex.getMessage());
                }
            }

            @Override
            public void onFailure ( int status, Header[] headers, String bytes, Throwable throwable) {
                throwable.printStackTrace();
                listener.onNetworkError(throwable.getMessage());
            }
        });
    }
}
