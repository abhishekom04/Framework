package com.Agent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.TestException;
import org.testng.annotations.DataProvider;

public class ReadDataFromExcel {

	static String dataString = null;
	
	public static String getData(String testcaseName,String actualData) {		

		try {
			String sheetName = ReadingConfigFile.readingConfigFile("SheetName");
			String testDataPath = ReadingConfigFile.readingConfigFile("TestDataPath");
			
			File file = new File (System.getProperty("user.dir")+"\\TestData\\"+testDataPath);
			FileInputStream fis = new  FileInputStream (file);
			Workbook workbook =WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet(sheetName);

			int totalRows = sheet.getLastRowNum();
			//System.out.println("Total Rows are:" +totalRows);
			Row rowCells = sheet.getRow(0);
			int totalCols = rowCells.getLastCellNum();

			DataFormatter format = new  DataFormatter();
			String paramName = null;
			
			String testcaseExcel=null;	
			
			actualData = actualData.substring(1, actualData.length()-1);
			//System.out.println("before : "+actualData+"before");
			actualData=actualData.trim();
			//System.out.println("after : "+actualData+"after");
			
			for (int i=1; i<=totalRows;i++) {
							
				
				if(sheet.getRow(i).getCell(0)==null) {
					continue;
				}
				else {
					
					testcaseExcel = sheet.getRow(i).getCell(0).getStringCellValue();
					testcaseExcel =testcaseExcel.trim();
					System.out.println("Actual test case name :::::::::::::: "+testcaseExcel);
					System.out.println("Test case name from Excel ::::: "+testcaseExcel);
					
					if (testcaseName.equals(testcaseExcel)) {
						
						
						System.out.println(testcaseName+" :::::: "+"Test case found  ::::: "+testcaseExcel);
						
					  for(int j=1;j<=totalCols;j++) {
						
					     paramName = sheet.getRow(i).getCell(j).getStringCellValue();
					     //System.out.println(actualData+" ::::::::::::: "+paramName);
						 if (paramName.equals(actualData)) {
							 
							// System.out.println(actualData+" ::::::::::::: "+paramName);
						 //dataString = format.formatCellValue(sheet.getRow(i+1).getCell(j));
					  CellType cell_type = sheet.getRow(i+1).getCell(j).getCellType();
					  System.out.println("Cell type : "+cell_type);
					  switch(cell_type) {					  
										  
					  case NUMERIC:
						  
						  Double d = sheet.getRow(i+1).getCell(j).getNumericCellValue();
						  
						  dataString = Double.toString(d);
						  break;
						  		
					  case BOOLEAN:
						  
						  boolean b = sheet.getRow(i+1).getCell(j).getBooleanCellValue();
						  dataString = Boolean.toString(b);
						  break;
						  
					  default:
						 
						 dataString = sheet.getRow(i+1).getCell(j).getStringCellValue();
						 break;
						 
					  }
					  break;
						 }
						 
						 else {
							 continue;
						 }
		
				 }
				break;
				}
				else {
					continue;
				}				
				
			}
			
		}
			
			workbook.close();
			fis.close();		
			
		}
		catch(Exception e) {
			
			throw new TestException("Test data genration error");
		}
		
		return  dataString;
	  }
	}
