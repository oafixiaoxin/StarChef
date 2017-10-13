package app.louiemok.uni.starchef.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

import app.louiemok.uni.starchef.R;

/**
 * Created by Administrator on 2017/9/25 0025.
 */

public class TodayMainAdapter extends RecyclerView.Adapter<TodayMainAdapter.ViewHolder> {

    private ArrayList<HashMap<String, String>> data;

    private View view;

    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_today_new_img;
        public ViewHolder ( View v ) {
            super(v);
            iv_today_new_img = v.findViewById(R.id.iv_today_new_img);
            int width = ((Activity)iv_today_new_img.getContext()).getWindowManager()
                    .getDefaultDisplay().getWidth();
            ViewGroup.LayoutParams layoutParams = iv_today_new_img.getLayoutParams();
            layoutParams.height = (int)(150+Math.random()*300);
            iv_today_new_img.setLayoutParams(layoutParams);
        }

    }

    public TodayMainAdapter ( Context context, ArrayList<HashMap<String, String>> d ) {
        this.data = d;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType ) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_new_cell, parent, false);
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
