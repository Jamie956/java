package com.jamie.utils;

/**
 * @Author: Zjm
 * @Date: 2021/2/5 11:00
 */
public class IDGenerator {
    private static Sequence sequence = new Sequence();
    public static long nextId(){
        return sequence.nextId();
    }
}