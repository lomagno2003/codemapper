package com.clomagno.codemapper;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Before;
import org.junit.Test;

import com.clomagno.codemapper.mapper.Configuration;
import com.clomagno.codemapper.mapper.Mapper;

public class TestCase_Mapper {
	private Mapper mapper;
	
	@Before
	public void setUp() throws Exception {
		mapper = new Mapper();
	}

	@Test
	public void testDoMap() {
		Configuration configuration = new Configuration();
		
		Map<String,String> mappings = new HashMap<String,String>();
		mappings.put("10", "1");
		mappings.put("11", "2");
		mappings.put("12", "3");
		
		configuration.setMappings(mappings);
		configuration.setExternalColumnName("ExternalCode");
		configuration.setSuffix("-sufix");
		
		mapper.getConfigurations().put("FruitTest", configuration);
		
		HSSFWorkbook workbook = new HSSFWorkbook();
	    HSSFSheet sheet = workbook.createSheet(Mapper.MAPPED_SHEET_NAME);
	  
	    Row header = sheet.createRow(0);
	    header.createCell(0).setCellValue("ExternalCode");
	    header.createCell(1).setCellValue("Price");
	    header.createCell(2).setCellValue("Product");
	      
	    Row dataRow = sheet.createRow(1);
	    dataRow.createCell(0).setCellValue("10");
	    dataRow.createCell(1).setCellValue(100.0);
	    dataRow.createCell(2).setCellValue("Pera");
	    
	    dataRow = sheet.createRow(2);
	    dataRow.createCell(0).setCellValue("11");
	    dataRow.createCell(1).setCellValue(100.0);
	    dataRow.createCell(2).setCellValue("Manzana");
	    
	    dataRow = sheet.createRow(3);
	    dataRow.createCell(0).setCellValue("12");
	    dataRow.createCell(1).setCellValue(100.0);
	    dataRow.createCell(2).setCellValue("Banana");
	    
	    
	    /**
	     * TEST
	     */
	    
	    HSSFWorkbook mapped = mapper.doMap("FruitTest", sheet);
        HSSFSheet mappedSheet = mapped.getSheet(Mapper.MAPPED_SHEET_NAME);
        
        String expected[][]={
        		{Mapper.INTERNAL_CODE_COLUMN_NAME,"ExternalCode","Price","Product"},
        		{"1-sufix","10","100.0","Pera"},
        		{"2-sufix","11","100.0","Manzana"},
        		{"3-sufix","12","100.0","Banana"}
        		};
        
        testSheet(mappedSheet, expected);
	}
	
	public static void testSheet(HSSFSheet mappedSheet, String [][] expected){
        for(int i=0;i<3;i++){
        	Row row = mappedSheet.getRow(i);
        	for(int j=0;j<3;j++){
        		
        		Cell cell = row.getCell(j);
        		
        		assertNotNull("For ["+i+"]["+j+"]", cell);
        		
        		String cellValue = null;
        		switch (cell.getCellType()) 
                {
                    case Cell.CELL_TYPE_NUMERIC:
                    	cellValue = Double.toString(cell.getNumericCellValue());
                        break;
                    case Cell.CELL_TYPE_STRING:
                    	cellValue = cell.getStringCellValue();
                        break;
                    default:
                    	throw new IllegalStateException("Type not recognized");
                }
        		System.out.println("For ["+i+"]["+j+"]"+cellValue);
        		assertEquals("For ["+i+"]["+j+"]",expected[i][j], cellValue);
        	}
        }
	}
}
