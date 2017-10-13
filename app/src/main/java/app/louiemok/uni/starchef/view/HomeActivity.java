package app.louiemok.uni.starchef.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

import app.louiemok.uni.starchef.ActivityCollector;
import app.louiemok.uni.starchef.BaseActivity;
import app.louiemok.uni.starchef.R;

public class HomeActivity extends BaseActivity {

    private ArrayList<Fragment> fragments = null;
    LinearLayout ll_fragment;
    BottomNavigationBar bottom_navagation_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initBottomNavigationBar();
        setDefaultFragment();
        if ( fragments != null ) {
            fragments.clear();
        }
        fragments = getFragments();
    }

    @Override
    protected void onResume () {
        super.onResume();
    }

    protected void setDefaultFragment () {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.ll_fragment, HomeFragment.newInstance("首页"));
        ft.commit();
    }

    protected ArrayList<Fragment> getFragments () {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("首页"));
        fragments.add(DiscoverFragment.newInstance("发现"));
        fragments.add(SubscribeFragment.newInstance("预订"));
        fragments.add(ShopFragment.newInstance("精品店"));
        fragments.add(PersonalFragment.newInstance("我"));
        return fragments;
    }

    protected void initBottomNavigationBar () {
        bottom_navagation_bar = (BottomNavigationBar)findViewById(R.id.bottom_navagation_bar);
        bottom_navagation_bar.setMode(BottomNavigationBar.MODE_FIXED);
        bottom_navagation_bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottom_navagation_bar.addItem(new BottomNavigationItem(R.drawable.homt22, "首页")
                .setActiveColorResource(R.color.bottomHomeColor))
                .addItem(new BottomNavigationItem(R.drawable.discover22, "发现")
                        .setActiveColorResource(R.color.bottomDiscoverColor))
                .addItem(new BottomNavigationItem(R.drawable.book22, "预订").setActiveColorResource
                        (R.color.bottomBookColor))
                .addItem(new BottomNavigationItem(R.drawable.subscribe22, "精品店")
                        .setActiveColorResource(R.color.bottomSubscribeColor))
                .addItem(new BottomNavigationItem(R.drawable.personal22, "我")
                        .setActiveColorResource(R.color.bottomPersonalColor))
                .setFirstSelectedPosition(0)
                .initialise();
        bottom_navagation_bar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if ( fragments.size() != 0 ) {
                    if ( position < fragments.size() ) {
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft  = fm.beginTransaction();
                        Fragment fragment = fragments.get(position);
                        ft.replace(R.id.ll_fragment, fragment);
                        ft.commitAllowingStateLoss();
                    }
                }
            }

            @Override
            public void onTabUnselected(int position) {
                if ( fragments.size() != 0 ) {
                    if ( position < fragments.size() ) {
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft  = fm.beginTransaction();
                        Fragment fragment = fragments.get(position);
                        ft.remove(fragment);
                        ft.commitAllowingStateLoss();
                    }
                }
            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
}
