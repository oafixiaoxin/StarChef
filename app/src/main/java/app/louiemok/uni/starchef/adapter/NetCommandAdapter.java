package app.louiemok.uni.starchef.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;

import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.seledefine.StarView;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class NetCommandAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<HashMap<String, String>> data;
    LayoutInflater inflater = null;

    public NetCommandAdapter ( Activity a, ArrayList<HashMap<String, String>> d ) {
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
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.ll_star_view.setStar(4, R.drawable.star_normal, R.drawable.star_cover, 20, 10);

        return view;
    }

    class ViewHolder {
        StarView ll_star_view;
    }

}
