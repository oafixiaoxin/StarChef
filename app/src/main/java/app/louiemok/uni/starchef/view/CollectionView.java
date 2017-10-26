package app.louiemok.uni.starchef.view;

import java.util.List;

import app.louiemok.uni.starchef.model.Collection;

/**
 * Created by Administrator on 2017/10/18 0018.
 */

public interface CollectionView {
    void onGetCollectionSuccess(List<Collection> ls);
    void onNetworkError(String msg);
    void onDeleteCollectionSuccess();
}
