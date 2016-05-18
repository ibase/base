package base.util;

import java.util.Arrays;

/**
 * Created by Doing on 2016/5/18.
 */
public class Base64Helper {

    public static final char[] ENCODE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    public static final int[]  DECODE_BYTES = new int[256];
    static {
        //初始化int数组
        Arrays.fill(DECODE_BYTES, -1);
        //将字符ASCII码作为索引，序号赋值给该索引下的元素
        for (int i = 0, cLen = ENCODE_CHARS.length; i < cLen; i++) {
            DECODE_BYTES[ENCODE_CHARS[i]] = i;
        }
        DECODE_BYTES['='] = 0;
    }

    /**
     * 有行分隔符则必须是76个字符一行,或者无行分隔符,所有数据在一行
     * 行分隔符必须是"\r\n"
     * 字符串内部不可有不规则字符,首尾可有(方法开始部分做了处理)
     * @param chars 源字符数组
     * @param offset 偏移量
     * @param charsLen 截取字符串长度 (长度为0返回空串,null则抛出异常)
     * @return
     */
    public static byte[] decode(char[] chars, int offset, int charsLen) {
        // 返回空串
        if (charsLen == 0) {
            return new byte[0];
        }
        // start and end index
        int sIx = offset, eIx = offset + charsLen - 1;
        // 去除首部不规则字符
        while (sIx < eIx && DECODE_BYTES[chars[sIx]] < 0)
            sIx++;
        // 去除尾部不规则字符
        while (eIx > 0 && DECODE_BYTES[chars[eIx]] < 0)
            eIx--;

        // 获取尾部‘=’填充数 (0, 1, 2)
        int equCnt = chars[eIx] == '=' ? (chars[eIx - 1] == '=' ? 2 : 1) : 0;
        // 字符总字节数
        int cCnt = eIx - sIx + 1;
        // 分隔符数, 76:\r,77:\n, (分隔符出现次数)*2
        int sepCnt = charsLen > 76 ? (chars[76] == '\r' ? cCnt / 78 : 0) << 1 : 0;
        // 预期解码后字节数
        int len = ((cCnt - sepCnt) * 6 >> 3) - equCnt;
        byte[] bytes = new byte[len];

        // 解码数据(此处忽略数据最后0~2字节)
        int index = 0;
        for (int sepLoc = 0, encCnt = (len / 3) * 3; index < encCnt;) {
            // 由4个有效字符装配到3个字节
            int i = DECODE_BYTES[chars[sIx++]] << 18 | DECODE_BYTES[chars[sIx++]] << 12 | DECODE_BYTES[chars[sIx++]] << 6 | DECODE_BYTES[chars[sIx++]];

            // 装配
            bytes[index++] = (byte) (i >> 16);
            bytes[index++] = (byte) (i >> 8);
            bytes[index++] = (byte) i;

            // 若有换行符则跳过: 76/4
            if (sepCnt > 0 && ++sepLoc == 19) {
                sIx += 2;
                sepLoc = 0;
            }
        }

        if (index < len) {
            // 解码数据(最后0~2字节, 包括'=')
            int i = 0;
            // 由4个有效字符装配到3个字节
            for (int j = 0; sIx <= eIx - equCnt; j++)
                i |= DECODE_BYTES[chars[sIx++]] << (18 - j * 6);
            // 装配
            for (int r = 16; index < len; r -= 8)
                bytes[index++] = (byte) (i >> r);
        }

        return bytes;
    }

    public static byte[] decode(char[] chars) {

        int charsLen = chars.length;
        if (charsLen == 0) {
            return new byte[0];
        }

        int sIx = 0, eIx = charsLen - 1;

        while (sIx < eIx && DECODE_BYTES[chars[sIx] & 0xff] < 0)
            sIx++;

        while (eIx > 0 && DECODE_BYTES[chars[eIx] & 0xff] < 0)
            eIx--;

        int pad = chars[eIx] == '=' ? (chars[eIx - 1] == '=' ? 2 : 1) : 0;
        int cCnt = eIx - sIx + 1;
        int sepCnt = charsLen > 76 ? (chars[76] == '\r' ? cCnt / 78 : 0) << 1 : 0;

        int len = ((cCnt - sepCnt) * 6 >> 3) - pad;
        byte[] dArr = new byte[len];

        int d = 0;
        for (int cc = 0, eLen = (len / 3) * 3; d < eLen;) {
            int i = 0;
            for (int j = 0; sIx <= eIx - pad; j++)
                i |= DECODE_BYTES[chars[sIx++]] << (18 - j * 6);

            for (int r = 16; d < len; r -= 8)
                dArr[d++] = (byte) (i >> r);

            if (sepCnt > 0 && ++cc == 19) {
                sIx += 2;
                cc = 0;
            }
        }

        if (d < len) {
            int i = 0;
            for (int j = 0; sIx <= eIx - pad; j++)
                i |= DECODE_BYTES[chars[sIx++]] << (18 - j * 6);

            for (int r = 16; d < len; r -= 8)
                dArr[d++] = (byte) (i >> r);
        }

        return dArr;
    }
}
