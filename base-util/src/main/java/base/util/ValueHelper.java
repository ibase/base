package base.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by base on 2016/3/28.
 */
public class ValueHelper {

    public static boolean isNone(Map map) {
        return map == null || map.size() == 0;
    }

    public static boolean isNone(List lst) {
        return lst == null || lst.size() == 0;
    }

    public static boolean isNone(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNoneByTrim(String value) {
        return value == null || "".equals(value.trim());
    }

    public static boolean isNone(Object object) {
        return object == null;
    }

    public static boolean isNone(Object[] objects) {
        return objects == null || objects.length == 0;
    }

    public static boolean isNone(byte[] bytes) {
        return bytes == null || bytes.length == 0;
    }

    public static String NullStringToEmpty(String value) {
        return value != null ? value : "";
    }

    public static int getIntegerValue(Integer value, int defaultValue) {
        return value != null ? value.intValue() : defaultValue;
    }

    public static byte getByteValue(Byte value, byte defaultValue) {
        return value != null ? value.byteValue() : defaultValue;
    }

    public static short getShortValue(Short value, short defaultValue) {
        return value != null ? value.shortValue() : defaultValue;
    }

    public static double getDoubleValue(Double value, double defaultValue) {
        return value != null ? value.doubleValue() : defaultValue;
    }

    public static boolean getBooleanValue(Boolean value, boolean defaultValue) {
        return value != null ? value.booleanValue() : defaultValue;
    }

    public static String getStringValue(String value, String defaultValue) {
        return value != null ? value : defaultValue;
    }

    public static Date getDateValue(Date date, Date defaultDate) {
        return date != null ? date : defaultDate;
    }

    public static int getIntegerValue(Integer value) {
        return value != null ? value.intValue() : 0;
    }

    public static byte getByteValue(Byte value) {
        return value != null ? value.byteValue() : (byte)0;
    }

    public static short getShortValue(Short value) {
        return value != null ? value.shortValue() : (short)0;
    }

    public static double getDoubleValue(Double value) {
        return value != null ? value.doubleValue() : (double)0;
    }

    public static boolean getBooleanValue(Boolean value) {
        return value != null ? value.booleanValue() : false;
    }

    public static Date getDateValue(Date date) {
        return date != null ? date : new Date(System.currentTimeMillis());
    }
}
