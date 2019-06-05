package com.example;

import java.util.function.Consumer;

public class _Consumer {

    public static void main(String[] args) {
        Consumer<String> consumer = (String x) -> System.out.println(x);
        consumer.accept("w");
    }

}
