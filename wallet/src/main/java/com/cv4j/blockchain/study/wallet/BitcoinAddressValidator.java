package com.cv4j.blockchain.study.wallet;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by tony on 2018/3/12.
 */
public class BitcoinAddressValidator {

    private static final String ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";

    public static boolean validateBitcoinAddress(String addr) {
        if (addr.length() < 26 || addr.length() > 35)
            return false;
        byte[] decoded = decodeBase58To25Bytes(addr);
        if (decoded == null)
            return false;

        byte[] hash1 = sha256(Arrays.copyOfRange(decoded, 0, 21));
        byte[] hash2 = sha256(hash1);

        return Arrays.equals(Arrays.copyOfRange(hash2, 0, 4), Arrays.copyOfRange(decoded, 21, 25));
    }

    private static byte[] decodeBase58To25Bytes(String input) {
        BigInteger num = BigInteger.ZERO;
        for (char t : input.toCharArray()) {
            int p = ALPHABET.indexOf(t);
            if (p == -1)
                return null;
            num = num.multiply(BigInteger.valueOf(58)).add(BigInteger.valueOf(p));
        }

        byte[] result = new byte[25];
        byte[] numBytes = num.toByteArray();
        System.arraycopy(numBytes, 0, result, result.length - numBytes.length, numBytes.length);
        return result;
    }

    private static byte[] sha256(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) {

        assertBitcoin("1F8GoWchAb56ePqftqRvLf7L729JGQSdqW", true);
    }

    private static void assertBitcoin(String address, boolean expected) {
        boolean actual = validateBitcoinAddress(address);
        if (actual != expected)
            throw new AssertionError(String.format("Expected %s for %s, but got %s.", expected, address, actual));
    }
}
