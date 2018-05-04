package com.example;

import java.io.File;
import java.io.IOException;

public class File1 {
	public static void main(String[] args) throws IOException {
		//从绝对路径获取抽象路径
		File pathname = new File("D:\\a.txt");
		//从相对路径获取抽象路径
		File pathname2 = new File("D:\\a.txt");
		//是否是文件夹
		boolean isDirectory = new File("D:\\a").isDirectory();
		//抽象路径是否存在
		boolean exists = new File("D:\\a.txt").exists();
		//创建文件夹
		boolean isMkdir = new File("D:\\a").mkdir();
		//创建文件
		boolean isCreateFile = new File("D:\\a.txt").createNewFile();
		//是否是文夹
		new File("D:\\a.txt").isFile();
		//创建层级文件夹
		boolean isMkdirs = new File("D:\\b\\bb\\bbb").mkdirs();
		//重命名
		new File("E:\\c.txt").renameTo(new File("E:\\d.txt"));
		//删除文件
		new File("E:\\f.txt").delete();
		//获取文件名或文件夹名
		new File("E:\\f.txt").getName();
		//获取绝对路径
		new File("E:\\f.txt").getAbsolutePath();
		//用户当前工作目录
		System.getProperty("user.dir");
		//获取文件和文件夹数组
		String[] fileArr = new File("E:\\a").list();
		//获取抽象路径数组
		File[] fileArr2 = new File("E:\\a").listFiles();
	}
}
