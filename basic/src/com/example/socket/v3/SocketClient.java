package com.example.socket.v3;

import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
	public static void main(String args[]) throws Exception {
		String host = "127.0.0.1";
		int port = 55533;
		Socket socket = new Socket(host, port);
		OutputStream outputStream = socket.getOutputStream();

		// 消息长度优先发送出去
		String message = "msg1";
		byte[] sendBytes = message.getBytes("UTF-8");
		outputStream.write(sendBytes.length >> 8);
		outputStream.write(sendBytes.length);
		outputStream.write(sendBytes);
		outputStream.flush();

		message = "msg2";
		sendBytes = message.getBytes("UTF-8");
		outputStream.write(sendBytes.length >> 8);
		outputStream.write(sendBytes.length);
		outputStream.write(sendBytes);
		outputStream.flush();

		message = "msg3";
		sendBytes = message.getBytes("UTF-8");
		outputStream.write(sendBytes.length >> 8);
		outputStream.write(sendBytes.length);
		outputStream.write(sendBytes);
		outputStream.flush();

		outputStream.close();
		socket.close();
	}
}
