package app.louiemok.uni.starchef.model.getcollection;

import java.util.List;

import app.louiemok.uni.starchef.model.Collection;

/**
 * Created by Administrator on 2017/10/18 0018.
 */

public interface GetCollectionModel {
    void getCollection(String uid, String type, OnGetCollectionFinishedListener listener);
    void deleteCollection(String uid, List<Collection> collections,
                          OnGetCollectionFinishedListener listener);
}
