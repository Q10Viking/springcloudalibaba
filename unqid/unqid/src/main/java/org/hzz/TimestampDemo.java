package org.hzz;

public class TimestampDemo {
    public static void main(String[] args) {
        long timeMillis = System.currentTimeMillis();
        String bit = Long.toBinaryString(timeMillis);
        System.out.println(bit);
        System.out.println(bit.length());
    }
}
/**
 * 11000011011111111100101011110100011100000
 * 41
 */