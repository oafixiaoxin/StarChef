package app.louiemok.uni.starchef.model.addcomment;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public interface OnAddCommentFinishedListener {
    void onSuccess();
    void onNetworkError(String msg);
}
