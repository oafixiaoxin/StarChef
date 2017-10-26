package app.louiemok.uni.starchef.model.addcomment;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class AddCommentModelImpl implements AddCommentModel {

    AsyncHttpClient client = null;
    private final static String ADD_COMMENT = "http://119.23.254.5/apistarchef/public/index" +
            ".php/api/v1/addComment";

    @Override
    public void addComment (RequestParams params, final OnAddCommentFinishedListener listener ) {
        client = new AsyncHttpClient();
        client.post(ADD_COMMENT, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess (int status, Header[] headers, JSONObject response) {
                try {
                    if ( status == 200 ) {
                        if ( response.getString("responseCode").equals("1") ) {
                            listener.onSuccess();
                        }
                        else {
                            listener.onNetworkError(response.getString("responseMsg"));
                        }
                    }
                    else {
                        listener.onNetworkError("网络错误");
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    listener.onNetworkError(ex.getMessage());
                }
            }

            @Override
            public void onFailure (int status, Header[] headers, String bytes, Throwable throwable) {
                throwable.printStackTrace();
                listener.onNetworkError(throwable.getMessage());
            }
        });
    }

}
