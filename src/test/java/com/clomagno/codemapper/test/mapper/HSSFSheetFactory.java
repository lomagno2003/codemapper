package com.clomagno.codemapper.test.mapper;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class HSSFSheetFactory {
	public static HSSFSheet createSheetFromArray(HSSFWorkbook workbook, String sheetName, String [][] array){
		HSSFSheet result = workbook.createSheet(sheetName);
		
		for(int i=0;i<array.length;i++){
			Row row = result.createRow(i);
			for(int j=0;j<array[i].length;j++){
				Cell cell = row.createCell(j);
				cell.setCellValue(array[i][j]);
			}
		}

		return result;
	}
}
