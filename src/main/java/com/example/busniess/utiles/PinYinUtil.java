package com.example.busniess.utiles;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtil {
    //
    private final static String[] DOUBLE_SURNAME = {"欧阳", "太史", "端木", "上官", "司马", "东方", "独孤", "南宫", "万俟", "闻人", "夏侯", "诸葛", "尉迟", "公羊", "赫连", "澹台", "皇甫", "宗政", "濮阳", "公冶", "太叔", "申屠", "公孙", "慕容", "仲孙", "钟离", "长孙", "宇文", "司徒", "鲜于", "司空", "闾丘", "子车", "亓官", "司寇", "巫马", "公西", "颛孙", "壤驷", "公良", "漆雕", "乐正", "宰父", "谷梁", "拓跋", "夹谷", "轩辕", "令狐", " 段干", "百里", "呼延", "东郭", "南门", "羊舌", "微生", "公户", "公玉", "公仪", "梁丘", "公仲", "公上", " 公门", "公山", "公坚", "左丘", "公伯", "西门", "公祖", "第五", "公乘", "贯丘", "公皙", "南荣", "东里", " 东宫", "仲长", "子书", "子桑", "即墨", "达奚", "褚师", "吴铭"};

    //将中文转换为拼音
    public static String getEname(String name) {
        String pinyinStr = "";
        char[] newChar = name.toCharArray();
        HanyuPinyinOutputFormat pyFormat = new HanyuPinyinOutputFormat();
        pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], pyFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }

    //姓","名的第一个字母需要为大写
    public static String getUpEname(String name) {
        char[] strs = name.toCharArray();
        String newName = null;
        for (String surname : DOUBLE_SURNAME) {
            if (name.indexOf(surname) == 0) {
                newName = getEname(surname) + " " + getEname(name.substring(2, name.length()));
                break;
            } else {
                newName = getEname("" + strs[0]) + " "
                        + getEname(name.substring(1, name.length()));
            }
        }
        return newName.toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(getUpEname("余承恩"));
    }
}
