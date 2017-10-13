package app.louiemok.uni.starchef;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    //监听home键
    @Override
    public void onBackPressed () {
        super.onBackPressed();
        String activityName = getClass().getSimpleName();
        if ( activityName.equals("HomeActivity") ) {
            ActivityCollector.finishAll();
            System.exit(0);
        }
    }

    public void finishActivity (Class<?> cls) {
        for ( Activity act : ActivityCollector.getAllActivities() ) {
            if ( act.getClass().equals(cls) ) {
                ActivityCollector.removeActivity(act);
                return;
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

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
