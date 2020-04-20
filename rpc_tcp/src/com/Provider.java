package com;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Provider {

    public static void main(String[] args) {
        System.out.println("start server");
        ServerSocket server = null;
        try {
            server = new ServerSocket(6666);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                try (Socket socket = server.accept(); ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {
                    String clazzPath = in.readUTF();
                    String methodName = in.readUTF();
                    Class<?>[] parameterTypes = (Class<?>[]) in.readObject();
                    Object[] arguments = (Object[]) in.readObject();

                    Method method = Class.forName(clazzPath).getMethod(methodName, parameterTypes);
                    Object result = method.invoke(Class.forName(clazzPath).newInstance(), arguments);
                    output.writeObject(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }


}