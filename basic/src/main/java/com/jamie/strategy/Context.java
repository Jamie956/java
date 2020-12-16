package com.jamie.strategy;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private static final Map<Integer, Strategy> map = new HashMap<>();

    static {
        map.put(1, new OperationAdd());
        map.put(2, new OperationMultiply());
        map.put(3, new OperationSubtract());
    }

    public int executeOperation(int type, int num1, int num2){
        return map.get(type).doOperation(num1, num2);
    }

    public int executeDoubleOperation(int type, int num1, int num2){
        return map.get(type).doDoubleOperation(num1, num2);
    }
}