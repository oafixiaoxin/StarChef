package app.louiemok.uni.starchef.model.getshoplist;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.louiemok.uni.starchef.model.Shop;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class GetShopListModelImpl implements GetShopListModel {
    AsyncHttpClient client = null;
    String url = "http://119.23.254.5/apistarchef/public/index.php/api/v1/getShopList";

    public void getShopList (final OnGetShopListFinishedListener listener ) {
        client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess ( int status, Header[] headers, JSONObject response ) {
                try {
                    if ( status == 200 ) {
                        if ( response.getString("responseCode").equals("1") ) {
                            List<Shop> list = new ArrayList<>();
                            JSONArray array = response.getJSONArray("responseBody");
                            for ( int i = 0 ; i < array.length() ; i++ ) {
                                JSONObject object = array.getJSONObject(i);
                                Shop shop = new Shop();
                                shop.setId(object.getInt("id"));
                                shop.setShopid(object.getString("shopid"));
                                shop.setName(object.getString("name"));
                                shop.setType(object.getString("type"));
                                shop.setAddress(object.getString("address"));
                                shop.setContact(object.getString("contact"));
                                shop.setOpentime(object.getString("opentime"));
                                shop.setEndtime(object.getString("endtime"));
                                shop.setConfig(object.getString("config"));
                                shop.setStar(object.getInt("star"));
                                shop.setPic(new String[] {"1", "2", "3"});
                                shop.setStatus(object.getInt("status"));
                                shop.setVerifytime(object.getString("verifytime"));
                                shop.setPeopleincharge(object.getString("peopleincharge"));
                                shop.setChargecontact(object.getString("chargecontact"));
                                shop.setPassword(object.getString("password"));
                                list.add(shop);
                            }
                            listener.success(list);
                        }
                        else {
                            listener.networkError(response.getString("responseMsg"));
                        }
                    }
                    else {
                        listener.networkError("网络错误");
                    }
                }
                catch ( Exception ex ) {
                    ex.printStackTrace();
                    listener.networkError(ex.getMessage());
                }
            }

            @Override
            public void onFailure ( int status, Header[] headers, String bytes, Throwable
                    throwable ) {
                throwable.printStackTrace();
                listener.networkError(throwable.getMessage());
            }
        });
    }

}
