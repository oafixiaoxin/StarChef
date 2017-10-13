package app.louiemok.uni.starchef.view;

import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.louiemok.uni.starchef.BaseActivity;
import app.louiemok.uni.starchef.R;

public class BuyCuponActivity extends BaseActivity{

    ActionBar actionBar;
    ViewPager viewPager;
    TextView tv_old_price;

    int cuponid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_cupon);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onResume () {
        super.onResume();

        cuponid = getIntent().getIntExtra("cuponid", 0);
        Log.e("cuponid", String.valueOf(cuponid));

        initViewPager();
        tv_old_price = (TextView)findViewById(R.id.tv_old_price);
        tv_old_price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public boolean onCreateOptionsMenu ( Menu menu ) {
        getMenuInflater().inflate(R.menu.subscribe_shop_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected ( MenuItem menuItem ) {
        switch (menuItem.getItemId()) {
            case R.id.sendtoother:
                Log.e("shopDetailActivity", "sendtoother");
                break;
            case R.id.star_collect:
                Log.e("shopDetailActivity", "star_collect");
                break;
            case R.id.dot:
                Log.e("shopDetailActivity", "dot");
                break;
            default:
                finish();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void initViewPager () {
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        int[] imgs = {R.drawable.loop_img_1, R.drawable.loop_img_2, R.drawable.loop_img_1};
        ImageView[] mImageViews = new ImageView[imgs.length];
        for ( int i = 0 ; i < imgs.length ; i++ ) {
            ImageView imageView = new ImageView(this);
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
        public boolean isViewFromObject (View arg0, Object arg1 ) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem ( View container, int position, Object object ) {
            ((ViewPager)container).removeView(this.imageViews[position]);
        }

        @Override
        public Object instantiateItem (ViewGroup container, int position ) {
            container.addView(this.imageViews[position], 0);
            return this.imageViews[position];
        }

    }

}
