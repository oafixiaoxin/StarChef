package app.louiemok.uni.starchef.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.model.Comment;
import app.louiemok.uni.starchef.seledefine.CircleImageView;
import app.louiemok.uni.starchef.seledefine.DateUtils;
import app.louiemok.uni.starchef.seledefine.StarView;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class NetCommandAdapter extends BaseAdapter {

    Activity activity;
    List<Comment> data;
    LayoutInflater inflater = null;

    public NetCommandAdapter ( Activity a, List<Comment> d ) {
        this.activity = a;
        this.data = d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount () {
//        return data.size();
        return 2;
    }

    @Override
    public long getItemId ( int position ) {
        return position;
    }

    @Override
    public Object getItem ( int position ) {
        return position;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent ) {
        View view;
        ViewHolder viewHolder;
        if ( convertView == null ) {
            view = inflater.from(activity).inflate(R.layout.net_comment_cell, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ll_star_view = view.findViewById(R.id.ll_star_view);
            viewHolder.iv_avatar = view.findViewById(R.id.iv_avatar);
            viewHolder.tv_usernickname = view.findViewById(R.id.tv_usernickname);
            viewHolder.tv_comment_date = view.findViewById(R.id.tv_comment_date);
            viewHolder.tv_comment_content = view.findViewById(R.id.tv_comment_content);
            viewHolder.tv_scan_count = view.findViewById(R.id.tv_scan_count);
            viewHolder.tv_like = view.findViewById(R.id.tv_like);
            viewHolder.ll_net_comment_img = view.findViewById(R.id.ll_net_comment_img);
            viewHolder.ll_btn_like = view.findViewById(R.id.ll_btn_like);
            viewHolder.ll_btn_comment = view.findViewById(R.id.ll_btn_comment);
            viewHolder.iv_like = view.findViewById(R.id.iv_like);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        Comment comment = data.get(position);
        viewHolder.ll_star_view.setStar(comment.getStar(), R.drawable.star_normal, R.drawable
                .star_cover, 20, 10);
        if ( comment.getAvatar().equals("") ) {
            Log.e("avatar1", comment.getAvatar());
            viewHolder.iv_avatar.setImageResource(R.mipmap.logo);
        }
        else {
            Log.e("avatar2", comment.getAvatar());
            Glide.with(activity).load(comment.getAvatar()).into(viewHolder.iv_avatar);
        }
        viewHolder.tv_usernickname.setText(comment.getNickname());
        viewHolder.tv_comment_date.setText(DateUtils.getTime(comment.getTime(), "MM月dd日"));
        viewHolder.tv_comment_content.setText(comment.getContent());
        viewHolder.ll_net_comment_img.setVisibility(View.GONE);
        viewHolder.tv_scan_count.setText(String.valueOf(comment.getScancount()));
        viewHolder.tv_like.setText(String.valueOf(comment.getLike()));

        return view;
    }

    class ViewHolder {
        StarView ll_star_view;
        CircleImageView iv_avatar;
        TextView tv_usernickname;
        TextView tv_comment_date;
        TextView tv_comment_content;
        TextView tv_scan_count;
        TextView tv_like;
        LinearLayout ll_net_comment_img;
        LinearLayout ll_btn_like;
        LinearLayout ll_btn_comment;
        ImageView iv_like;
    }

}
