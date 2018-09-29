package com.example.socket.v3;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	public static void main(String[] args) throws Exception {
		int port = 55533;
		ServerSocket server = new ServerSocket(port);

		System.out.println("Server waiting");
		Socket socket = server.accept();
		InputStream inputStream = socket.getInputStream();
		byte[] bytes;
		// 因为可以复用Socket且能判断长度，所以可以一个Socket用到底
		while (true) {
			// 首先读取两个字节表示的长度
			int first = inputStream.read();
			// 如果读取的值为-1 说明到了流的末尾，Socket已经被关闭了，此时将不能再去读取
			if (first == -1) {
				break;
			}
			int second = inputStream.read();
			int length = (first << 8) + second;
			bytes = new byte[length];
			inputStream.read(bytes);
			System.out.println("Server printer: " + new String(bytes, "UTF-8"));
		}
		inputStream.close();
		socket.close();
		server.close();
	}
}