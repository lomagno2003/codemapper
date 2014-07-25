package com.clomagno.codemapper;

import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class MapperFactory {
	private Integer findHeaderIndex(HSSFSheet sheet, String header){
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
	
	public Mapper getMapperFromFiles(HSSFWorkbook configuration){
		Mapper result = null;
		
		HSSFSheet generalConfiguration = configuration.getSheet("Configuracion General");
        
        Integer distributorsIndex = findHeaderIndex(generalConfiguration,"Distribuidor");
        Integer suffixIndex = findHeaderIndex(generalConfiguration,"Sufijo");
        
		
		//Iterate through each rows one by one
        Iterator<Row> rowIterator = generalConfiguration.iterator();
        rowIterator.next();
        
        while (rowIterator.hasNext()) 
        {
            Row row = rowIterator.next();

            //TODO Create Configuration
            Cell distributorCell = row.getCell(distributorsIndex);
            result.getConfigurations().put(distributorCell.getStringCellValue(), new Configuration());
            
            //TODO Set Suffix
            Cell suffixCell = row.getCell(suffixIndex);
            result.getConfigurations().get(distributorCell.getStringCellValue()).setSuffix(suffixCell.getStringCellValue());
            
            //TODO Set ExternalCode column name
            
            //TODO Parse configuration
        }
		
		
		return result;
	}
}
