package app.louiemok.uni.starchef.seledefine;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class DateUtils {
    public static String getTime ( String time, String format ) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        return simpleDateFormat.format(new Date(i * 1000L));
    }
}
