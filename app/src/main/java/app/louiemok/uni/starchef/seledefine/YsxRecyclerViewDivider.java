package app.louiemok.uni.starchef.seledefine;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class YsxRecyclerViewDivider extends RecyclerView.ItemDecoration{

    private Paint paint;
    private Drawable mDivider;
    private int mDividerHeight = 2;
    private int mOrientation;
    private static final int[] ATTRs = new int[]{android.R.attr.listDivider};
    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    /*
    * 默认分割线
    */
    public YsxRecyclerViewDivider ( Context context, int orientation ) {
        if ( orientation != LinearLayout.HORIZONTAL && orientation != LinearLayout.VERTICAL ) {
            throw new IllegalArgumentException("请输入正确的参数");
        }
        mOrientation = orientation;
        final TypedArray a = context.obtainStyledAttributes(ATTRs);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    public YsxRecyclerViewDivider ( int spanCount, int spacing, boolean
            includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    /*
    * 自定义分割线drawable
    */
    public YsxRecyclerViewDivider ( Context context, int orientation, int drawableId ) {
        this(context, orientation);
        mDivider = ContextCompat.getDrawable(context, drawableId);
        mDividerHeight = mDivider.getIntrinsicHeight();
    }

    /*
    * 自定义分割线，宽度和颜色
    */
    public YsxRecyclerViewDivider ( Context context, int orientation, int dividerHeight, int
            dividerColor ) {
        this(context, orientation);
        mDividerHeight = dividerHeight;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(dividerColor);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void getItemOffsets ( Rect outRect, View view, RecyclerView parent, RecyclerView.State
     state ) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, mDividerHeight);

        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }

    @Override
    public void onDraw ( Canvas c, RecyclerView parent, RecyclerView.State state ) {
        super.onDraw(c, parent, state);
        if ( mOrientation == LinearLayout.VERTICAL ) {
            drawVertical(c, parent);
        }
        else {
            drawHorizontal(c, parent);
        }
    }

    private void drawVertical ( Canvas canvas, RecyclerView parent ) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mDividerHeight;
            if ( mDivider != null ) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if ( paint != null ) {
                canvas.drawRect(left, top, right, bottom, paint);
            }
        }
    }

    private void drawHorizontal ( Canvas canvas, RecyclerView parent ) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int chileSize = parent.getChildCount();
        for ( int i = 0 ; i < chileSize ; i++ ) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)child
                    .getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + mDividerHeight;
            if ( mDivider != null ) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if ( paint != null ) {
                canvas.drawRect(left, top, right, bottom, paint);
            }
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
