package app.louiemok.uni.starchef.presenter;

import com.loopj.android.http.RequestParams;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public interface AddCommentPresenter {
    void addComment(RequestParams params);
    void onDestroy();
}
