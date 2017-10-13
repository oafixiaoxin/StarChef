package app.louiemok.uni.starchef.model.getshoplist;

import java.util.List;

import app.louiemok.uni.starchef.model.Shop;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public interface OnGetShopListFinishedListener {
    void networkError ( String msg );
    void success (List<Shop> shops);
}
