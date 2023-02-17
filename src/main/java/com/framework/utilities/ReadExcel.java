package com.framework.utilities;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	public static String [][] readData(String filename,String sheetname){
		String [][] data = null;
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\"+filename);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet(sheetname);
			int totalRows= sh.getPhysicalNumberOfRows();
			int totalCols= sh.getRow(0).getPhysicalNumberOfCells();
			data = new String [totalRows-1][totalCols];
			for(int r=1; r<totalRows;r++) {
				for(int c=0; c<totalCols;c++) {
					data[r-1][c] =sh.getRow(r).getCell(c).getStringCellValue();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return data;		
	}

}
