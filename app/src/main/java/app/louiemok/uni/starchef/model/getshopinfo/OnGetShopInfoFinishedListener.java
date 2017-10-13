package app.louiemok.uni.starchef.model.getshopinfo;

import java.util.List;

import app.louiemok.uni.starchef.model.Shop;
import app.louiemok.uni.starchef.model.Voucher;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public interface OnGetShopInfoFinishedListener {
    void onNetworkError(String msg);
    void onSuccess(Shop shop, List<Voucher> vouchers);
}
