package com.example;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Demo {
	
	public void write() throws Exception {
		File xlsFile = new File("jxl.xls");
		WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
		WritableSheet sheet1 = workbook.createSheet("mySheet1", 0);

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				sheet1.addCell(new Label(col, row, "data" + row + col));
			}
		}

		workbook.write();
		workbook.close();
	}

	public void write2() throws Exception {
		File xlsFile = new File("jxl.xls");
		WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
		WritableSheet sheet1 = workbook.createSheet("mySheet1", 0);
		WritableSheet sheet2 = workbook.createSheet("mySheet2", 0);

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				sheet1.addCell(new Label(col, row, "data" + row + col));
				sheet2.addCell(new Label(col, row, "2data" + row + col));
			}
		}

		workbook.write();
		workbook.close();
	}

	public void read() throws Exception {
		File xlsFile = new File("jxl.xls");
		Workbook workbook = Workbook.getWorkbook(xlsFile);
		Sheet[] sheets = workbook.getSheets();
		if (sheets != null) {
			for (Sheet sheet : sheets) {
				System.out.println("sheet => " + sheet.getName());
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
