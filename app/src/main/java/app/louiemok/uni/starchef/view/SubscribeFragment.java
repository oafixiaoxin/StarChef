package app.louiemok.uni.starchef.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.adapter.SubcribeAdapter;
import app.louiemok.uni.starchef.model.Shop;
import app.louiemok.uni.starchef.presenter.GetShopListPresenter;
import app.louiemok.uni.starchef.presenter.GetShopListPresenterImpl;
import app.louiemok.uni.starchef.seledefine.ListViewInScrollView;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;

public class SubscribeFragment extends Fragment implements View.OnClickListener, SubscribeView {

    YsxNavigationBar ysxNavigationBar;
    ListView lv_subscribe;
    SubcribeAdapter subcribeAdapter;
    LinearLayout ll_food;
    LinearLayout ll_rangement;
    LinearLayout ll_select;
    ViewPager viewPager;
    View view;

    GetShopListPresenter presenter;

    public SubscribeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_subscribe, container, false);
        return view;
    }

    @Override
    public void onActivityCreated ( Bundle saveInstancedState ) {
        super.onActivityCreated(saveInstancedState);

        presenter = new GetShopListPresenterImpl(this);

        initYsxNavigationBar();
        initViewPager();
        ll_food = view.findViewById(R.id.ll_food);
        ll_rangement = view.findViewById(R.id.ll_rangement);
        ll_select = view.findViewById(R.id.ll_select);

        ll_food.setOnClickListener(this);
        ll_rangement.setOnClickListener(this);
        ll_select.setOnClickListener(this);

        presenter.getShopList();

    }

    @Override
    public void onResume () {
        super.onResume();
    }

    public static SubscribeFragment newInstance ( String title ) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        SubscribeFragment fragment = new SubscribeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void initYsxNavigationBar () {
        ysxNavigationBar = view.findViewById(R.id.nav);
        ysxNavigationBar.hideLeftSrc();
        ysxNavigationBar.setTitle("预订餐厅");
        ysxNavigationBar.hideRightText();
        ysxNavigationBar.showRightSrc(R.drawable.magnifer);
        ysxNavigationBar.setClickCallback(new YsxNavigationBar.ClickCallback() {
            @Override
            public void onLeftClick() {

            }

            @Override
            public void onRightBtnClick() {

            }

            @Override
            public void onRightTextClick() {

            }
        });
    }

    private void setListViewHeightBasedOnChild ( ListView ls ) {
        ListAdapter listAdapter = ls.getAdapter();
        Log.e("height", String.valueOf(listAdapter.getCount()));
        if ( listAdapter == null ) {
            return;
        }

        int totalHeight = 0;
        for ( int i = 0 ; i < listAdapter.getCount() ; i++ ) {
            View listItem = listAdapter.getView(i, null, ls);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = ls.getLayoutParams();
        params.height = totalHeight + (ls.getDividerHeight() * (listAdapter.getCount() - 1)
                );
        //-dip2px(getActivity(), 56)
        ls.setLayoutParams(params);
    }

    public void onClick ( View view ) {
        switch (view.getId()) {
            case R.id.ll_food:
                Log.e("ll_food", "ll_food");
                break;
            case R.id.ll_rangement:
                Log.e("ll_rangement", "ll_rangement");
                break;
            case R.id.ll_select:
                Log.e("ll_select", "ll_select");
                break;
            default:
                break;
        }
    }

    public void initViewPager () {
        viewPager = view.findViewById(R.id.viewPager);
        int[] imgs = {R.drawable.loop_img_1, R.drawable.loop_img_2, R.drawable.loop_img_1};
        ImageView[] mImageViews = new ImageView[imgs.length];
        for ( int i = 0 ; i < imgs.length ; i++ ) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(imgs[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImageViews[i] = imageView;
        }
        viewPager.setAdapter(new MyAdapter(mImageViews));
        viewPager.setCurrentItem(0);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public class MyAdapter extends PagerAdapter {

        private ImageView[] imageViews;

        public MyAdapter ( ImageView[] imgs ) {
            this.imageViews = imgs;
        }

        @Override
        public int getCount () {
            return this.imageViews.length;
        }

        @Override
        public boolean isViewFromObject ( View arg0, Object arg1 ) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem ( View container, int position, Object object ) {
            ((ViewPager)container).removeView(this.imageViews[position]);
        }

        @Override
        public Object instantiateItem ( ViewGroup container, int position ) {
            container.addView(this.imageViews[position], 0);
            return this.imageViews[position];
        }

    }

    @Override
    public void onNetworkError ( String msg ) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success ( final List<Shop> shops ) {
        lv_subscribe = view.findViewById(R.id.lv_subscribe);
        subcribeAdapter = new SubcribeAdapter(getActivity(), shops);
        lv_subscribe.setAdapter(subcribeAdapter);

        lv_subscribe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ShopDetailActivity.class);
                intent.putExtra("shopid", shops.get(i).getShopid());
                getActivity().startActivity(intent);
            }
        });

        setListViewHeightBasedOnChild(lv_subscribe);
    }

}
