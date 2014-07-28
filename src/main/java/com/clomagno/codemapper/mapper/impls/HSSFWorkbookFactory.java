package com.clomagno.codemapper.mapper.impls;

import java.io.File;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.jamel.dbf.DbfReader;

import com.clomagno.codemapper.mapper.exceptions.BadHeadersException;

public class HSSFWorkbookFactory {
	public static HSSFWorkbook getMapperFromDBF(File dbfFile, String sheetName) throws BadHeadersException {
		HSSFWorkbook tempWorkbook = new HSSFWorkbook();
		HSSFSheet tempSheet = tempWorkbook.createSheet(sheetName);
		DbfReader reader = new DbfReader(dbfFile);

		
		int rowNum = 0;
		Object[] row;
		while ((row = reader.nextRecord()) != null) {
			Row newRow = tempSheet.createRow(rowNum);
			
			for(int j=0;j<row.length;j++){
				newRow.createCell(j).setCellValue(row[j].toString());
			}
			
			rowNum++;
		}
		
		reader.close();
		
		return tempWorkbook;
	}
}
