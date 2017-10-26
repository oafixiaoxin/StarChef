package app.louiemok.uni.starchef.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.louiemok.uni.starchef.R;

/**
 * Created by Administrator on 2017/10/13 0013.
 */

public class CommentImageAdapter extends RecyclerView.Adapter<CommentImageAdapter.ViewHolder> {

    private View view;

    private Context context;

    private ArrayList<HashMap<String, Bitmap>> data;

    private MyItemClickListener listener;

    public CommentImageAdapter ( Context context, ArrayList<HashMap<String, Bitmap>> d ) {
        this.context = context;
        this.data = d;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_comment_image;

        public ViewHolder ( View view ) {
            super(view);
            iv_comment_image = view.findViewById(R.id.iv_comment_image);
        }

    }

    @Override
    public CommentImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.comment_image_cell, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v, (int)v.getTag());
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        HashMap<String, Bitmap> map = data.get(position);
        if ( map.get("normal") != null ) {
            holder.iv_comment_image.setImageBitmap(map.get("normal"));
            holder.iv_comment_image.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        else {
            holder.iv_comment_image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static interface MyItemClickListener {
        void onClick(View view, int position);
    }

    public void setOnItemClickListener ( MyItemClickListener listener ) {
        this.listener = listener;
    }

}
