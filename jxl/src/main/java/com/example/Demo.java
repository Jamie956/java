package com.example;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Demo {
	
	public static void main(String[] args) throws Exception {
		read();
		
	}
	
	public static void write() throws Exception {
		WritableWorkbook workbook = Workbook.createWorkbook(new File("demo.xls"));
		WritableSheet sheet1 = workbook.createSheet("mySheet1", 0);
		WritableSheet sheet2 = workbook.createSheet("mySheet2", 1);
		
		sheet1.addCell(new Label(0, 0, "row0,col0"));
		sheet1.addCell(new Label(1, 0, "row0,col1"));
		
		sheet2.addCell(new Label(0, 0, "sheet2"));
		
		workbook.write();
		workbook.close();
	}

	public static void read() throws Exception {
		Workbook workbook = Workbook.getWorkbook(new File("demo.xls"));
		Sheet[] sheets = workbook.getSheets();
		
		if (sheets != null) {
			for (Sheet sheet : sheets) {
				System.out.println(sheet.getName());
				
				int rows = sheet.getRows();
				int cols = sheet.getColumns();
				for (int row = 0; row < rows; row++) {
					for (int col = 0; col < cols; col++) {
						System.out.printf("%10s", sheet.getCell(col, row).getContents());
					}
					System.out.println();
				}
			}
		}
		workbook.close();
	}
}
