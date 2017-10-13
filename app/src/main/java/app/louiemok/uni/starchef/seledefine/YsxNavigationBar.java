package app.louiemok.uni.starchef.seledefine;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.louiemok.uni.starchef.R;

/**
 * Created by Administrator on 2017/8/30 0030.
 */

public class YsxNavigationBar extends RelativeLayout implements View.OnClickListener{

    ImageButton ibtn_back;
    ImageButton ibtn_right;
    TextView tv_title;
    TextView tv_right;

    public YsxNavigationBar ( Context context ) {
        super(context);
    }

    public YsxNavigationBar (Context context, AttributeSet attr ) {
        super(context, attr);
        View view = LayoutInflater.from(context).inflate(R.layout.ysx_navigation_bar, this, true);
        ibtn_back = view.findViewById(R.id.ibtn_back);
        ibtn_right = view.findViewById(R.id.ibtn_right);
        tv_title = view.findViewById(R.id.tv_title);
        tv_right = view.findViewById(R.id.tv_right);

        ibtn_back.setOnClickListener(this);
        ibtn_right.setOnClickListener(this);
        tv_right.setOnClickListener(this);
    }

    public void setLeftSrc ( int src ) {
        ibtn_back.setBackgroundResource(src);
    }

    public void setTitle ( String title ) {
        tv_title.setText(title);
    }

    public void showRightSrc ( int src ) {
        ibtn_right.setBackgroundResource(src);
    }

    public void showRightText ( String text ) {
        tv_right.setText(text);
    }

    public void hideRightText () {
        tv_right.setVisibility(GONE);
    }

    public void hideRightSrc () {
        ibtn_right.setVisibility(GONE);
    }

    public void hideLeftSrc () {
        ibtn_back.setVisibility(GONE);
    }

    public void setRightText ( String t ) {
        tv_right.setText(t);
    }

    public void setRightTextColor ( int color ) {
        tv_right.setTextColor(getResources().getColor(color));
    }

    public String getRightText () {
        return tv_right.getText().toString();
    }

    private ClickCallback callback;

    public void setClickCallback ( ClickCallback callback ) {
        this.callback = callback;
    }

    public static interface ClickCallback {
        void onLeftClick();
        void onRightBtnClick();
        void onRightTextClick();
    }

    public void onClick ( View view ) {
        switch (view.getId()) {
            case R.id.ibtn_back:
                callback.onLeftClick();
                break;
            case R.id.ibtn_right:
                callback.onRightBtnClick();
                break;
            case R.id.tv_right:
                callback.onRightTextClick();
                break;
            default:
                break;
        }
    }

}
;