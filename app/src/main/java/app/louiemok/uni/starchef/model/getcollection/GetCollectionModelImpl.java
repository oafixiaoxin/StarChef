package app.louiemok.uni.starchef.model.getcollection;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.louiemok.uni.starchef.model.Collection;
import app.louiemok.uni.starchef.model.Comment;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2017/10/18 0018.
 */

public class GetCollectionModelImpl implements GetCollectionModel {

    AsyncHttpClient client = null;

    private final static String GET_COLLECTION = "http://119.23.254.5/apistarchef/public/index" +
            ".php/api/v1/getCollection/";
    private final static String DELETE_COLLECTION = "";

    @Override
    public void getCollection (String uid, String type, final OnGetCollectionFinishedListener listener) {
        client = new AsyncHttpClient();
        client.get(GET_COLLECTION+uid+"/"+type, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess ( int status, Header[] headers, JSONObject response ) {
                try {
                    if ( status == 200 ) {
                        if ( response.getString("responseCode").equals("1") ) {
                            List<Collection> ls = new ArrayList<>();
                            JSONArray array = response.getJSONArray("responseBody");
                            for ( int i = 0 ; i < array.length() ; i++) {
                                JSONObject object = array.getJSONObject(i);
                                Collection collection = new Collection();
                                collection.setId(object.getInt("id"));
                                collection.setName(object.getString("name"));
                                ls.add(collection);
                            }
                            listener.onGetCollectionSuccess(ls);
                        }
                        else {
                            listener.onNetworkError("获取数据失败");
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
            public void onFailure ( int status, Header[] headers, String byes, Throwable
                    throwable ) {
                throwable.printStackTrace();
                listener.onNetworkError("获取数据失败");
            }
        });
    }

    @Override
    public void deleteCollection (String uid, List<Collection> collections,
                                  OnGetCollectionFinishedListener listener) {

    }

}
