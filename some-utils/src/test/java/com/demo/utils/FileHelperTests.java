package com.demo.utils;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.utils.FileHelper;

public class FileHelperTests {
	@Test
	public void getFileCRCCodeTest() {
		File file = new File("D:\\a.txt");
		try {
			String crc = FileHelper.getFileCRCCode(file);
			System.out.println(crc);//b1a8c371
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getStringCRCCodeTest() {
		try {
			String result = FileHelper.getStringCRCCode("456");
			System.out.println(result);//b1a8c371
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void concatTest() {
		String basePath = "D:\\";
		String fileName = "a.txt";
		String result = FileHelper.concat(basePath, fileName);
		System.out.println(result);
	}
	
	@Test
	public void getBaseNameTest() {
		String fileName = "D:\\test\\bookworm-react\\src\\actions\\auth.js";
		String result = FileHelper.getBaseName(fileName);
		System.out.println(result);
	}
	
	@Test
	public void getFileNameTest() {
		String fileName = "D:\\test\\bookworm-react\\src\\actions\\auth.js";
		String result = FileHelper.getFileName(fileName);
		System.out.println(result);
	}
	
	@Test
	public void getFullPathTest() {
		String fileName = "D:\\test\\bookworm-react\\src\\actions\\auth.js";
		String result = FileHelper.getFullPath(fileName);
		System.out.println(result);
	}
	
	@Test
	public void getFullPathNoEndSeparatorTest() {
		String fileName = "D:\\test\\bookworm-react\\src\\actions\\auth.js";
		String result = FileHelper.getFullPathNoEndSeparator(fileName);
		System.out.println(result);
	}
	
	@Test
	public void isExtensionTest() {
		String fileName = "D:\\test\\bookworm-react\\src\\actions\\auth.js";
		boolean result = FileHelper.isExtension(fileName, "jsx");
		System.out.println(result);
	}
	
	@Test
	public void isExtensionTest2() {
		String fileName = "D:\\test\\bookworm-react\\src\\actions\\auth.js";
		String[] exArr = { "js", "jsx" };
		boolean result = FileHelper.isExtension(fileName, exArr);
		System.out.println(result);
	}
	
	@Test
	public void normalizeTest() {
		String fileName = "D:\\test\\bookworm-react\\src\\actions\\auth.js";
		String result = FileHelper.normalize(fileName);
		System.out.println(result);
	}
	
	@Test
	public void normalizeNoEndSeparatorTest() {
		String fileName = "D:\\test\\bookworm-react\\src\\actions\\auth.js";
		String result = FileHelper.normalizeNoEndSeparator(fileName);
		System.out.println(result);
	}
	
	@Test
	public void separatorsToUnixTest() {
		String fileName = "D:\\test\\bookworm-react\\src\\actions\\auth.js";
		String result = FileHelper.separatorsToUnix(fileName);
		System.out.println(result);
	}
	
	@Test
	public void separatorsToWindowsTest() {
		String fileName = "D:\\test\\bookworm-react\\src\\actions\\auth.js";
		String result = FileHelper.separatorsToWindows(fileName);
		System.out.println(result);
	}
	
	@Test
	public void separatorsToSystemTest() {
		String fileName = "D:\\test\\bookworm-react\\src\\actions\\auth.js";
		String result = FileHelper.separatorsToSystem(fileName);
		System.out.println(result);
	}
	
	@Test
	public void getExtensionTest() {
		String fileName = "D:\\test\\bookworm-react\\src\\actions\\auth.js";
		String result = FileHelper.getExtension(fileName);
		System.out.println(result);
	}
	
	@Test
	public void removeExtensionTest() {
		String fileName = "D:\\test\\bookworm-react\\src\\actions\\auth.js";
		String result = FileHelper.removeExtension(fileName);
		System.out.println(result);
	}
	
	@Test
	public void cleanDirectoryTest() {
		File directory = new File("D:\\test\\ax");
		boolean result = FileHelper.cleanDirectory(directory);
		System.out.println(result);
	}
	
	@Test
	public void copyDirectoryTest() {
		File srcDir = new File("D:\\test\\ax\\a");
		File destDir = new File("D:\\test\\ax\\b");
		boolean result = FileHelper.copyDirectory(srcDir, destDir);
		System.out.println(result);
	}
	
	@Test
	public void copyDirectoryTest2() {
		String srcDir = "D:\\test\\ax\\a";
		String destDir = "D:\\test\\ax\\b";
		boolean result = FileHelper.copyDirectory(srcDir, destDir);
		System.out.println(result);
	}
	
	@Test
	public void copyDirectoryTest3() {
		File srcDir = new File("D:\\test\\ax\\a");
		File destDir = new File("D:\\test\\ax\\b");
		boolean result = FileHelper.copyDirectory(srcDir, destDir, true);
		System.out.println(result);
	}
	
	@Test
	public void copyDirectoryToDirectoryTest() {
		File srcDir = new File("D:\\test\\ax");
		File destDir = new File("D:\\test\\ax");
		boolean result = FileHelper.copyDirectoryToDirectory(srcDir, destDir);
		System.out.println(result);
	}
	
	@Test
	public void copyDirectoryToDirectoryTest2() {
		String srcDir = "D:\\test\\ax";
		String destDir = "D:\\test\\ax";
		boolean result = FileHelper.copyDirectoryToDirectory(srcDir, destDir);
		System.out.println(result);
	}
	
	@Test
	public void copyFileTest() {
		File srcDir = new File("D:\\test\\ax\\a\\a.txt");
		File destDir = new File("D:\\test\\ax\\b\\a.txt");
		boolean result = FileHelper.copyFile(srcDir, destDir);
		System.out.println(result);
	}
	
	@Test
	public void copyFileTest2() {
		String srcDir = "D:\\test\\ax\\a\\a.txt";
		String destDir = "D:\\test\\ax\\b\\a.txt";
		boolean result = FileHelper.copyFile(srcDir, destDir);
		System.out.println(result);
	}
	
	@Test
	public void copyFileTest3() {
		File srcDir = new File("D:\\test\\ax\\a\\a.txt");
		File destDir = new File("D:\\test\\ax\\b\\a.txt");
		boolean result = FileHelper.copyFile(srcDir, destDir, true);
		System.out.println(result);
	}
	
	@Test
	public void copyFileToDirectoryTest() {
		File srcDir = new File("D:\\test\\ax\\a\\a.txt");
		File destDir = new File("D:\\test\\ax\\b");
		boolean result = FileHelper.copyFileToDirectory(srcDir, destDir);
		System.out.println(result);
	}
	
	@Test
	public void copyFileToDirectoryTest2() {
		String srcDir = "D:\\test\\ax\\a\\a.txt";
		String destDir = "D:\\test\\ax\\b";
		boolean result = FileHelper.copyFileToDirectory(srcDir, destDir);
		System.out.println(result);
	}
	
	@Test
	public void deleteDirectoryTest() {
		String dir = "D:\\test\\ax\\a";
		boolean result = FileHelper.deleteDirectory(dir);
		System.out.println(result);
	}
	
	@Test
	public void deleteFile() {
		String dir = "D:\\test\\ax\\b";
		boolean result = FileHelper.deleteFile(dir);
		System.out.println(result);
	}
	
	@Test
	public void createDirectory() {
		String dir = "D:\\test\\ax\\b";
		boolean result = FileHelper.createDirectory(dir);
		System.out.println(result);
	}
	
	@Test
	public void readFileToByteArrayTest() {
		String file = "D:\\test\\ax\\a\\a.txt";
		byte[] result = FileHelper.readFileToByteArray(file);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
	
	@Test
	public void readFileToStringTest() {
		String file = "D:\\test\\ax\\a\\a.txt";
		String result = FileHelper.readFileToString(file);
		System.out.println(result);
	}
	
	@Test
	public void readFileToStringTest2() {
		String file = "D:\\test\\ax\\a\\a.txt";
		String result = FileHelper.readFileToString(file, "GBK");
		System.out.println(result);
	}
	
	@Test
	public void readLinesTest() {
		String file = "D:\\test\\ax\\a\\a.txt";
		List<?> result = FileHelper.readLines(file);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
	}
	
	@Test
	public void readLinesTest2() {
		String file = "D:\\test\\ax\\a\\a.txt";
		List<?> result = FileHelper.readLines(file, "GBK");
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
	}
	
	@Test
	public void sizeOfDirectoryTest() {
		String dir = "D:\\test\\ax";
		Long result = FileHelper.sizeOfDirectory(dir);
		System.out.println(result);
	}
	
	@Test
	public void writeToFileTest() {
		String file = "D:\\test\\ax\\a\\b.txt";
		byte[] data = FileHelper.readFileToByteArray("D:\\test\\ax\\a\\a.txt");
		boolean result = FileHelper.writeToFile(file, data);
		System.out.println(result);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
