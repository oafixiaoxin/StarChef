package app.louiemok.uni.starchef.seledefine;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class StarView extends LinearLayout {

    private static final int COUNT = 5;

    private MyItemClickListener myItemClickListener;

    public StarView ( Context context ) {
        super(context);
    }

    public StarView ( Context context, AttributeSet attr ) {
        super(context, attr);
    }

    public StarView ( Context context, AttributeSet attr, int defStyle ) {
        super(context, attr, defStyle);
    }

    public void setStar (int star, final int normalPic, final int coverPic, int pix, int marginLeft
    ) {
        for ( int i = 0 ; i < COUNT ; i++ ) {
            final int position = i;
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dip2px(getContext(), pix),
                    dip2px(getContext(), pix));
            if ( i != 0 ) {
                lp.setMargins(dip2px(getContext(), marginLeft), 0, 0, 0);
            }
            imageView.setLayoutParams(lp);
            if ( i < star ) {
                imageView.setImageResource(coverPic);
            }
            else {
                imageView.setImageResource(normalPic);
            }
            imageView.setTag(i);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    for ( int k = 4 ; k >= 0 ; k-- ) {
                        ImageView iv = findViewWithTag(k);
                        iv.setImageResource(normalPic);
                    }
                    for ( int k = position ; k >= 0 ; k-- ) {
                        ImageView iv = findViewWithTag(k);
                        iv.setImageResource(coverPic);
                    }
                    myItemClickListener.click(position);
                }
            });
            this.addView(imageView);
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static interface MyItemClickListener {
        void click(int position);
    }

    public void setOnImtemClickListener ( MyItemClickListener listener ) {
        this.myItemClickListener = listener;
    }

}
