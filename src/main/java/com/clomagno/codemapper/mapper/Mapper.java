package com.clomagno.codemapper.mapper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Mapper {
	public static String INTERNAL_CODE_COLUMN_NAME = "ICCN";
	public static String MAPPED_SHEET_NAME = "MSN";

	private Map<String,Configuration> configurations;
	
	public Mapper(){
		configurations = new HashMap<String, Configuration>();
	}
	
	public Map<String, Configuration> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(Map<String, Configuration> configurations) {
		this.configurations = configurations;
	}
	
	public HSSFWorkbook doMap(String distributorName, HSSFWorkbook externalWorkbook){
		System.out.println(externalWorkbook.getSheetName(0));
		return doMap(distributorName, externalWorkbook.getSheet(distributorName));
	}

	public HSSFWorkbook doMap(String distributorName, HSSFSheet externalSheet){
		if(!configurations.containsKey(distributorName)){
			throw new NullPointerException("Couldn't find the distributor name");
		}
		
        HSSFWorkbook result = new HSSFWorkbook(); 
        HSSFSheet resultSheet = result.createSheet(MAPPED_SHEET_NAME);
		
		Configuration distributorConfiguration = configurations.get(distributorName);
		
		Integer codeColumnIndex = getCodeColumnIndex(distributorConfiguration, externalSheet);
		if(codeColumnIndex==null){
			throw new NullPointerException("Couldn't find the code column");
		}
		
		copyFirstRow(externalSheet,resultSheet);
		
		Iterator<Row> rowIterator = externalSheet.iterator();
		
		rowIterator.next(); //Ignore the first row
		Integer rowNumber = 1;
		
        while (rowIterator.hasNext()) 
        {
            Row row = rowIterator.next();
            Row newRow = resultSheet.createRow(rowNumber);
            
            String code = distributorConfiguration.getMappings().get(row.getCell(codeColumnIndex).toString()) + distributorConfiguration.getSuffix();

            newRow.createCell(0).setCellValue(code);
            
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();
            Integer columnIndex = 1;
            while (cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();
                
                switch (cell.getCellType()) 
                {
                    case Cell.CELL_TYPE_NUMERIC:
                    	newRow.createCell(columnIndex).setCellValue(cell.getNumericCellValue());
                        break;
                    case Cell.CELL_TYPE_STRING:
                    	newRow.createCell(columnIndex).setCellValue(cell.getStringCellValue());
                        break;
                    default:
                    	throw new IllegalStateException("Type not recognized");
                }
                
                columnIndex++;
                
            }
            
            rowNumber++;
        }
        
		return result;
	}
	
	private void copyFirstRow(HSSFSheet externalSheet, HSSFSheet resultSheet) {
		Iterator<Row> rowIterator = externalSheet.iterator();
		Integer rowNumber = 0;
		
        if (rowIterator.hasNext()) 
        {
            Row row = rowIterator.next();
            Row newRow = resultSheet.createRow(rowNumber);
            
            newRow.createCell(0).setCellValue(INTERNAL_CODE_COLUMN_NAME);

            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();
            Integer columnIndex = 1;
            while (cellIterator.hasNext()) 
            {
                Cell cell = cellIterator.next();
                
                switch (cell.getCellType()) 
                {
                    case Cell.CELL_TYPE_NUMERIC:
                    	newRow.createCell(columnIndex).setCellValue(cell.getNumericCellValue());
                        break;
                    case Cell.CELL_TYPE_STRING:
                    	newRow.createCell(columnIndex).setCellValue(cell.getStringCellValue());
                        break;
                    default:
                    	throw new IllegalStateException("Type not recognized");
                }
                
                columnIndex++;
                
            }
            
            rowNumber++;
        }
	}

	public Integer getCodeColumnIndex(Configuration distributorConfiguration, HSSFSheet externalSheet){
		//Iterate through each rows one by one
        Iterator<Row> rowIterator = externalSheet.iterator();
		
		//Read the columns headers to find the index of the CodeColumn
        Integer codeColumnIndex = null;
		if(rowIterator.hasNext()){
			Row headers = rowIterator.next();
			
			//For each row, iterate through all the columns
            Iterator<Cell> cellIterator = headers.cellIterator();
            codeColumnIndex = 0;
            
            while (cellIterator.hasNext()) 
            {
                Cell cell = cellIterator.next();
                
                //Check the cell type and format accordingly
                if(cell.getStringCellValue().equals(distributorConfiguration.getExternalColumnName())){
                	break;
                };
                
                codeColumnIndex++;
            }
		}
		
		return codeColumnIndex;
	}
}
