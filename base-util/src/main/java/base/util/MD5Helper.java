package base.util;

import java.security.MessageDigest;

/**
 * Created by base on 2016/3/28.
 */
public class MD5Helper {

    public static final byte _CASE_UPPER = 0x01;
    public static final byte _CASE_LOWER = 0x02;
    public static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String getMD5OfString(String value, byte _case){
        String result=null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(value.getBytes("UTF-8"));
            result = bytesToHexStr(md.digest(),_case);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String bytesToHexStr(byte[] byteArray, byte _case) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            sb.append(byteToHexStr(byteArray[i], _case));
        }
        return sb.toString();
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte, byte _case) {

        char[] tempArr = new char[2];
        tempArr[0] = HEX[(mByte >>> 4) & 0X0F];
        tempArr[1] = HEX[mByte & 0X0F];

        String s = new String(tempArr);
        if(_case == MD5Helper._CASE_UPPER){
            return s.toUpperCase();
        }
        return s;
    }
}
