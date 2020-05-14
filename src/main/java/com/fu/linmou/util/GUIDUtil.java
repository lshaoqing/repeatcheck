package com.fu.linmou.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/8 14:18
 * @Version: 1.0
 */
public class GUIDUtil {

    private GUIDUtil() {}


    /**
     * 描述： java内置标准MD5算法
     * @param input
     * @return
     */
    public static String getMD5(String input) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = input.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }


        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString().toUpperCase();
    }

    /**
     * 描述：X-MD5，经过X处理的非标准MD5
     * @param input
     * @return
     */
    public static String getXMD5(String input) {
        String xMD5 = getMD5(input);
        xMD5 = getMD5(xMD5.substring(16, 32).replace("1", "A") + input + xMD5.substring(1, 16).replace("B", "8"));

        return xMD5;
    }

    /**
     * 获取 GUID。
     * @param length 36：标准GUID；32：Oracle 版 GUID（匹配Oracle SYS_GUID()函数）。
     * @return
     */
    public static String getGUID(int length) {
        String xString = String.valueOf(System.currentTimeMillis()) + String.valueOf(Math.random());
        char[] CHARS = (new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()) + getMD5(xString)).toCharArray();
        char[] guid = new char[36];

        guid[8] = guid[13] = guid[18] = guid[23] = '-';
        guid[14] = '4';

        int j = 0;
        for (int i = 0; i < 36; i++) {
            if (guid[i] == 0) {
                guid[i] = CHARS[j];
                j++;
            }
        }
        if (length == 32) {
            return new String(guid).replace("-", "");
        }

        return new String(guid);
    }

    /**
     * 描述：获取 Oracle 版 GUID。
     * @return  SYS_GUID() 长度32
     */
    public static String getGUID() {
        return getGUID(36);
    }

    /**
     * 判断是否为 GUID 格式
     * @param str
     * @return
     */
    public static boolean IsGUID(String str) {
        char[] guid = str.toCharArray();
        if (guid.length != 36 || guid[8] != '-' || guid[13] != '-' || guid[18] != '-' || guid[23] != '-' || guid[14] != '4') {
            return false;
        }
        return true;
    }

}
