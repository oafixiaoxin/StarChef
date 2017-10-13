package app.louiemok.uni.starchef.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.model.ProCityArea;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class ProCityAreaAdapter extends RecyclerView.Adapter<ProCityAreaAdapter.ViewHolder> {

    private List<ProCityArea> data;

    private Context context;

    private View view;

    private MyItemClickListener listener = null;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_pro_city_area;

        public ViewHolder ( View v ) {
            super(v);
            tv_pro_city_area = v.findViewById(R.id.tv_pro_city_area);
        }

    }

    public ProCityAreaAdapter ( Context context, List<ProCityArea> d ) {
        this.data = d;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.pro_city_area_cell, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, (int)view.getTag());
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProCityArea proCityArea = data.get(position);
        holder.tv_pro_city_area.setText(proCityArea.getTitle());
        holder.itemView.setTag(position);
    }

    public static interface MyItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener ( MyItemClickListener listener ) {
        this.listener = listener;
    }
}
