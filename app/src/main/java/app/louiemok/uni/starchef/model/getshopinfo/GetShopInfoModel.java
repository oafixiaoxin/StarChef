package app.louiemok.uni.starchef.model.getshopinfo;

import com.loopj.android.http.RequestParams;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public interface GetShopInfoModel {
    void getShopInfo(String shopid, String uid, OnGetShopInfoFinishedListener listener);
    void addCollection(RequestParams params, OnGetShopInfoFinishedListener listener);
    void cancelCollection(RequestParams params, OnGetShopInfoFinishedListener listener);
}
