package base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by base on 2016/3/28.
 */
public class DateHelper {

    /**
     * 比较两个日期大小，无论是否为空值
     * @param date1
     * @param date2
     * @return -1:date1<date2; 0:date1=date2; 1:date1>date2
     */
    public static int compareTeam(Date date1, Date date2){
        if (ValueHelper.isNone(date1) && ValueHelper.isNone(date2)){
            return 0;
        }
        if (!ValueHelper.isNone(date1) && !ValueHelper.isNone(date2)){
            return date1.compareTo(date2);
        }else {
            if (ValueHelper.isNone(date1)){
                return -1;
            }
            if (ValueHelper.isNone(date2)){
                return 1;
            }
        }
        return 0;
    }

    /**
     * 获取两个时间点中的任意一个时间点(不包括给定的时间)
     * @param startTime
     * @param endTime
     * @return
     */
    public static long getDateOfThem(long startTime, long endTime) {

        long date = startTime + (long)(Math.random()* (endTime - startTime));
        if(date==startTime){
            return getDateOfThem(startTime,endTime);
        }
        return date;
    }

    /**
     * covert to yyyy-MM-dd string
     * @param date
     * @return
     */
    public static String toDateString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * covert to yyyy-MM-dd HH:mm:ss string
     * @param date
     * @return
     */
    public static String toTimeString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}
