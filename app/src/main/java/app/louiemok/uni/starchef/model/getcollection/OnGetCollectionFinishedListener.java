package app.louiemok.uni.starchef.model.getcollection;

import java.util.List;

import app.louiemok.uni.starchef.model.Collection;

/**
 * Created by Administrator on 2017/10/18 0018.
 */

public interface OnGetCollectionFinishedListener {
    void onNetworkError(String msg);
    void onGetCollectionSuccess(List<Collection> ls);
    void onDeleteCollectionSuccess();
}
