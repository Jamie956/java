package com.jamie.aop;


public class Halo implements IHalo {
    /**
     * 被增强的方法
     */
    @Override
    public void greeting() {
        System.out.println("greeting");
    }
}