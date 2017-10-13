package app.louiemok.uni.starchef.seledefine;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class ListViewInScrollView extends ListView {

    public ListViewInScrollView ( Context context ) {
        super(context);
    }

    public ListViewInScrollView ( Context context, AttributeSet attr ) {
        super(context, attr);
    }

    public ListViewInScrollView ( Context context, AttributeSet attr, int defineStyle ) {
        super(context, attr, defineStyle);
    }

    @Override
    protected void onMeasure ( int width, int height ) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(width, expandSpec);
    }

}
