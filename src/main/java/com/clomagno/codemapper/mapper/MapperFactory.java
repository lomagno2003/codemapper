package com.clomagno.codemapper.mapper;

import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class MapperFactory {
	public static String GENERAL_CONFIGURATION_SHEET_NAME = "GCSN";
	public static String DISTRIBUTOR_COLUMN_NAME = "D";
	public static String SUFFIX_COLUMN_NAME = "S";
	public static String EXTERNAL_CODE_COLUMN_NAME = "E";
	
	public static Integer findHeaderIndex(HSSFSheet sheet, String header){
		Integer result = 0;
		
		Iterator<Row> rowIterator = sheet.iterator();
		Row row = rowIterator.next();
        //For each row, iterate through all the columns
        Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) 
        {
            Cell cell = cellIterator.next();
            switch (cell.getCellType()) 
            {
                case Cell.CELL_TYPE_NUMERIC:
                	if(Double.valueOf(cell.getNumericCellValue()).toString().equals(header)){
                    	return result;
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    if(cell.getStringCellValue().equals(header)){
                    	return result;
                    }
                    break;
            }
            
            result++;
        }
		
		return null;
	}
	
	public static Mapper getMapperFromWorkbook(HSSFWorkbook configuration){
		Mapper result = new Mapper();
		
		HSSFSheet generalConfiguration = configuration.getSheet(GENERAL_CONFIGURATION_SHEET_NAME);
        
        Integer distributorsIndex = findHeaderIndex(generalConfiguration,DISTRIBUTOR_COLUMN_NAME);
        Integer suffixIndex = findHeaderIndex(generalConfiguration,SUFFIX_COLUMN_NAME);
        Integer externalColumnNameIndex = findHeaderIndex(generalConfiguration,EXTERNAL_CODE_COLUMN_NAME);
		
        Iterator<Row> rowIterator = generalConfiguration.iterator();
        rowIterator.next();
        
        while (rowIterator.hasNext()) 
        {
            Row row = rowIterator.next();

            Cell distributorCell = row.getCell(distributorsIndex);
            result.getConfigurations().put(distributorCell.getStringCellValue(), new Configuration());
            
            Cell suffixCell = row.getCell(suffixIndex);
            result.getConfigurations().get(distributorCell.getStringCellValue()).setSuffix(suffixCell.getStringCellValue());
            
            Cell externalColumnNameCell = row.getCell(externalColumnNameIndex);
            result.getConfigurations().get(distributorCell.getStringCellValue()).setExternalColumnName(externalColumnNameCell.getStringCellValue());
            
            configureDistributorMappings(
            		result.getConfigurations().get(distributorCell.getStringCellValue()), 
            		configuration.getSheet(distributorCell.getStringCellValue()));
        }
		
		
		return result;
	}
	
	public static void configureDistributorMappings(Configuration configuration, HSSFSheet distributorMappingSheet){
		Integer internalCodeIndex = findHeaderIndex(distributorMappingSheet,Mapper.INTERNAL_CODE_COLUMN_NAME);
		Integer externalCodeIndex = findHeaderIndex(distributorMappingSheet,configuration.getExternalColumnName());
		
        Iterator<Row> rowIterator = distributorMappingSheet.iterator();
        rowIterator.next();
        
		while (rowIterator.hasNext()) 
        {
            Row row = rowIterator.next();

            Cell internalCodeCell = row.getCell(internalCodeIndex);
            Cell externalCodeCell = row.getCell(externalCodeIndex);
            
            System.out.println(internalCodeCell.getStringCellValue());
            System.out.println(externalCodeCell.getStringCellValue());

            configuration.getMappings().put(externalCodeCell.getStringCellValue(), internalCodeCell.getStringCellValue());
        }
	}
}
