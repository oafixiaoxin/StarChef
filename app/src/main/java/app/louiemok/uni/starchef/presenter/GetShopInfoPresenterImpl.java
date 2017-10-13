package app.louiemok.uni.starchef.presenter;

import java.util.List;

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
    public void getShopInfo ( String shopid ) {
        getShopInfoModel.getShopInfo(shopid, this);
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
    public void onSuccess ( Shop shop, List<Voucher> vouchers ) {
        shopDetailView.onSuccess(shop, vouchers);
    }
}
