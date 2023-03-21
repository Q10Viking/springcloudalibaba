package org.hzz;

public class IntLengthDemo {
    public static void main(String[] args) {
        int i = Integer.MAX_VALUE;
        String str = Integer.toBinaryString(i);
        System.out.println(str);
        System.out.println(str.length());
    }
}
