package app.louiemok.uni.starchef.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;

import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.adapter.DynamicOutSideAdapter;
import app.louiemok.uni.starchef.adapter.FoodtipsAdapter;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;

public class DiscoverFragment extends Fragment implements View.OnClickListener {

    YsxNavigationBar ysxNavigationBar;
    ViewPager viewPager;
    ListView lv_jingcaidongtai;
    ListView lv_xingjiqingbao;
    ListView lv_foodtips;
    View view;
    DynamicOutSideAdapter dynamicOutSideAdapter;
    FoodtipsAdapter foodtipsAdapter;
    RelativeLayout rl_todynamic;

    public DiscoverFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_discover, container, false);
        return view;
    }

    @Override
    public void onActivityCreated ( Bundle savedInstanceState ) {
        super.onActivityCreated(savedInstanceState);
        initYsxNavigationBar();
        initViewPager();
        initJingcaidongtai();
        initXingjiqingbao();
        initFoodTips();
        rl_todynamic = view.findViewById(R.id.rl_todynamic);
        rl_todynamic.setOnClickListener(this);
    }

    public static DiscoverFragment newInstance ( String title ) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        DiscoverFragment fragment = new DiscoverFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void initYsxNavigationBar () {
        ysxNavigationBar = view.findViewById(R.id.nav);
        ysxNavigationBar.hideLeftSrc();
        ysxNavigationBar.setTitle("美食发现");
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

    public void initViewPager () {
        viewPager = view.findViewById(R.id.viewPager);
        int[] imgs = {R.drawable.banner_1, R.drawable.banner_2, R.drawable.banner_3};
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

    private void initJingcaidongtai () {
        lv_jingcaidongtai = view.findViewById(R.id.lv_jingcaidongtai);
        dynamicOutSideAdapter = new DynamicOutSideAdapter(new ArrayList<HashMap<String, String>>
                (), getActivity());
        lv_jingcaidongtai.setAdapter(dynamicOutSideAdapter);
        setListViewHeightBasedOnChild(lv_jingcaidongtai);
    }

    private void initXingjiqingbao () {
        lv_xingjiqingbao = view.findViewById(R.id.lv_xingjiqingbao);
        foodtipsAdapter = new FoodtipsAdapter(getActivity(), new ArrayList<HashMap<String,
                String>>());
        lv_xingjiqingbao.setAdapter(foodtipsAdapter);
        setListViewHeightBasedOnChild(lv_xingjiqingbao);
    }

    private void initFoodTips () {
        lv_foodtips = view.findViewById(R.id.lv_foodtips);
        foodtipsAdapter = new FoodtipsAdapter(getActivity(), new ArrayList<HashMap<String,
                String>>());
        lv_foodtips.setAdapter(foodtipsAdapter);
        setListViewHeightBasedOnChild(lv_foodtips);
    }

    private void setListViewHeightBasedOnChild ( ListView ls ) {
        ListAdapter listAdapter = ls.getAdapter();
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
        params.height = totalHeight + (ls.getDividerHeight() * (listAdapter.getCount() - 1));
        ls.setLayoutParams(params);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void onClick ( View v ) {
        Intent intent;
        switch (v.getId()) {
            case R.id.rl_todynamic:
                intent = new Intent();
                intent.setClass(getActivity(), DynamicActivity.class);
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }
    }

}
