package app.louiemok.uni.starchef.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import app.louiemok.uni.starchef.R;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private ArrayList<HashMap<String, String>> data;

    private View view;

    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder ( View v ) {
            super(v);
        }

    }

    public OrderAdapter ( Context context, ArrayList<HashMap<String, String>> d ) {
        this.data = d;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder ( ViewGroup parent, int viewType ) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder ( ViewHolder holder, int position ) {

    }

    @Override
    public int getItemCount () {
        return data.size();
    }

}
