package com.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * <p>
 * Title: 数字工具类
 * </p>
 * <p>
 * Description: 常用的数字处理及格式化函数集合
 * </p>
 */
public class NumberTools {
    public NumberTools() {
        // default constructor
    }

    /**
     * 根据参数格式化double型数值到小数点后几位 格式化结果将进行四舍五入处理
     *
     * @param d
     *            double 待格式化的数值
     * @param definition
     *            int 格式化到小数点后几位
     * @return String 格式化后得到的数值字符串形式
     */
    public static String getFormatNumber(double d, int definition) {
        /*
         * String str = ""; StringBuffer sb = new StringBuffer("");
         * sb.append("0"); if ( (d + "").indexOf(".") == -1 && definition > 0) {
         * sb.append("."); for (int i = 0; i < definition; i++) {
         * sb.append("0"); } }
         */
        // 2006-10-19 cb 修改
        // String str = "";
        StringBuffer sb = new StringBuffer("");
        sb.append("0.");

        if (definition > 0) {
            for (int i = 0; i < definition; i++) {
                sb.append("0");
            }
        }

        DecimalFormat dft = new DecimalFormat(sb.toString());
        return dft.format(d);
    }

    /**
     * 判断对象形的数字是否为空
     *
     * @param srcObject
     *            Object
     * @param defaultValue
     *            int
     * @return String
     */
    public static String numberIfNull(Object srcObject, String defaultValue) {
        if (srcObject == null) {
            if (defaultValue != null) {
                return defaultValue;
            }

            return "";
        }
        if (srcObject instanceof Long || srcObject instanceof Integer ||
            srcObject instanceof BigDecimal || srcObject instanceof String||srcObject instanceof Double) {
            return srcObject.toString();
        }
        return srcObject.toString();

    }

    /**
     * 如果需变更对象为空 则返回空字符串
     *
     * @param srcObject
     *            Object
     * @return String
     */
    public static String numberIfNull(Object srcObject) {
        String str = "";
        if (srcObject != null) {
            str = srcObject.toString();
        }
        return str;

    }

    public static String numberChangeZero(Object srcObject, String defaultValue) {
        if (srcObject == null) {
            if (defaultValue != null) {
                return defaultValue;
            }

            return "";
        }
        if (srcObject instanceof Long || srcObject instanceof Integer ||
            srcObject instanceof BigDecimal||srcObject instanceof Double) {
            return srcObject.toString();
        }
        return srcObject.toString();

    }

    /**
     * 将一个对象的int值,如果对象为空,则返回默认值,如果对象不可转换为int,则返回Integer.MAX_VALUE
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static final int getIntValue(Object value, int defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        if (value instanceof Integer) {
            return ( (Integer) value).intValue();
        }
        else if (value instanceof Long) {
            return ( (Long) value).intValue();
        }
        else if (value instanceof BigDecimal) {
            return ( (BigDecimal) value).intValue();
        }
        else if (value instanceof Float) {
            return ( (Float) value).intValue();
        }
        else if (value instanceof Double) {
            return ( (Double) value).intValue();
        }
        else if (value instanceof BigInteger) {
            return ( (BigInteger) value).intValue();
        }
        else if (value instanceof String) {
            Pattern p = Pattern.compile("^[-+]{0,1}[0-9]{1,}$");
            Matcher m = p.matcher( (String) value);
            if (!m.find() || ( (String) value).length() == 0) {
                return defaultValue;
            }
            return Integer.parseInt( (String) value);
        }
        else {
            return defaultValue;
        }
    }

    /**
     * 将一个对象的long值,如果对象为空,则返回默认值,如果对象不可转换为long,则返回Long.MAX_VALUE
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static final long getLongValue(Object value, long defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        if (value instanceof Integer) {
            return ( (Integer) value).longValue();
        }
        else if (value instanceof Long) {
            return ( (Long) value).longValue();
        }
        else if (value instanceof BigDecimal) {
            return ( (BigDecimal) value).longValue();
        }
        else if (value instanceof Float) {
            return ( (Float) value).longValue();
        }
        else if (value instanceof Double) {
            return ( (Double) value).longValue();
        }
        else if (value instanceof BigInteger) {
            return ( (BigInteger) value).longValue();
        }
        else if (value instanceof String) {
            Pattern p = Pattern.compile("^[-+]{0,1}[0-9]{1,}$");
            Matcher m = p.matcher( (String) value);
            if (!m.find() || ( (String) value).length() == 0) {
                return defaultValue;
            }
            return Long.parseLong( (String) value);
        }
        else {
            return defaultValue;
        }
    }

    /**
     * 将一个对象的double值,如果对象为空,则返回默认值,如果对象不可转换为long,则返回Long.MAX_VALUE
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static final double getDoubleValue(Object value, double defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        if (value instanceof Integer) {
            return ( (Integer) value).doubleValue();
        }
        else if (value instanceof Long) {
            return ( (Long) value).doubleValue();
        }
        else if (value instanceof BigDecimal) {
            return ( (BigDecimal) value).doubleValue();
        }
        else if (value instanceof Float) {
            return ( (Float) value).doubleValue();
        }
        else if (value instanceof Double) {
            return ( (Double) value).doubleValue();
        }
        else if (value instanceof BigInteger) {
            return ( (BigInteger) value).doubleValue();
        }
        else if (value instanceof String) {
            Pattern p = Pattern.compile(
                "^[-+]{0,1}[0-9]{1,}[\\.]{0,1}[0-9]{0,}$");
            Matcher m = p.matcher( (String) value);
            if (!m.find() || ( (String) value).endsWith(".") ||
                ( (String) value).length() == 0) {
                return defaultValue;
            }
            return Double.parseDouble( (String) value);
        }
        else {
            return defaultValue;
        }
    }

    public static final String[] numStep = {
        "", "十", "百", "千"};

    public static final String[] numUnit = {
        "", "万", "亿", "亿"};

    /**
     * 将数字转换为中文
     *
     * @param num
     * @param zero
     * @return
     */
    public static String toZhCN(double num, boolean zero, int definition) {
        String str = "";

        // 格式化数字
        if (definition == 0) {
            str = new DecimalFormat("0").format(num);
        }
        else {
            str = getFormatNumber(num, definition);
        }

        String[] part = {
            "", ""};

        if (str.indexOf(".") != -1) {
            part = str.split("\\.");
        }
        else {
            part[0] = str;
        }

        StringBuffer dest = new StringBuffer("");

        boolean preZero = false; // 前一位是否为0
        boolean zeroUnit = false; // 是否有零单位

        for (int i = part[0].length() - 1, count = 0; i >= 0; i--) {
            int n = Integer.parseInt(part[0].charAt(i) + "");

            if (n == 0) {
                String sn = "";

                if (!preZero) {
                    sn = getCnNum(n, true);
                }

                if (count % 4 == 0) {
                    if (count / 4 > 2) {
                        sn = numUnit[count / 4 % 3 + 1];
                    }
                    else {
                        sn = numUnit[count / 4 % 3];
                    }

                    if (zeroUnit) {
                        for (int j = 0; j < numUnit.length; j++) {
                            if (dest.length() > 0 &&
                                (dest.charAt(0) + "").equals(numUnit[j]) &&
                                ! (dest.charAt(0) + "").equals(sn)) {
                                dest = new StringBuffer(dest.substring(1));
                                break;
                            }
                        }
                    }

                    zeroUnit = true;
                }

                dest.insert(0, sn);

                preZero = true;
            }
            else {
                String sn = getCnNum(n, zero);

                sn += numStep[count % 4];

                if (n == 1 && count % 4 == 1) {
                    sn = numStep[count % 4];
                }

                if (count % 4 == 0) {
                    if (count / 4 > 1) {
                        sn += numUnit[count / 4 % 3 + 1];
                    }
                    else {
                        sn += numUnit[count / 4 % 3];
                    }

                    zeroUnit = false;
                }

                dest.insert(0, sn);

                preZero = false;
            }

            count++;
        }

        if (part[1].length() > 0) {
            dest.append("点" + getCnNum(Long.parseLong(part[1]), zero));
        }

        // return dest.toString();
        String result = dest.toString();
        // result = result.replaceAll("[零]+", "零");
        return result;
    }

    /**
     * 将数字按位转换为中文，没有进位。
     *
     * @param num
     * @param zero
     *            true - 显示汉字 "零", false - 显示符号
     * @return
     */
    public static final String getCnNum(long num, boolean zero) {
        String cn = "";

        char[] c = String.valueOf(num).toCharArray();

        for (int i = 0; i < c.length; i++) {

            switch (c[i]) {
                case 49: {
                    cn += "一";
                    break;
                }
                case 50: {
                    cn += "二";
                    break;
                }
                case 51: {
                    cn += "三";
                    break;
                }
                case 52: {
                    cn += "四";
                    break;
                }
                case 53: {
                    cn += "五";
                    break;
                }
                case 54: {
                    cn += "六";
                    break;
                }
                case 55: {
                    cn += "七";
                    break;
                }
                case 56: {
                    cn += "八";
                    break;
                }
                case 57: {
                    cn += "九";
                    break;
                }
                case 48: {
                    if (!zero) {
                        cn += "○";
                    }
                    else {
                        cn += "零";
                    }
                    break;
                }
            }
        }

        return cn;
    }
    
    public String getFourNum(long i)
	{
		if (i >= 0 && i < 9) return "000" + i;
		else if (i >=10 && i < 99) return "00" + i;
		else if (i < 999) return "0" + i;
		else return "" + i;
	}
}
