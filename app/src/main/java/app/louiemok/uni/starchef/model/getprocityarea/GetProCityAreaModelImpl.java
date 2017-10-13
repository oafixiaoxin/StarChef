package app.louiemok.uni.starchef.model.getprocityarea;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.louiemok.uni.starchef.model.ProCityArea;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class GetProCityAreaModelImpl implements GetProCityAreaModel {

    AsyncHttpClient client = null;

    private final static String GET_PROVINCE = "http://119.23.254.5/apistarchef/public/index" +
            ".php/api/v1/getProvince";

    private final static String GET_CITY = "http://119.23.254.5/apistarchef/public/index" +
            ".php/api/v1/getCity/";

    private final static String GET_AREA = "http://119.23.254.5/apistarchef/public/index" +
            ".php/api/v1/getArea/";

    @Override
    public void getProCityArea (int type, int pid, final OnGetProCityAreaFinishedListener listener ) {
        String url = "";
        switch (type) {
            case 1:
                url = GET_PROVINCE;
                break;
            case 2:
                url = GET_CITY+pid;
                break;
            case 3:
                url = GET_AREA+pid;
                break;
        }
        client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess ( int status, Header[]  headers, JSONObject response ) {
                try {
                    if ( status == 200 ) {
                        if ( response.getString("responseCode").equals("1") ) {
                            JSONArray array = response.getJSONArray("responseBody");
                            List<ProCityArea> ls = new ArrayList<>();
                            for ( int i = 0 ; i < array.length() ; i++ ) {
                                JSONObject object = array.getJSONObject(i);
                                ProCityArea proCityArea = new ProCityArea();
                                proCityArea.setId(object.getInt("id"));
                                proCityArea.setTitle(object.getString("title"));
                                ls.add(proCityArea);
                            }
                            listener.onSuccess(ls);
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
