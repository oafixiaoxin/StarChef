package app.louiemok.uni.starchef.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.readystatesoftware.viewbadger.BadgeView;

import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.model.User;
import app.louiemok.uni.starchef.presenter.UpdateUserInfoPresenter;
import app.louiemok.uni.starchef.presenter.UpdateUserInfoPresenterImpl;

public class PersonalFragment extends Fragment implements View.OnClickListener, PersonalView {

    private View view;
    ImageButton ibtn_setting;
    ImageButton ibtn_notification;
    ImageView iv_avatar;
    TextView tv_usernickname;
    LinearLayout ll_order;
    LinearLayout ll_shopping_cart;
    LinearLayout ll_collection;
    LinearLayout ll_comment;
    LinearLayout ll_dianzan;
    BadgeView badgeView;

    UpdateUserInfoPresenter updateUserInfoPresenter;

    static String imgPrefix = "http://119.23.254.5/blog_img/";
    String nickname;

    private final static int UPDATE_INFO = 1;

    private Handler handler = new Handler(){
        public void handleMessage ( Message message ) {
            switch (message.what) {
                case UPDATE_INFO:
                    if ( nickname.equals("") ) {
                        tv_usernickname.setText("登录");
                        ibtn_setting.setVisibility(View.GONE);
                    }
                    else {
                        tv_usernickname.setText(nickname);
                        ibtn_setting.setVisibility(View.VISIBLE);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public PersonalFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        return view;
    }

    @Override
    public void onActivityCreated ( Bundle savedInstanceState ) {
        super.onActivityCreated(savedInstanceState);
        initElements();
    }

    private void initElements () {
        ibtn_setting = view.findViewById(R.id.ibtn_setting);
        ibtn_setting.setOnClickListener(this);
        ibtn_notification = view.findViewById(R.id.ibtn_notification);
        ibtn_notification.setOnClickListener(this);
        iv_avatar = view.findViewById(R.id.iv_avatar);
        iv_avatar.setOnClickListener(this);
        tv_usernickname = view.findViewById(R.id.tv_usernickname);
        tv_usernickname.setOnClickListener(this);
        ll_order = view.findViewById(R.id.ll_order);
        ll_order.setOnClickListener(this);
        ll_shopping_cart = view.findViewById(R.id.ll_shopping_cart);
        ll_shopping_cart.setOnClickListener(this);
        ll_collection = view.findViewById(R.id.ll_collection);
        ll_collection.setOnClickListener(this);
        ll_comment = view.findViewById(R.id.ll_comment);
        ll_comment.setOnClickListener(this);
        ll_dianzan = view.findViewById(R.id.ll_dianzan);
        ll_dianzan.setOnClickListener(this);

        badgeView = new BadgeView(getActivity(), ibtn_notification);
        badgeView.setText("1");
        badgeView.setTextSize(8);
        badgeView.setHeight(dip2px(getActivity(), 15));
        badgeView.setWidth(dip2px(getActivity(), 15));
        badgeView.setGravity(Gravity.CENTER);
        badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        badgeView.show();
    }

    public static PersonalFragment newInstance ( String title ) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        PersonalFragment fragment = new PersonalFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onClick ( View view ) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ibtn_setting:
                intent = new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.ibtn_notification:
                badgeView.toggle(true);
                break;
            case R.id.iv_avatar:
                break;
            case R.id.tv_usernickname:
                if ( nickname.equals("")) {
                    //跳转至登录页面
                    intent = new Intent(getActivity(), LoginActivity.class);
                    getActivity().startActivity(intent);
                }
                break;
            case R.id.ll_order:
                intent = new Intent(getActivity(), OrderActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.ll_shopping_cart:
                intent = new Intent(getActivity(), ShoppingCartActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.ll_collection:
                break;
            case R.id.ll_comment:
                break;
            case R.id.ll_dianzan:
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume () {
        super.onResume();
        SharedPreferences sp = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        nickname = sp.getString("nickname", "");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = UPDATE_INFO;
                handler.sendMessage(message);
            }
        }).start();
    }

    @Override
    public void onNetworkError ( String errorMsg ) {
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserIdError () {
        Log.e("PersonalFragment", "onUserIdError");
    }

    @Override
    public void updateUserInfo ( User user ) {
//        tv_usernickname.setText(user.getName());
//        if ( !TextUtils.isEmpty(user.getFilename()) ) {
//            Glide.with(getActivity()).load(imgPrefix+user.getFilename()).into(iv_avatar);
//        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
