package com.jugu.www.pcbonlinev2.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA256Util {
    /**
     * 利用java原生的摘要实现SHA256加密
     *
     * @param str 加密后的报文
     * @return
     */
    public static String getSHA256StrJava(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 将byte转为16进制
     *
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String key = "1111";
        String test1 = SHA256Util.getSHA256StrJava("123456@qq.com" + key);
        String test2 = SHA256Util.getSHA256StrJava("123456@qq.com" + key);


        System.out.println(test1);
        System.out.println(test2);
//
//        System.out.println(test1.equals(test2));


        String s = "[w110000007A, w110000006A]";
        String ssss = s.replace('[', ' ').replace(']', ' ').trim();
        String[] sss = ssss.split(",");
        System.out.println(Arrays.toString(sss));

        char[] chars = s.toCharArray();
        System.out.println(Arrays.toString(chars));
    }
}
