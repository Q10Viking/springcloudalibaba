package org.hzz;

public class TestRuntimeException {

    public static void main(String[] args) {
        try{
            throw new RuntimeException("抛出一个Runtime异常");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("main运行结束");
    }
    /**
     * 抛出一个Runtime异常
     * main运行结束
     */
}
