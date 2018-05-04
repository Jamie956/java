package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

public class IO1 {
	@Test
	public void read() {
		// 获取抽象路径
		File file = new File("D:\\a.txt");
		// 创建抽象路径的输入流实例
		try (InputStream in = new FileInputStream(file)) {
			while (true) {
				// 读取输入流
				int b = in.read();
				if (b == -1) {
					break;
				} else {
					System.out.println(b);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void write() {
		// 获取抽象路径
		File file = new File("D:\\a.txt");
		// 创建抽象路径的输出流实例
		try (OutputStream out = new FileOutputStream(file);) {
			byte[] bytes = "hi".getBytes();
			// 写入输出流
			out.write(bytes);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Test
	public void readAndWrite() {
		File src = new File("D:\\a.txt");
		File dest = new File("D:\\b.txt");

		try (InputStream in = new FileInputStream(src); OutputStream out = new FileOutputStream(dest);) {
			int len = -1;
			byte[] b = new byte[20];
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
