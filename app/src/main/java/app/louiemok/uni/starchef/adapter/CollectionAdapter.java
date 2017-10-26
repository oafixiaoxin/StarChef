package app.louiemok.uni.starchef.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.model.Collection;

/**
 * Created by Administrator on 2017/10/18 0018.
 */

public class CollectionAdapter extends BaseAdapter {

    private List<Collection> data;
    private Activity activity;
    private LayoutInflater inflater = null;

    public CollectionAdapter ( Activity a, List<Collection> d ) {
        this.data = d;
        this.activity = a;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
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
    public View getView (int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if ( convertView == null ) {
            view = inflater.from(activity).inflate(R.layout.collection_shop_cell, parent, false);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        return view;
    }

    static class ViewHolder {

    }

}
