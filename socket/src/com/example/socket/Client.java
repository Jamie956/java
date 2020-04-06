package com.example.socket;

import java.io.OutputStream;
import java.net.Socket;

public class Client {
	public static void main(String args[]) throws Exception {
		Socket socket = new Socket("127.0.0.1", 8080);
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write("Say hi to Server".getBytes("UTF-8"));
		outputStream.close();
		socket.close();
	}
}
