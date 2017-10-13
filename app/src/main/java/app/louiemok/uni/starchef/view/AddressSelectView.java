package app.louiemok.uni.starchef.view;

import java.util.List;

import app.louiemok.uni.starchef.model.ProCityArea;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public interface AddressSelectView {
    void onNetworkError(String msg);
    void onSuccess(List<ProCityArea> ls);
}
