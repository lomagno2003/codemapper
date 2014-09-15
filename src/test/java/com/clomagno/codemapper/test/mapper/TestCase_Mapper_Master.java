package com.clomagno.codemapper.test.mapper;

import static org.junit.Assert.*;

import java.io.IOException;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.junit.Test;

import com.clomagno.codemapper.mapper.ICell;
import com.clomagno.codemapper.mapper.IRow;
import com.clomagno.codemapper.mapper.IWorkbook;
import com.clomagno.codemapper.mapper.exceptions.MapperException;
import com.clomagno.codemapper.mapper.facades.hssf.HSSFWorkbookFacade;
import com.clomagno.codemapper.mapper.impls.HSSFMapper;
import com.clomagno.codemapper.mapper.impls.MapperFactory;

/**
 * This test case contemplates the case in which the configuration file got blank columns
 * 
 * @author clomagno
 *
 */
public class TestCase_Mapper_Master {
	@Test
	public void testRandomCellsFromOriginalFile_XLS() throws IOException, MapperException {
		/* Do the mapping */
		HSSFWorkbook workbookConfiguration = new HSSFWorkbook(TestCase_MapperFactory.class.getResourceAsStream("configurationFile MASTER.xls"));
		assertNotNull(workbookConfiguration);
		HSSFWorkbook externalWorkbook = new HSSFWorkbook(TestCase_MapperFactory.class.getResourceAsStream("BALSAMO MASTER 2.xls"));
		assertNotNull(externalWorkbook);		
		HSSFMapper mapper = MapperFactory.getMapperFromWorkbook(workbookConfiguration);
        
        IWorkbook mappedSheet = mapper.doMap("Balsamo", new HSSFWorkbookFacade(externalWorkbook).getSheet("articulos"));

        /* Do random tests */
        
		assertEquals("018885_d2", getCell(HSSFMapper.MAPPED_SHEET_NAME, 5, 0, mappedSheet));
	}
	
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
}
