package com.example;

import java.io.IOException;

public class ExceptionTest {

    /**
     * 运行时异常
     * 1.比如校验数据不正确，在运行时抛出异常
     *
     * 受检异常
     * 1.编译时检查，需要捕获的异常，可能发生的异常
     */
    public static void main(String[] args) {
//        throw new RuntimeException();
//        throw new IndexOutOfBoundsException("111");

        try {
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
