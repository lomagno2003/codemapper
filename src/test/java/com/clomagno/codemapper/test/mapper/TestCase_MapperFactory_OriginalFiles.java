package com.clomagno.codemapper.test.mapper;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import com.clomagno.codemapper.mapper.ICell;
import com.clomagno.codemapper.mapper.IRow;
import com.clomagno.codemapper.mapper.IWorkbook;
import com.clomagno.codemapper.mapper.facades.dbf.DBFWorkbookFacade;
import com.clomagno.codemapper.mapper.facades.hssf.HSSFWorkbookFacade;

public class TestCase_MapperFactory_OriginalFiles {
	private String getCell(String sheet, Integer rowIndex, Integer columnIndex,
			IWorkbook workbook) {
		Integer rowNumber = 0;
		for(IRow row:workbook.getSheet(sheet)){
			if(rowNumber.equals(rowIndex)){
				Integer cellNumber = 0;
				for(ICell cell:row){
					if(cellNumber == columnIndex){						
						return cell.toString();
					}
					cellNumber++;
				}
				throw new NullPointerException();
			}
			
			rowNumber++;
		}
		throw new NullPointerException();
	}

	@Before
	public void setUp() throws Exception {
		// TODO Stub-method
	}

	@Test
	public void testRandomCellsFromOriginalFile_DBF() throws IOException {
		InputStream inputStream = TestCase_MapperFactory_OriginalFiles.class
				.getResourceAsStream("VW.DBF");

		IWorkbook workbook = new DBFWorkbookFacade(inputStream);

		assertEquals(" 5065", getCell("articulos", 89, 5, workbook));

		assertEquals("PROTECTOR SUP.CORREA DIST.(ORIG) GOL/POLO/CADDY **",
				getCell("articulos", 151, 1, workbook));

		assertEquals("123.48".toUpperCase(),
				getCell("articulos", 11511, 3, workbook).toUpperCase());
	}

	@Test
	public void testRandomCellsFromOriginalFile_XLS() throws IOException {
		IWorkbook externalWorkbook = new HSSFWorkbookFacade(
				TestCase_MapperFactory_OriginalFiles.class
						.getResourceAsStream("Balsamo 24-07.xls"));

		assertEquals("8.9", getCell("articulos", 17, 4, externalWorkbook));

		assertEquals("857632B",
				getCell("articulos", 37551, 6, externalWorkbook));

		assertEquals("08/05/2007".toUpperCase(),
				getCell("articulos", 30381, 14, externalWorkbook).toUpperCase());
	}
}
