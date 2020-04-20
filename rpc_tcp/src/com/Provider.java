package com;

import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Provider {

    public static void main(String[] args) throws IOException {
        System.out.println("start server");
        ServerSocket server = new ServerSocket(6666);
        while (true) {
            try (Socket socket = server.accept(); ObjectInputStream input = new ObjectInputStream(socket.getInputStream()); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {

                String methodName = input.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                Object[] arguments = (Object[]) input.readObject();

                Method method = HelloImpl.class.getMethod(methodName, parameterTypes);
                Object result = method.invoke(HelloImpl.class.newInstance(), arguments);
                output.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}