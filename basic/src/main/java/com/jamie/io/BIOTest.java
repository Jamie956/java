package com.jamie.io;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOTest {
    /**
     * 阻塞IO，服务端
     *
     * telnet 127.0.0.1 6666
     */
    @Test
    public void server() throws IOException {

        //创建线程池
        ExecutorService pool = Executors.newCachedThreadPool();

        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);

        //accept 阻塞监听 客户端连接
        while (true) {
            //获取当前线程
            System.out.println("等待下一个客户端连接...");
//            System.out.println(Thread.currentThread().getId() + " # " + Thread.currentThread().getName());
            final Socket socket = serverSocket.accept();
            System.out.println("连接成功!");
//            System.out.println(Thread.currentThread().getId() + " # " + Thread.currentThread().getName());

            //分配线程与新连接通信
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId() + " # " + Thread.currentThread().getName());

                    //获取socket 输入流
                    InputStream in = null;
                    try {
                        in = socket.getInputStream();

                        //读取输入流
                        byte[] bytes = new byte[1024];
                        while (true) {
                            int length = in.read(bytes);
                            if (length != -1) {
                                //输出客户端输入流
                                System.out.println(new String(bytes, 0, length));
                            } else {
                                break;
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }


    @Test
    public void client() throws IOException {
        Socket socket = new Socket("127.0.0.1", 6666);
        while (true){

        }
    }
}
