package com.tony.eureka.client1.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tony
 * @describe MixUtils
 * @date 2019-09-17
 */
public class MixUtils {
    private static final boolean flag = true; //是否进行编码转换

    public static String getProjectPath() {
        String classpath;
        classpath = MixUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return classpath;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stamp;
        for (int n = 0; n < b.length; ++n) {
            stamp = Integer.toHexString(b[n] & 0xFF);
            if (stamp.length() == 1)
                hs = hs + "0" + stamp;
            else
                hs = hs + stamp;
        }
        return hs;
    }

    public static byte[] hex2byte(String str) {
        if (str == null)
            return null;
        str = str.trim();
        int len = str.length();
        if ((len == 0) || (len % 2 == 1))
            return null;
        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[(i / 2)] = (byte) Integer.decode("0x" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String byte2hexSum(String str) {
        int index = 0;
        String[] sumStr = str.split("|");

        for (int i = 1; i < sumStr.length; ++i)
            index = (int) (index + Double.parseDouble(StrToBinStr(sumStr[i])));

        index %= 256;
        return String.valueOf(index);
    }

    private static String StrToBinStr(String str) {
        char[] strChar = str.toCharArray();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < strChar.length; ++i)
            result.append(Integer.toBinaryString(strChar[i]));

        return result.toString();
    }

    public static String getNowDate() {
        return getFormat("yyyyMMdd").format(new Date());
    }

    public static String getNowDate1() {
        return getFormat("yyyy/MM/dd").format(new Date());
    }

    public static String getNowTime() {
        return getFormat("HHmmss").format(new Date());
    }


    public static String getTime() {
        return getFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String getNowTime1() {
        return getFormat("HH:mm:ss").format(new Date());
    }

    public static SimpleDateFormat getFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    public static String getPropertiesParam(String propertiesName, String ParamName) throws Exception {
        Properties properties = new Properties();
        properties.load(MixUtils.class.getResourceAsStream("/" + propertiesName + ".properties"));

        if (properties.get(ParamName) == null)
            return null;

        return properties.get(ParamName).toString();
    }

    /**
     * 效验日期是否合法
     *
     * @param dateStr 日期字符串
     * @param spe     日期分隔符 如 :2014-01-01 , 分隔符为 -
     * @return 合法返回true, 反之false
     */
    public static boolean dateValidate(String dateStr, String spe) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy" + spe + "MM" + spe + "dd");
            df.setLenient(false);
            df.parse(dateStr);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    /**
     * 日期计算
     *
     * @param date yyyyMMdd
     * @param days +-n
     */
    public static String DateDay(String date, int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        GregorianCalendar gc = new GregorianCalendar();
        try {
            gc.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        gc.add(5, days);// 表示天减一.
        return new SimpleDateFormat("yyyyMMdd").format(gc.getTime());
    }


    /**
     * TODO 获取字符串长度，中文占cn bit位.
     *
     * @param str
     * @param cn_bit bit中文占的位数
     */
    public static int getStrLength(String str, int cn_bit) {
        int length = 0;
        String chinese = "[\u4e00-\u9fa5]";
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                String temp = str.substring(i, i + 1);
                if (temp.matches(chinese)) {
                    length += cn_bit;
                } else {
                    length += 1;
                }
            }
        }
        return length;
    }

    /**
     * TODO 截取字符串，中文占cnbit位.
     *
     * @param str
     * @param len
     * @param cn_bit 中文占的位数
     */
    // 截取字符串长度(中文cnbit个字节，半个中文显示一个)
    public static String subString(String str, int len, int cn_bit) {
        if (cn_bit <= 0) {
            throw new RuntimeException("参数有误,cn_bit不能小于等于0");
        }
        if (str.length() < len / cn_bit)
            return str;
        int count = 0;
        StringBuffer sb = new StringBuffer();
        String[] ss = str.split("");
        for (int i = 1; i < ss.length; i++) {
            count += ss[i].getBytes().length > 1 ? cn_bit : 1;
            sb.append(ss[i]);
            if (count >= len)
                break;
        }
        return (sb.toString().length() < str.length()) ? sb.toString() : str;
    }

    public static String UTF8String(String para, String bm) throws UnsupportedEncodingException {
        String msg = "";
        if (flag) {
            String encode = getEncoding(para);
            if (encode.equals("ISO-8859-1")) {
                msg = new String(para.getBytes("ISO-8859-1"), bm);
            } else {
                msg = para;
            }
        } else {
            msg = para;
        }
        return msg;
    }

    public static String getEncoding(String str) {
        String encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        return "";
    }

    /**
     * 获取6位随机数.
     */
    public static String getLastSixNum() {
        int count = 0;
        char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer();
        Random r = new Random();
        while (count < 6) {
            int i = Math.abs(r.nextInt(10));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }

    /**
     * 动态拼接字符串
     * zeroPrefix:向前补0
     * zeroSuffix:向后补0
     * BlankSuffix：向后补空格
     * BlankPrefix：向前补空格
     *
     * @param content       要拼接的字符串
     * @param type          zeroPrefix:向前补0、zeroSuffix:向后补0、BlankSuffix：向后补空格、BlankPrefix：向前补空格
     * @param contentLength 要拼接的字符串的自身的长度
     * @param length        拼接完成的字符串的长度
     * @return <pre>
     */
    public static String getString(String content, String type, int contentLength, int length) {
        StringBuffer buffer = new StringBuffer();
        if ("zeroPrefix".equals(type)) {
            if (contentLength < length) {
                for (int i = 0; i < (length - contentLength); i++) {
                    buffer.append("0");
                }
                content = buffer.toString() + content;
            }
        }
        if ("zeroSuffix".equals(type)) {
            if (contentLength < length) {
                for (int i = 0; i < (length - contentLength); i++) {
                    buffer.append("0");
                }
                content = content + buffer.toString();
            }
        }
        if ("BlankSuffix".equals(type)) {
            if (contentLength < length) {
                for (int i = 0; i < (length - contentLength); i++) {
                    buffer.append(" ");
                }
                content = content + buffer.toString();
            }
        }
        if ("BlankPrefix".equals(type)) {
            if (contentLength < length) {
                for (int i = 0; i < (length - contentLength); i++) {
                    buffer.append(" ");
                }
                content = buffer.toString() + content;
            }
        }
        return content;
    }

    /**
     * 验证是否是数字类型
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^(([1-9]\\d*)|([0]))(\\.(\\d){0,2})?$");
        Matcher match = pattern.matcher(str);
        return match.matches();
    }

    /**
     * 获取日期的前/后 n天的日期
     *
     * @param nowDate
     * @param n
     * @return
     */
    public static String getLastDay(String nowDate, int n) {
        String newDate = "";
        try {
            Date dateNow = new SimpleDateFormat("yyyyMMdd").parse(nowDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateNow);
            calendar.add(Calendar.DATE, n);
            newDate = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * 比较日期大小
     *
     * @param date1
     * @param date2
     */
    public static int compareDate(String date1, String date2) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date d1 = sdf.parse(date1);
            Date d2 = sdf.parse(date2);
            if (d1.getTime() > d2.getTime()) {
                return 1;
            }
            if (d1.getTime() < d2.getTime()) {
                return -1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
