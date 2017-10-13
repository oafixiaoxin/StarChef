package app.louiemok.uni.starchef.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import app.louiemok.uni.starchef.R;

/**
 * Created by Administrator on 2017/9/25 0025.
 */

public class TodayNewAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<HashMap<String, String>> data;
    LayoutInflater inflater = null;

    public TodayNewAdapter ( Activity a, ArrayList<HashMap<String, String>> d ) {
        this.activity = a;
        this.data = d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount () {
        return data.size()==0?7:data.size();
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
            view = inflater.from(activity).inflate(R.layout.today_new_cell, parent, false);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        return view;
    }

    class ViewHolder {

    }

}
