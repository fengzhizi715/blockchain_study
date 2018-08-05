package com.cv4j.blockchain.study.scrypt;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/**
 * Created by tony on 2018/8/5.
 */
public class Test {

    public static void main(String[] args) {

        byte[] password = new byte[0];
        try {
            password = "123456".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] salt = new byte[0];
        try {
            salt = "abcdefg".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();

        byte[] scrypt = new byte[0];
        try {
            scrypt = Scrypt.scrypt(password,salt,131072,8,1,32);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        String str = HashUtils.encodeBase64(scrypt);

        long end = System.currentTimeMillis();
        System.out.println("加密后的值:"+str);
        System.out.println("花费时间:"+(end-start)+" ms");
    }
}
