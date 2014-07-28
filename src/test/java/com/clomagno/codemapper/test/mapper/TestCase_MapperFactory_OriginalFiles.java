package com.clomagno.codemapper.test.mapper;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.clomagno.codemapper.mapper.MapperFactory;

public class TestCase_MapperFactory_OriginalFiles {
	private String getCell(String sheet, Integer row, Integer column, HSSFWorkbook workbook){
		return workbook.getSheet(sheet).getRow(row).getCell(column).toString();
	}
	@Before
	public void setUp() throws Exception {
		//TODO Stub-method
	}
	
	@Test
	public void testRandomCellsFromOriginalFile_DBF() throws IOException{
        HSSFWorkbook externalWorkbook = new HSSFWorkbook(TestCase_MapperFactory_OriginalFiles.class.getResourceAsStream("VW.DBF"));
        System.out.println(externalWorkbook.getSheetName(0));
	}
	
	@Test
	public void testRandomCellsFromOriginalFile_XLS() throws IOException{
        HSSFWorkbook externalWorkbook = new HSSFWorkbook(TestCase_MapperFactory_OriginalFiles.class.getResourceAsStream("Balsamo 24-07.xls"));
        
        assertEquals("8.9",getCell("articulos",17,4,externalWorkbook));
        
        assertEquals("857632B",getCell("articulos",37551,6,externalWorkbook));
        
        assertEquals("08-May-2007".toUpperCase(),getCell("articulos",30381,14,externalWorkbook).toUpperCase());
	}
}
