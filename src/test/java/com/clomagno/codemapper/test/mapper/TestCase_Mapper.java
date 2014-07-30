package com.clomagno.codemapper.test.mapper;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Before;
import org.junit.Test;

import com.clomagno.codemapper.mapper.Configuration;
import com.clomagno.codemapper.mapper.ICell;
import com.clomagno.codemapper.mapper.IRow;
import com.clomagno.codemapper.mapper.ISheet;
import com.clomagno.codemapper.mapper.IWorkbook;
import com.clomagno.codemapper.mapper.exceptions.MapperException;
import com.clomagno.codemapper.mapper.facades.hssf.HSSFSheetFacade;
import com.clomagno.codemapper.mapper.impls.HSSFMapper;

public class TestCase_Mapper {
	private HSSFMapper mapper;
	
	@Before
	public void setUp() throws Exception {
		mapper = new HSSFMapper();
	}

	@Test
	public void testDoMap() throws MapperException {
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
	    HSSFSheet sheet = workbook.createSheet(HSSFMapper.MAPPED_SHEET_NAME);
	  
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
	    
	    IWorkbook mapped = mapper.doMap("FruitTest", new HSSFSheetFacade(sheet));
        ISheet mappedSheet = mapped.getSheet(HSSFMapper.MAPPED_SHEET_NAME);
        
        String expected[][]={
        		{HSSFMapper.INTERNAL_CODE_COLUMN_NAME,"ExternalCode","Price","Product"},
        		{"1-sufix","10","100","Pera"},
        		{"2-sufix","11","100","Manzana"},
        		{"3-sufix","12","100","Banana"}
        		};
        
        testSheet(mappedSheet, expected);
	}
	
	public static void testSheet(ISheet mappedSheet, String [][] expected){
		Integer rowIndex = 0;
		for(IRow row:mappedSheet){
			Integer cellIndex = 0;
			for(ICell cell:row){
				assertNotNull("For ["+rowIndex+"]["+cellIndex+"]", cell);
				
				assertEquals("For ["+rowIndex+"]["+cellIndex+"]",expected[rowIndex][cellIndex].toString(), cell.toString());
				cellIndex++;
			}
			rowIndex++;
		}
	}
}
