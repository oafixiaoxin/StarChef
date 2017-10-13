package app.louiemok.uni.starchef.presenter;

import app.louiemok.uni.starchef.model.getshopinfo.OnGetShopInfoFinishedListener;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public interface GetShopInfoPresenter {
    void getShopInfo(String shopid);
    void onDestroy();
}
