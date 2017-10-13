package app.louiemok.uni.starchef.presenter;

import java.util.List;

import app.louiemok.uni.starchef.model.Shop;
import app.louiemok.uni.starchef.model.getshoplist.GetShopListModel;
import app.louiemok.uni.starchef.model.getshoplist.GetShopListModelImpl;
import app.louiemok.uni.starchef.model.getshoplist.OnGetShopListFinishedListener;
import app.louiemok.uni.starchef.view.SubscribeView;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class GetShopListPresenterImpl implements GetShopListPresenter, OnGetShopListFinishedListener {
    private SubscribeView subscribeView;
    private GetShopListModel getShopListModel;

    public GetShopListPresenterImpl ( SubscribeView subscribeView ) {
        this.subscribeView = subscribeView;
        this.getShopListModel = new GetShopListModelImpl();
    }

    public void getShopList () {
        getShopListModel.getShopList(this);
    }

    public void onDestroy () {
        subscribeView = null;
    }

    public void networkError ( String msg ) {
        subscribeView.onNetworkError(msg);
    }

    public void success (List<Shop> shops) {
        subscribeView.success(shops);
    }
}
