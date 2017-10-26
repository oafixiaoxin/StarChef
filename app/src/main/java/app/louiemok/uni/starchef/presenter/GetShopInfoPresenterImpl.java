package app.louiemok.uni.starchef.presenter;

import com.loopj.android.http.RequestParams;

import java.util.List;

import app.louiemok.uni.starchef.model.Comment;
import app.louiemok.uni.starchef.model.Shop;
import app.louiemok.uni.starchef.model.Voucher;
import app.louiemok.uni.starchef.model.getshopinfo.GetShopInfoModel;
import app.louiemok.uni.starchef.model.getshopinfo.GetShopInfoModelImpl;
import app.louiemok.uni.starchef.model.getshopinfo.OnGetShopInfoFinishedListener;
import app.louiemok.uni.starchef.view.ShopDetailView;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class GetShopInfoPresenterImpl implements GetShopInfoPresenter, OnGetShopInfoFinishedListener {
    private ShopDetailView shopDetailView;
    private GetShopInfoModel getShopInfoModel;

    public GetShopInfoPresenterImpl ( ShopDetailView shopDetailView) {
        this.shopDetailView = shopDetailView;
        this.getShopInfoModel = new GetShopInfoModelImpl();
    }

    @Override
    public void getShopInfo ( String shopid, String uid ) {
        getShopInfoModel.getShopInfo(shopid, uid, this);
    }

    @Override
    public void addCollection (RequestParams params) {
        getShopInfoModel.addCollection(params, this);
    }

    @Override
    public void cancelCollection ( RequestParams params ) {
        getShopInfoModel.cancelCollection(params, this);
    }

    @Override
    public void onDestroy () {
        shopDetailView = null;
    }

    @Override
    public void onNetworkError ( String msg ) {
        shopDetailView.onNetworkError(msg);
    }

    @Override
    public void onSuccess (Shop shop, List<Voucher> vouchers, List<Comment> comments, String
            isCollect) {
        shopDetailView.onSuccess(shop, vouchers, comments, isCollect);
    }

    @Override
    public void onAddCollectionSuccess (String msg) {
        shopDetailView.onAddCollectionSuccess(msg);
    }

    @Override
    public void onCancelCollectionSuccess ( String msg ) {
        shopDetailView.onCancelCollectionSuccess(msg);
    }

}
