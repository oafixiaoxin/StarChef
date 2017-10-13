package app.louiemok.uni.starchef.model.getshopinfo;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.louiemok.uni.starchef.model.Shop;
import app.louiemok.uni.starchef.model.Voucher;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class GetShopInfoModelImpl implements GetShopInfoModel{
    AsyncHttpClient client = null;
    String url = "http://119.23.254.5/apistarchef/public/index.php/api/v1/getShopInfo/";

    public void getShopInfo (String shopid, final OnGetShopInfoFinishedListener listener ) {
        client = new AsyncHttpClient();
        client.get(url+shopid, new JsonHttpResponseHandler() {
           @Override
            public void onSuccess ( int status, Header[] headers, JSONObject response ) {
                try {
                    if ( status == 200 ) {
                        if ( response.getString("responseCode").equals("1") ) {
                            List<Voucher> vouchers = new ArrayList<>();
                            JSONObject object = response.getJSONObject("responseBody")
                                    .getJSONObject("shopinfo");
                            JSONArray array = response.getJSONObject("responseBody").getJSONArray
                                    ("voucherinfo");
                            //shop信息
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
                            //voucher信息
                            for ( int i = 0 ; i < array.length() ; i++ ) {
                                JSONObject o = array.getJSONObject(i);
                                Voucher voucher = new Voucher();
                                voucher.setId(o.getInt("id"));
                                voucher.setTitle(o.getString("title"));
                                voucher.setShopid(o.getString("shopid"));
                                voucher.setUserange(o.getString("userange"));
                                voucher.setStarttime(o.getString("starttime"));
                                voucher.setEndtime(o.getString("endtime"));
                                voucher.setAttention(o.getString("attention"));
                                voucher.setPrice(o.getDouble("price"));
                                voucher.setSold(o.getInt("sold"));
                                vouchers.add(voucher);
                            }

                            listener.onSuccess(shop, vouchers);
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
            public void onFailure ( int status, Header[] headers, String bytes, Throwable
                   throwable ) {
                throwable.printStackTrace();
               listener.onNetworkError(throwable.getMessage());
           }
        });
    }

}
