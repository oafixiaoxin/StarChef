package app.louiemok.uni.starchef.seledefine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class RecyclerViewWithEmptyView extends RecyclerView {

    private View emptyView;

    public void setEmptyView ( View emptyView ) {
        this.emptyView = emptyView;
    }

    public RecyclerViewWithEmptyView ( Context context ) {
        super(context);
    }

    public RecyclerViewWithEmptyView ( Context context, AttributeSet attr ) {
        super(context, attr);
    }

    public RecyclerViewWithEmptyView ( Context context, AttributeSet attr, int defStyle ) {
        super(context, attr, defStyle);
    }

    @Override
    public void setAdapter ( Adapter adapter ) {
        super.setAdapter(adapter);
        if ( adapter != null ) {
            adapter.registerAdapterDataObserver(emptyAdapterDataObserver);
        }
        emptyAdapterDataObserver.onChanged();
    }

    private AdapterDataObserver emptyAdapterDataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            if ( getAdapter() != null && emptyView != null ) {
                if ( getAdapter().getItemCount() == 0 ) {
                    emptyView.setVisibility(VISIBLE);
                    setVisibility(GONE);
                }
                else {
                    emptyView.setVisibility(GONE);
                    setVisibility(VISIBLE);
                }
            }
        }
    };

}
