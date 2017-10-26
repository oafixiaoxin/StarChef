package app.louiemok.uni.starchef.presenter;

import com.loopj.android.http.RequestParams;

import app.louiemok.uni.starchef.model.addcomment.AddCommentModel;
import app.louiemok.uni.starchef.model.addcomment.AddCommentModelImpl;
import app.louiemok.uni.starchef.model.addcomment.OnAddCommentFinishedListener;
import app.louiemok.uni.starchef.view.AddCommentView;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class AddCommentPresenterImpl implements AddCommentPresenter, OnAddCommentFinishedListener {

    private AddCommentModel addCommentModel;

    private AddCommentView addCommentView;

    public AddCommentPresenterImpl ( AddCommentView addCommentView ) {
        this.addCommentView = addCommentView;
        this.addCommentModel = new AddCommentModelImpl();
    }

    @Override
    public void addComment(RequestParams params) {
        addCommentModel.addComment(params, this);
    }

    @Override
    public void onDestroy() {
        addCommentView = null;
    }

    @Override
    public void onNetworkError(String msg) {
        addCommentView.onNetworkError(msg);
    }

    @Override
    public void onSuccess() {
        addCommentView.onSuccess();
    }
}
