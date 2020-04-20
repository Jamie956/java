package com;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConsumerProxy<T> implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        try {
            Socket socket = new Socket("localhost", 6666);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            out.writeUTF("com.HelloImpl");
            out.writeUTF(method.getName());
            out.writeObject(method.getParameterTypes());
            out.writeObject(args);

            // 同步阻塞等待响应
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

}