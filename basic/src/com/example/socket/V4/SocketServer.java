package com.example.socket.V4;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
	public static void main(String args[]) throws Exception {
		int port = 55533;
		ServerSocket server = new ServerSocket(port);
		
		System.out.println("Server waiting");
		// 如果使用多线程，那就需要线程池，防止并发过高时创建过多线程耗尽资源
		ExecutorService threadPool = Executors.newFixedThreadPool(100);

		while (true) {
			Socket socket = server.accept();
			Runnable runnable = () -> {
				try {
					InputStream inputStream = socket.getInputStream();
					byte[] bytes = new byte[1024];
					int len;
					StringBuilder sb = new StringBuilder();
					while ((len = inputStream.read(bytes)) != -1) {
						sb.append(new String(bytes, 0, len, "UTF-8"));
					}
					System.out.println("Server printer: " + sb);
					inputStream.close();
					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
			threadPool.submit(runnable);
		}
	}
}
