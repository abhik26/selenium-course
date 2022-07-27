package com.example.spreedsheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SpreadsheetDemo {

	public static void main(String[] args) throws IOException, URISyntaxException {
		FileInputStream fis = new FileInputStream(ClassLoader
				.getSystemResource("data.xlsx").toURI().getPath());
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		for (CellType cellType : CellType.values()) {
			System.out.print(cellType + " ");
		}
		System.out.println();
		
		int sheets = workbook.getNumberOfSheets();
		
		for (int i=0; i<sheets; i++) {
			if (workbook.getSheetName(i).equals("TestData")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				
				Iterator<Row> rows = sheet.rowIterator();
				
				Row headerRow = rows.next();
				Iterator<Cell> headerCells = headerRow.cellIterator();
				
				int k=0;
				int column = -1;
				
				while (headerCells.hasNext()) {
					Cell headerCell = headerCells.next();
					
					if (headerCell.getStringCellValue().equals("Test Cases")) {
						column = k;
						break;
					}
					
					k++;
				}
				
				while (rows.hasNext()) {
					Row dataRow = rows.next();
					
					if (dataRow.getCell(column).getStringCellValue().equals("Purchase")) {
						Iterator<Cell> requiredCells = dataRow.cellIterator();
						
						while (requiredCells.hasNext()) {
							Cell cell = requiredCells.next();
							
							switch (cell.getCellType()) {
								case STRING:
									System.out.print(cell.getStringCellValue() + " ");
									break;
								case NUMERIC:
									System.out.print(cell.getNumericCellValue() + " ");
									break;
								case BOOLEAN:
									System.out.print(cell.getBooleanCellValue() + " ");
									break;
								default:
									break;
							}
						}
						
						break;
					}
				}
			}
		}
	}
	
}
