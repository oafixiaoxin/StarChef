package app.louiemok.uni.starchef.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.model.Shop;
import app.louiemok.uni.starchef.seledefine.StarView;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class SubcribeAdapter extends BaseAdapter {

    Activity activity;
    List<Shop> data;
    LayoutInflater inflater = null;

    public SubcribeAdapter ( Activity a, List<Shop> d ) {
        this.activity = a;
        this.data = d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount () {
        return data.size();
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
            view = inflater.from(activity).inflate(R.layout.subcribe_cell, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ll_star_view = view.findViewById(R.id.ll_star_view);
            viewHolder.tv_shop_name = view.findViewById(R.id.tv_shop_name);
            viewHolder.tv_shop_type = view.findViewById(R.id.tv_shop_type);
            viewHolder.ll_new = view.findViewById(R.id.ll_new);
            viewHolder.ll_act = view.findViewById(R.id.ll_act);
            viewHolder.tv_new = view.findViewById(R.id.tv_new);
            viewHolder.tv_act = view.findViewById(R.id.tv_act);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        Shop shop = data.get(position);

        viewHolder.tv_shop_name.setText(shop.getName());
        viewHolder.tv_shop_type.setText(shop.getType());
        viewHolder.ll_star_view.setStar(shop.getStar(), R.drawable.star_normal,
                R.drawable.star_cover, 20, 10);

        return view;
    }

    class ViewHolder {
        StarView ll_star_view;
        TextView tv_shop_name;
        TextView tv_shop_type;
        LinearLayout ll_new;
        LinearLayout ll_act;
        TextView tv_new;
        TextView tv_act;
    }
}
