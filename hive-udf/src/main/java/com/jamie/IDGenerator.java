package com.jamie;

import com.baomidou.mybatisplus.core.toolkit.Sequence;

public class IDGenerator {
    private static Sequence sequence = new Sequence();
    public static long nextId(){
        return sequence.nextId();
    }
}
