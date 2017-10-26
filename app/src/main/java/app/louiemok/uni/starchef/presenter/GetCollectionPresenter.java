package app.louiemok.uni.starchef.presenter;

import java.util.List;

import app.louiemok.uni.starchef.model.Collection;

/**
 * Created by Administrator on 2017/10/18 0018.
 */

public interface GetCollectionPresenter {
    void getCollection(String uid, String type);
    void deleteCollection(String uid, List<Collection> collections);
    void onDestroy();
}
