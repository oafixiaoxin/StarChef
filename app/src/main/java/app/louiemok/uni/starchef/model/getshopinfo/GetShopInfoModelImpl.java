package app.louiemok.uni.starchef.model.getshopinfo;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.louiemok.uni.starchef.model.Comment;
import app.louiemok.uni.starchef.model.Shop;
import app.louiemok.uni.starchef.model.Voucher;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class GetShopInfoModelImpl implements GetShopInfoModel{
    AsyncHttpClient client = null;
    private final static String url = "http://119.23.254.5/apistarchef/public/index" +
            ".php/api/v1/getShopInfo/";
    private final static String ADD_COLLECTION  = "http://119.23.254.5/apistarchef/public/index" +
            ".php/api/v1/addCollection";
    private final static String CANCEL_COLLECTION = "http://119.23.254.5/apistarchef/public/index" +
            ".php/api/v1/cancelCollection";

    public void getShopInfo (String shopid, String uid, final OnGetShopInfoFinishedListener listener
    ) {
        client = new AsyncHttpClient();
        client.get(url+shopid+"/"+uid, new JsonHttpResponseHandler() {
           @Override
            public void onSuccess ( int status, Header[] headers, JSONObject response ) {
                try {
                    if ( status == 200 ) {
                        if ( response.getString("responseCode").equals("1") ) {
                            List<Voucher> vouchers = new ArrayList<>();
                            List<Comment> comments = new ArrayList<>();
                            JSONObject object = response.getJSONObject("responseBody")
                                    .getJSONObject("shopinfo");
                            JSONArray array = response.getJSONObject("responseBody").getJSONArray
                                    ("voucherinfo");
                            JSONArray array1 = response.getJSONObject("responseBody")
                                    .getJSONArray("commentinfo");
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
                            //comment信息
                            for ( int i = 0 ; i< array1.length() ; i++ ) {
                                JSONObject o = array1.getJSONObject(i);
                                Comment comment = new Comment();
                                comment.setId(o.getInt("id"));
                                comment.setUid(o.getString("uid"));
                                comment.setType(o.getString("type"));
                                comment.setTime(o.getString("time"));
                                comment.setContent(o.getString("content"));
                                comment.setParentid(o.getInt("parentid"));
                                comment.setScancount(o.getInt("scancount"));
                                comment.setPic(o.getString("pic"));
                                comment.setTaste(o.getDouble("taste"));
                                comment.setEnviroment(o.getDouble("environment"));
                                comment.setService(o.getDouble("service"));
                                comment.setTargetid(o.getString("targetid"));
                                comment.setCostaver(o.getDouble("cost_aver"));
                                comment.setLike(o.getInt("like"));
                                comment.setNickname(o.getString("nickname"));
                                comment.setAvatar(o.getString("avatar"));
                                comment.setStar(o.getInt("star"));
                                comments.add(comment);
                            }
                            String isCollect = response.getJSONObject("responseBody").getString
                                    ("isCollect");
                            listener.onSuccess(shop, vouchers, comments, isCollect);
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
                    listener.onNetworkError("获取数据失败");
                }
           }

           @Override
            public void onFailure ( int status, Header[] headers, String bytes, Throwable
                   throwable ) {
                throwable.printStackTrace();
               listener.onNetworkError("获取数据失败");
           }
        });
    }

    @Override
    public void addCollection (RequestParams params, final OnGetShopInfoFinishedListener listener) {
        client = new AsyncHttpClient();
        client.post(ADD_COLLECTION, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess ( int status, Header[] headers, JSONObject response ) {
                try {
                    if ( status == 200 ) {
                        if ( response.getString("responseCode").equals("1") ) {
                            listener.onAddCollectionSuccess("收藏成功");
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
    public void cancelCollection (RequestParams params, final OnGetShopInfoFinishedListener listener ) {
        client = new AsyncHttpClient();
        client.post(CANCEL_COLLECTION, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess ( int status, Header[] headers, JSONObject response ) {
                try {
                    if ( status == 200 ) {
                        if ( response.getString("responseCode").equals("1") ) {
                            listener.onCancelCollectionSuccess("取消收藏成功");
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
