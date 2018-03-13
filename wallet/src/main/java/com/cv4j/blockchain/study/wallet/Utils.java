package com.cv4j.blockchain.study.wallet;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by tony on 2018/3/13.
 */
public class Utils {

    /**
     * sha256加密算法
     * @param data
     * @return
     */
    public static byte[] sha256(byte[] data) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 数组转换成十六进制字符串
     *
     * @param bArray
     * @return HexString
     */
    public static String bytesToHexString(byte[] bArray) {

        StringBuilder sb = new StringBuilder(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 两个byte[]数组相加
     * @param data1
     * @param data2
     * @return
     */
    public static byte[] add(byte[] data1, byte[] data2) {

        byte[] result = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, result, 0, data1.length);
        System.arraycopy(data2, 0, result, data1.length, data2.length);

        return result;
    }
}
