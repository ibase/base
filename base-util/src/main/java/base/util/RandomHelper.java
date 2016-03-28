package base.util;

import java.util.Random;

/**
 * Created by base on 2016/3/28.
 */
public class RandomHelper {

    /** 小写 */
    public static final byte CASE_UPPER = 0x01;
    /** 大写 */
    public static final byte CASE_LOWER = 0x02;
    /** 所有 */
    public static final byte CASE_ALL = 0x03;

    /**
     * 随机生成指定长度的数字串
     * @param len
     * @return
     */
    public static String randomNumbers(int len) {
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < len; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 随机生成指定长度的字母串
     * @param len
     * @param _case
     * @return
     */
    public static String randomLetters(int len, byte _case) {
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        switch (_case){
            case RandomHelper.CASE_LOWER:
                for (int i = 0; i < len; i++) {
                    sb.append((char) (random.nextInt(26)+97));
                }
                break;
            case RandomHelper.CASE_UPPER :
                for (int i = 0; i < len; i++) {
                    sb.append((char) (random.nextInt(26)+65));
                }
                break;
            default:
                for (int i = 0; i < len; i++) {
                    int num = random.nextInt(52);
                    if(num<26){
                        sb.append((char) (num+97));
                    }else {
                        sb.append((char)(num-26+65));
                    }
                }
                break;
        }

        return sb.toString();
    }
}
