package app.louiemok.uni.starchef.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.louiemok.uni.starchef.R;

/**
 * Created by Administrator on 2017/9/29 0029.
 */

public class ShopConfigAdapter extends BaseAdapter {

    Activity activity;
    String[] data;
    LayoutInflater inflater = null;

    public ShopConfigAdapter ( Activity a, String[] d ) {
        this.activity = a;
        this.data = d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount () {
        return data.length;
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
    public View getView ( int position, View convertView, ViewGroup parent ) {
        View view;
        ViewHolder viewHolder;
        if ( convertView == null ) {
            view = inflater.from(activity).inflate(R.layout.shop_config_cell, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.iv_shop_config_img = view.findViewById(R.id.iv_shop_config_img);
            viewHolder.tv_shop_config_title = view.findViewById(R.id.tv_shop_config_title);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.tv_shop_config_title.setText(data[position]);
        return view;
    }

    class ViewHolder {
        ImageView iv_shop_config_img;
        TextView tv_shop_config_title;
    }

}
