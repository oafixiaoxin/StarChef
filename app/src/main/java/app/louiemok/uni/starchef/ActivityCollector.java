package app.louiemok.uni.starchef;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class ActivityCollector {

    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity ( Activity activity ) {
        activities.add(activity);
    }

    public static void removeActivity ( Activity activity ) {
        if ( !activity.isFinishing() ) {
            activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public static void finishAll () {
        for ( Activity activity : activities ) {
            if ( !activity.isFinishing() ) {
                activity.finish();
            }
        }
        activities.clear();
    }

    public static List<Activity> getAllActivities () {
        return activities;
    }
}
