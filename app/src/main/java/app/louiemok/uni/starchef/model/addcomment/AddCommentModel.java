package app.louiemok.uni.starchef.model.addcomment;

import com.loopj.android.http.RequestParams;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public interface AddCommentModel {
    void addComment(RequestParams params, OnAddCommentFinishedListener listener);
}
