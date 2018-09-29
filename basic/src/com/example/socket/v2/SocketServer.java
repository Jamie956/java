package com.example.socket.v2;

import java.io.InputStream;
import java.io.OutputStream;
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
		// 只有当客户端关闭它的输出流的时候，服务端才能取得结尾的-1
		while ((len = inputStream.read(bytes)) != -1) {
			sb.append(new String(bytes, 0, len, "UTF-8"));
		}
		System.out.println("Server printer: " + sb);

		OutputStream outputStream = socket.getOutputStream();
		outputStream.write("Server respond".getBytes("UTF-8"));

		inputStream.close();
		outputStream.close();
		socket.close();
		server.close();
	}
}
