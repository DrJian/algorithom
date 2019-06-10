package com.hotj.java.algorithom.actuator.log4j;

/**
 * @author hongjian@youzan.com
 * @date 2019/3/29
 */
public class TestMain {
    public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            System.out.println("runtime"
                    + " Class:" + new Throwable().getStackTrace()[0].getClassName()
                    + " Line:" + new Throwable().getStackTrace()[0].getLineNumber());
            throw e;
        } catch (Exception e) {
            System.out.println("root"
                    + " Class:" + new Throwable().getStackTrace()[0].getClassName()
                    + " Line:" + new Throwable().getStackTrace()[0].getLineNumber());
        }
    }
}
