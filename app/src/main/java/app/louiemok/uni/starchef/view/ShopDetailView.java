package app.louiemok.uni.starchef.view;

import java.util.List;

import app.louiemok.uni.starchef.model.Comment;
import app.louiemok.uni.starchef.model.Shop;
import app.louiemok.uni.starchef.model.Voucher;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public interface ShopDetailView {

    void onNetworkError(String msg);

    void onSuccess(Shop shop, List<Voucher> voucherList, List<Comment> commentList,
                   String isCollect);

    void onAddCollectionSuccess(String msg);

    void onCancelCollectionSuccess(String msg);

}
