package app.louiemok.uni.starchef.presenter;

import android.util.Log;

import java.util.List;

import app.louiemok.uni.starchef.model.Collection;
import app.louiemok.uni.starchef.model.getcollection.GetCollectionModel;
import app.louiemok.uni.starchef.model.getcollection.GetCollectionModelImpl;
import app.louiemok.uni.starchef.model.getcollection.OnGetCollectionFinishedListener;
import app.louiemok.uni.starchef.view.CollectionView;

/**
 * Created by Administrator on 2017/10/18 0018.
 */

public class GetCollectionPresenterImpl implements GetCollectionPresenter,
        OnGetCollectionFinishedListener {

    private CollectionView collectionView;

    private GetCollectionModel getCollectionModel;

    public GetCollectionPresenterImpl ( CollectionView collectionView ) {
        this.collectionView = collectionView;
        getCollectionModel = new GetCollectionModelImpl();
    }

    @Override
    public void onNetworkError(String msg) {
        this.collectionView.onNetworkError(msg);
    }

    @Override
    public void getCollection(String uid, String type) {
        getCollectionModel.getCollection(uid, type, this);
    }

    @Override
    public void deleteCollection(String uid, List<Collection> collections) {
        getCollectionModel.deleteCollection(uid, collections, this);
    }

    @Override
    public void onDeleteCollectionSuccess() {
        collectionView.onDeleteCollectionSuccess();
    }

    @Override
    public void onGetCollectionSuccess(List<Collection> ls) {
        collectionView.onGetCollectionSuccess(ls);
    }

    @Override
    public void onDestroy() {
        collectionView = null;
    }
}
