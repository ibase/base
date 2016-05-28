package base.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by base on 2016-05-29.
 */
public class PinyinUtil {
    /**
     * 汉字串转拼音
     * @param name 汉字串
     * @return 拼音
     */
    public static String words2Pinyin(String name){
        String pinyinName = "";
        char[] nameChars = name.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChars.length; i++) {
            if (nameChars[i] > 128) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChars[i], defaultFormat)[0];
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return pinyinName;
    }

    /**
     * 汉字串转拼音(使用Unicode)
     * @param name 汉字串
     * @return 拼音
     */
    public static String words2PinyinByUnicode(String name){
        String pinyinName = "";
        char[] nameChars = name.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

        for(char nameChar : nameChars) {
            if (nameChar > 128) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar, defaultFormat)[0];
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return pinyinName;
    }

    /**
     * 汉字单字转拼音(含声调)
     * @param name 汉字
     * @param isWithTone 是否需要显示音调
     * @return 拼音
     */
    public static String word2Pinyin(char name,boolean isWithTone){
        String result = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

        String[] temp = null;
        if(isWithTone) {
            temp = PinyinHelper.toHanyuPinyinStringArray(name);
        } else{
            try {
                temp = PinyinHelper.toHanyuPinyinStringArray(name, defaultFormat);
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }
        }
        for(String s : temp){
            result += s;
        }
        return result;
    }

    /**
     * 汉字串转拼音首字母
     * @param name 汉字串
     * @param isUpper 是否需要大写
     * @return 拼音
     */
    public static String getWordsFirstLettersOfPinyin(String name,boolean isUpper){
        String pinyinName = "";
        char[] nameChars = name.toCharArray();

        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        if(isUpper){
            defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        } else{
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        }
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

        String firstLetters = "";
        for(char nameChar : nameChars) {
            if (nameChar > 128) {
                try {
                    pinyinName = PinyinHelper.toHanyuPinyinStringArray(nameChar, defaultFormat)[0];
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            firstLetters += pinyinName.substring(0,1);
        }
        return firstLetters;
    }
}
