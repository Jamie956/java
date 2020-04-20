package com.example.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	public static void main(String args[]) throws Exception {
		Socket socket = new Socket("127.0.0.1", 8080);
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		out.writeUTF("say hi from client");

		out.close();
		socket.close();
	}
}
