package app.louiemok.uni.starchef.model.updateuserinfo;

/**
 * Created by Administrator on 2017/8/30 0030.
 */

public interface UserModel {
    void getUserInfo(int userId, OnUpdateUserInfoListener listener);
}
