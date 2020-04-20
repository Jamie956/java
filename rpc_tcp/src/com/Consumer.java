package com;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Consumer {

    public Object invoke(String clazz, String methodName) {
        try {
            Socket socket = new Socket("127.0.0.1", 6666);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            out.writeUTF(clazz);
            out.writeUTF(Hello.class.getMethod(methodName, String.class).getName());
            out.writeObject(Hello.class.getMethod(methodName, String.class).getParameterTypes());
            Object[] arg = {"tom"};
            out.writeObject(arg);

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Object ret = in.readObject();
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
