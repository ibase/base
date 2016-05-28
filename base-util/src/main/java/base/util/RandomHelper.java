package base.util;

import java.util.Random;

/**
 * Created by base on 2016/3/28.
 */
public class RandomHelper {

    /** 小写 */
    public static final int _CASE_UPPER = 0x01;
    /** 大写 */
    public static final int _CASE_LOWER = 0x02;
    /** 所有 */
    public static final int _CASE_ALL = 0x03;

    public final static int _CASE_INC_INC = 1;
    public final static int _CASE_INC_EXC = 2;
    public final static int _CASE_EXC_INC = 3;
    public final static int _CASE_EXC_EXC = 4;

    private final static String BASE_CHARS = "ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

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
    public static String randomLetters(int len, int _case) {
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        switch (_case){
            case RandomHelper._CASE_LOWER:
                for (int i = 0; i < len; i++) {
                    sb.append((char) (random.nextInt(26)+97));
                }
                break;
            case RandomHelper._CASE_UPPER :
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

    /**
     * 生成一个指定范围的随机数
     * @param start
     * @param end
     * @param _case 是否包含给定的start或end
     * @return
     */
    public static int createNumber(int start, int end, int _case){
        int number = 0;
        Random random = new Random();
        switch (_case){
            case _CASE_INC_INC:
                number = random.nextInt(end-start+1)+start;
                break;
            case _CASE_INC_EXC:
                number = random.nextInt(end-start)+start;
                break;
            case _CASE_EXC_INC:
                number = random.nextInt(end-start)+start+1;
                break;
            case _CASE_EXC_EXC:
                number = random.nextInt(end-start-1)+start+1;
                break;
            default:
                number = random.nextInt(end-start+1)+start;
                break;
        }
        return number;
    }

    /**
     * 生成指定长度的验证码(包含大小写字母和数字)
     * @param length
     * @return
     */
    public static String createCode(int length){
        StringBuilder sb = new StringBuilder();

        //随机选取字符组成字符串
        for(int n=0; n<length; n++){
            int index = (int)(62*Math.random());
            sb.append(BASE_CHARS.charAt(index));
        }
        return sb.toString();
    }
}
