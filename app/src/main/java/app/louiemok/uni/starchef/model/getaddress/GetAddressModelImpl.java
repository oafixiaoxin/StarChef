package app.louiemok.uni.starchef.model.getaddress;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.louiemok.uni.starchef.model.Address;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public class GetAddressModelImpl implements GetAddressModel{
    private AsyncHttpClient client = null;
    private final static String GET_ADDRESS_LIST = "http://119.23.254.5/apistarchef/public/index" +
            ".php/api/v1/getAddressList/";
    private final static String UPDATE_ADDRESS_USED =
            "http://119.23.254.5/apistarchef/public/index.php/api/v1/setAddressUsed";
    private final static String DELETE_ADDRESS = "http://119.23.254.5/apistarchef/public/index" +
            ".php/api/v1/deleteAddress";

    @Override
    public void getAddressList(String uid, final OnGetAddressFinishedListener listener ) {
        client = new AsyncHttpClient();
        client.get(GET_ADDRESS_LIST+uid, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess ( int status, Header[] headers, JSONObject response ) {
                try {
                    if ( status == 200 ) {
                        if ( response.getString("responseCode").equals("1") ) {
                            List<Address> ls = new ArrayList<>();
                            JSONArray array = response.getJSONArray("responseBody");
                            for ( int i = 0 ; i < array.length() ; i++ ) {
                                JSONObject object = array.getJSONObject(i);
                                Address address = new Address();
                                address.setId(object.getInt("id"));
                                address.setUid(object.getString("uid"));
                                address.setName(object.getString("name"));
                                address.setContact(object.getString("contact"));
                                address.setAddress(object.getString("address"));
                                address.setMailcode(object.getInt("mailcode"));
                                address.setIsuesd(object.getInt("isused"));
                                address.setIsselected(0);
                                ls.add(address);
                            }
                            listener.onSuccess(ls);
                        }
                        else {
                            listener.onNetworkError(response.getString("responseMsg"));
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
    public void getAddressInfo ( String uid, int addressId, OnGetAddressFinishedListener listener
    ) {
        
    }

    @Override
    public void updateAddressUsed (String uid, int addressId, final OnGetAddressFinishedListener
            listener ) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("addressid", addressId);
        client = new AsyncHttpClient();
        client.post(UPDATE_ADDRESS_USED, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess ( int status, Header[] headers, JSONObject response ) {
                try {
                    if ( status == 200 ) {
                        if ( response.getString("responseCode").equals("1") ) {
                            listener.onUpdateAddressUsedSuccess();
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
    public void deleteAddress (String uid, List<Address> addressList,
                               final OnGetAddressFinishedListener listener) {
        if ( formAddressStr(addressList).length() == 0 ) {
            listener.onNetworkError("请选择地址");
        }
        else {
            RequestParams params = new RequestParams();
            params.put("uid", uid);
            params.put("addressStr", formAddressStr(addressList));
            client = new AsyncHttpClient();
            client.post(DELETE_ADDRESS, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess ( int status, Header[] headers, JSONObject response ) {
                    try {
                        if ( status == 200 ) {
                            if ( response.getString("responseCode").equals("1") ) {
                                Log.e("deleteAddress", response.toString());
                                List<Address> ls = new ArrayList<>();
                                JSONArray array = response.getJSONArray("responseBody");
                                for ( int i = 0 ; i < array.length() ; i++ ) {
                                    JSONObject object = array.getJSONObject(i);
                                    Address address = new Address();
                                    address.setId(object.getInt("id"));
                                    address.setUid(object.getString("uid"));
                                    address.setName(object.getString("name"));
                                    address.setContact(object.getString("contact"));
                                    address.setAddress(object.getString("address"));
                                    address.setMailcode(object.getInt("mailcode"));
                                    address.setIsuesd(object.getInt("isused"));
                                    address.setIsselected(0);
                                    ls.add(address);
                                }
                                listener.onDeleteAddressSuccess(ls);
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
                        throwable) {
                    throwable.printStackTrace();
                    listener.onNetworkError(throwable.getMessage());
                }
            });
        }
    }

    /*
    * 将符合条件的地址id拼接为字符串
    * */
    private String formAddressStr ( List<Address> addressList ) {
        String str = "";
        for ( Address address : addressList ) {
            if ( address.getIsselected() == 2 ) {
                str += address.getId();
                str += ",";
            }
        }
        if ( str.length() > 0 ) {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }
}
