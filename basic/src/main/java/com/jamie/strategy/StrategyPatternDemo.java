package com.jamie.strategy;

public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context();
        System.out.println(context.executeOperation(1, 2, 3));
        System.out.println(context.executeDoubleOperation(1, 2, 3));


        System.out.println(context.executeOperation(2, 8, 3));
        System.out.println(context.executeDoubleOperation(2, 8, 3));
    }
}