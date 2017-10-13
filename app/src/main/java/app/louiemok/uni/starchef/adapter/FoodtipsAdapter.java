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
 * Created by Administrator on 2017/9/23 0023.
 */

public class FoodtipsAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<HashMap<String, String>> data;
    LayoutInflater inflater = null;

    public FoodtipsAdapter ( Activity a, ArrayList<HashMap<String, String>> d ) {
        this.data = d;
        this.activity = a;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount () {
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
    public View getView ( int position, View convertView, ViewGroup parent ) {
        View view;
        ViewHolder viewHolder;
        if ( convertView == null ) {
            view = inflater.from(activity).inflate(R.layout.food_tips_cell, parent, false);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        return view;
    }

    class ViewHolder{

    }

}
