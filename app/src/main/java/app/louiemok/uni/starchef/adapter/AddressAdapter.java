package app.louiemok.uni.starchef.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.model.Address;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private List<Address> data;

    private View view;

    private Context context;

    private MyItemClickListener listener = null;

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_choose;
        ImageView iv_isused;
        ImageView iv_arrow;
        TextView tv_address_contact_name;
        TextView tv_address_contact_address;
        LinearLayout ll_main;

        public ViewHolder ( View v ) {
            super(v);
            iv_choose = v.findViewById(R.id.iv_choose);
            iv_isused = v.findViewById(R.id.iv_isused);
            iv_arrow = v.findViewById(R.id.iv_arrow);
            tv_address_contact_name = v.findViewById(R.id.tv_address_contact_name);
            tv_address_contact_address = v.findViewById(R.id.tv_address_contact_address);
            ll_main = v.findViewById(R.id.ll_main);
        }

    }

    public AddressAdapter ( Context context, List<Address> d ) {
        this.data = d;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Address address = data.get(position);
        holder.tv_address_contact_name.setText(address.getName());
        holder.tv_address_contact_address.setText(address.getAddress());
        if ( address.getIsuesd() == 1 ) {
            holder.iv_isused.setVisibility(View.VISIBLE);
        }
        else {
            holder.iv_isused.setVisibility(View.GONE);
        }
        if ( address.getIsselected() == 0 ) {
            holder.iv_choose.setVisibility(View.GONE);
        }
        else if ( address.getIsselected() == 1 ) {
            holder.iv_choose.setVisibility(View.VISIBLE);
            holder.iv_choose.setImageResource(R.drawable.tick_normal);
        }
        else if ( address.getIsselected() == 2 ) {
            holder.iv_choose.setVisibility(View.VISIBLE);
            holder.iv_choose.setImageResource(R.drawable.tick);
        }
        holder.iv_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.choose(view, position);
            }
        });
        holder.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.select(view, position);
            }
        });
        holder.iv_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.select(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.address_cell, parent, false);
        return new ViewHolder(view);
    }

    public static interface MyItemClickListener {
        public void choose(View view, int position);
        public void select(View view, int position);
    }

    public void setOnItemClickListener ( MyItemClickListener listener ) {
        this.listener = listener;
    }
}
