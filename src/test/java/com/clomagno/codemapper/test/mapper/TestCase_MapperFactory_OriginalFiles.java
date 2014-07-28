package com.clomagno.codemapper.test.mapper;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.jamel.dbf.processor.DbfProcessor;
import org.jamel.dbf.processor.DbfRowMapper;
import org.jamel.dbf.processor.DbfRowProcessor;
import org.jamel.dbf.utils.DbfUtils;
import org.junit.Before;
import org.junit.Test;

import com.clomagno.codemapper.mapper.Configuration;
import com.clomagno.codemapper.mapper.Mapper;
import com.clomagno.codemapper.mapper.MapperFactory;

public class TestCase_MapperFactory_OriginalFiles {
	private String getCell(String sheet, Integer row, Integer column,
			HSSFWorkbook workbook) {
		return workbook.getSheet(sheet).getRow(row).getCell(column).toString();
	}

	@Before
	public void setUp() throws Exception {
		// TODO Stub-method
	}

	@Test
	public void testRandomCellsFromOriginalFile_DBF() throws IOException{
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		File dbFile = null;
	 
		try {
			// read this file into InputStream
			inputStream = TestCase_MapperFactory_OriginalFiles.class.getResourceAsStream("VW.DBF");
	 
			// write the inputStream to a FileOutputStream
			dbFile = new File("dbFile.dbf");
			outputStream = new FileOutputStream(dbFile);
	 
			int read = 0;
			byte[] bytes = new byte[1024];
	 
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
	 
			String dbfInfo = DbfProcessor.readDbfInfo(dbFile);	
	        System.out.println(dbfInfo);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	 
			}
		}
	}

	@Test
	public void testRandomCellsFromOriginalFile_XLS() throws IOException {
		HSSFWorkbook externalWorkbook = new HSSFWorkbook(
				TestCase_MapperFactory_OriginalFiles.class
						.getResourceAsStream("Balsamo 24-07.xls"));

		assertEquals("8.9", getCell("articulos", 17, 4, externalWorkbook));

		assertEquals("857632B",
				getCell("articulos", 37551, 6, externalWorkbook));

		assertEquals("08-May-2007".toUpperCase(),
				getCell("articulos", 30381, 14, externalWorkbook).toUpperCase());
	}
}
