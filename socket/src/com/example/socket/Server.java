package com.example.socket;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(8080);

		Socket socket = server.accept();
		InputStream inputStream = socket.getInputStream();
		byte[] bytes = new byte[1024];
		int len;
		StringBuilder sb = new StringBuilder();
		while ((len = inputStream.read(bytes)) != -1) {
			// S/C统一编码
			sb.append(new String(bytes, 0, len, "UTF-8"));
		}
		System.out.println(sb);
		
		inputStream.close();
		socket.close();
		server.close();
	}
}
