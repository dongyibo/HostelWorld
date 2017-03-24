package com.nju.hostelworld.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by dongyibo on 2017/1/8.
 */
public class MD5 {

    private MD5(){}

    /**
     * MD5加密器
     * @param plainText
     * @return
     */
    public static String getMd5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("!!!!!!!!");
            e.printStackTrace();
            return null;
        }

    }

}
