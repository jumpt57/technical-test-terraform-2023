package com.myapp.demo.urlshortener.utils;

/**
 * Class to handle Base 62 conversion to and from base 10
 * To optimize length of data
 */
public class Base62 {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int BASE = ALPHABET.length();

    private Base62() {
    }

    public static String fromBase10(long i) {
        StringBuilder sb = new StringBuilder();
        if (i == 0) {
            return "a";
        }
        while (i > 0) {
            i = fromBase10(i, sb);
        }
        return sb.reverse().toString();
    }

    private static long fromBase10(long i, final StringBuilder sb) {
        int rem = (int) (i % BASE);
        sb.append(ALPHABET.charAt(rem));
        return i / BASE;
    }

    public static long toBase10(String str) {
        return toBase10(new StringBuilder(str).reverse().toString().toCharArray());
    }

    private static long toBase10(char[] chars) {
        long n = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            n += toBase10(ALPHABET.indexOf(chars[i]), i);
        }
        return n;
    }

    private static long toBase10(long n, int pow) {
        return n * (int) Math.pow(BASE, pow);
    }
}