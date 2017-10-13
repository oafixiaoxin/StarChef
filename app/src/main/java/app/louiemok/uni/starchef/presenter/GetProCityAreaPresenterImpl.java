package app.louiemok.uni.starchef.presenter;

import java.util.List;

import app.louiemok.uni.starchef.model.ProCityArea;
import app.louiemok.uni.starchef.model.getprocityarea.GetProCityAreaModel;
import app.louiemok.uni.starchef.model.getprocityarea.GetProCityAreaModelImpl;
import app.louiemok.uni.starchef.model.getprocityarea.OnGetProCityAreaFinishedListener;
import app.louiemok.uni.starchef.view.AddressSelectView;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class GetProCityAreaPresenterImpl implements GetProCityAreaPresenter, OnGetProCityAreaFinishedListener {

    private GetProCityAreaModel getProCityAreaModel;

    private AddressSelectView addressSelectView;

    public GetProCityAreaPresenterImpl ( AddressSelectView addressSelectView ) {
        this.addressSelectView = addressSelectView;
        this.getProCityAreaModel = new GetProCityAreaModelImpl();
    }

    @Override
    public void onNetworkError(String msg) {
        addressSelectView.onNetworkError(msg);
    }

    @Override
    public void onSuccess(List<ProCityArea> ls) {
        addressSelectView.onSuccess(ls);
    }

    @Override
    public void getProCityArea(int type, int pid) {
        getProCityAreaModel.getProCityArea(type, pid, this);
    }

    @Override
    public void onDestroy() {
        addressSelectView = null;
    }
}
