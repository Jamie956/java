package com.example.socket;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("Start Server");

        ServerSocket server = new ServerSocket(8080);
        Socket socket = server.accept();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        System.out.println(in.readUTF());

        in.close();
        server.close();
        socket.close();
    }
}
