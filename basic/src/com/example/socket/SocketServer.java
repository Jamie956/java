package com.example.socket;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	public static void main(String[] args) throws Exception {
		int port = 55533;
		ServerSocket server = new ServerSocket(port);

		System.out.println("Server Waiting");
		Socket socket = server.accept();
		InputStream inputStream = socket.getInputStream();
		byte[] bytes = new byte[1024];
		int len;
		StringBuilder sb = new StringBuilder();
		while ((len = inputStream.read(bytes)) != -1) {
			// 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
			sb.append(new String(bytes, 0, len, "UTF-8"));
		}
		System.out.println("Server printer: " + sb);
		inputStream.close();
		socket.close();
		server.close();
	}
}
