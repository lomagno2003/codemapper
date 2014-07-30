package com.clomagno.codemapper.mapper.impls;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.clomagno.codemapper.mapper.Configuration;
import com.clomagno.codemapper.mapper.ICell;
import com.clomagno.codemapper.mapper.IRow;
import com.clomagno.codemapper.mapper.ISheet;
import com.clomagno.codemapper.mapper.IWorkbook;
import com.clomagno.codemapper.mapper.Mapper;
import com.clomagno.codemapper.mapper.exceptions.BadHeadersException;
import com.clomagno.codemapper.mapper.exceptions.MapperException;
import com.clomagno.codemapper.mapper.exceptions.UnknowDistributorException;
import com.clomagno.codemapper.mapper.exceptions.UnmappedCodesException;
import com.clomagno.codemapper.mapper.facades.hssf.HSSFSheetFacade;
import com.clomagno.codemapper.mapper.facades.hssf.HSSFWorkbookFacade;

public class HSSFMapper extends Mapper{
	public IWorkbook doMap(String distributorName, ISheet externalSheet) throws MapperException{
		if(!this.getConfigurations().containsKey(distributorName)){
			throw new UnknowDistributorException();
		}
		
        HSSFWorkbook result = new HSSFWorkbook(); 
        ISheet resultSheet = new HSSFSheetFacade(result.createSheet(MAPPED_SHEET_NAME));
		
		Configuration distributorConfiguration = getConfigurations().get(distributorName);
		
		Integer codeColumnIndex = getCodeColumnIndex(distributorConfiguration, externalSheet);
		if(codeColumnIndex==null){
			throw new BadHeadersException();
		}
		
		copyFirstRow(externalSheet,resultSheet);
		
		Iterator<IRow> rowIterator = externalSheet.iterator();
		
		rowIterator.next(); //Ignore the first row
		Integer rowNumber = 1;
		List<IRow> unmappedCodes = new LinkedList<IRow>();
		
        while (rowIterator.hasNext()) 
        {
            IRow row = rowIterator.next();
                        
            if(distributorConfiguration.getMappings().containsKey(row.getCell(codeColumnIndex).toString())){
            	
                IRow newRow = resultSheet.createRow(rowNumber);

	            String code = distributorConfiguration.getMappings().get(row.getCell(codeColumnIndex).toString()) + distributorConfiguration.getSuffix();
	
	            newRow.createCell(0).setCellValue(code);
	            
	            Iterator<ICell> cellIterator = row.iterator();
	            Integer columnIndex = 1;
	            while (cellIterator.hasNext())
	            {
	                ICell cell = cellIterator.next();
	                
	                newRow.createCell(columnIndex).setCellValue(cell.toString());
	                
	                columnIndex++;
	            }
            } else {
            	unmappedCodes.add(row);
            }
            
            rowNumber++;
        }
        
        IWorkbook workbookResult = new HSSFWorkbookFacade(result);
        
        if(!unmappedCodes.isEmpty()){
        	throw new UnmappedCodesException(workbookResult, unmappedCodes);
        }
        
		return workbookResult;
	}
	
	private void copyFirstRow(ISheet externalSheet, ISheet resultSheet) {
		Iterator<IRow> rowIterator = externalSheet.iterator();
		Integer rowNumber = 0;
		
        if (rowIterator.hasNext()) 
        {
            IRow row = rowIterator.next();
            IRow newRow = resultSheet.createRow(rowNumber);
            
            newRow.createCell(0).setCellValue(INTERNAL_CODE_COLUMN_NAME);

            //For each row, iterate through all the columns
            Iterator<ICell> cellIterator = row.iterator();
            Integer columnIndex = 1;
            while (cellIterator.hasNext()) 
            {
                ICell cell = cellIterator.next();
                
                newRow.createCell(columnIndex).setCellValue(cell.toString());
                
                columnIndex++;
                
            }
            
            rowNumber++;
        }
	}

	public Integer getCodeColumnIndex(Configuration distributorConfiguration, ISheet externalSheet){
        Iterator<IRow> rowIterator = externalSheet.iterator();
		
        Integer codeColumnIndex = null;
		if(rowIterator.hasNext()){
			IRow headers = rowIterator.next();
			
            Iterator<ICell> cellIterator = headers.iterator();
            codeColumnIndex = 0;

            while (cellIterator.hasNext()) 
            {
                ICell cell = cellIterator.next();

                if(cell.toString().equals(distributorConfiguration.getExternalColumnName())){
                	return codeColumnIndex++;
                };
                
                codeColumnIndex++;
            }
		}
		
		return null;
	}
}
