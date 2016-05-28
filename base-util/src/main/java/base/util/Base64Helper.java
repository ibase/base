package base.util;

import java.util.Arrays;

/**
 * Created by base on 2016/5/18.
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
     * string source to bytes
     *
     * 有行分隔符(行分隔符必须是"\r\n")则必须是76个字符一行,或者无行分隔符,所有数据在一行
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
        for (int sepLoc = 0, decCnt = (len / 3) * 3; index < decCnt;) {
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
        return decode(chars, 0, chars.length);
    }

    public static byte[] decode(String string) {
        return decode(string.toCharArray(), 0, string.length());
    }

    public static String encode(byte[] bytes){

        if(bytes==null || bytes.length==0){
            return null;
        }

        StringBuilder sb = new StringBuilder();
        // 编码数据(此处忽略数据最后0~2字节)
        int index = 0 , srcLen = bytes.length;
        for (int encCnt = (srcLen / 3) * 3; index < encCnt; ) {
            int i = ((bytes[index++] << 16) & 0xff0000) | ((bytes[index++] << 8) & 0xff00) | (bytes[index++] & 0xff);
            sb.append(ENCODE_CHARS[(i >> 18) & 0x3f]);
            sb.append(ENCODE_CHARS[(i >> 12) & 0x3f]);
            sb.append(ENCODE_CHARS[(i >> 6) & 0x3f]);
            sb.append(ENCODE_CHARS[i & 0x3f]);
        }
        // 编码数据(最后0~2字节, 包括'=')
        if (index < srcLen) {
            //剩余解码字节个数, 补'='(字节)个数
            int modCnt = srcLen - index, equCnt = 3 - modCnt;
            int i = 0x00ffffff;
            for (int j = 0; j < modCnt; j++)
                i &= (bytes[index++] << (16 - j << 3));
            for (int j = 0; j <= modCnt; j++)
                sb.append(ENCODE_CHARS[(i >> (18 - j * 6)) & 0x3f]);
            for (int j = 0; j < equCnt; j++) {
                sb.append('=');
            }
        }

        return sb.toString();
    }
}

