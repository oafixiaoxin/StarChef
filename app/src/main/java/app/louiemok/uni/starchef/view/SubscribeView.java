package app.louiemok.uni.starchef.view;

import java.util.List;

import app.louiemok.uni.starchef.model.Shop;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public interface SubscribeView {

    void onNetworkError ( String msg );

    void success (List<Shop> shops);

}
