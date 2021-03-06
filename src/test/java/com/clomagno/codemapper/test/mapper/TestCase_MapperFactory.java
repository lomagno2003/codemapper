package com.clomagno.codemapper.test.mapper;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Before;
import org.junit.Test;

import com.clomagno.codemapper.mapper.Configuration;
import com.clomagno.codemapper.mapper.ISheet;
import com.clomagno.codemapper.mapper.exceptions.MapperException;
import com.clomagno.codemapper.mapper.facades.hssf.HSSFSheetFacade;
import com.clomagno.codemapper.mapper.facades.hssf.HSSFWorkbookFacade;
import com.clomagno.codemapper.mapper.impls.HSSFMapper;
import com.clomagno.codemapper.mapper.impls.MapperFactory;

public class TestCase_MapperFactory {
	@Before
	public void setUp() throws Exception {
		//TODO Stub-method
	}
	
	@Test
	public void testGetMapperFromFile() throws IOException, MapperException{
		HSSFWorkbook workbookConfiguration = new HSSFWorkbook(TestCase_MapperFactory.class.getResourceAsStream("mapping1.xls"));
		HSSFMapper mapper = MapperFactory.getMapperFromWorkbook(workbookConfiguration);

        HSSFWorkbook externalWorkbook = new HSSFWorkbook(TestCase_MapperFactory.class.getResourceAsStream("distributor1.xls"));
        
        ISheet mappedSheet = mapper.doMap("distributor1", new HSSFWorkbookFacade(externalWorkbook)).getSheet(HSSFMapper.MAPPED_SHEET_NAME);
        
		String distributor1Expected[][]={
        		{HSSFMapper.INTERNAL_CODE_COLUMN_NAME,"ec1","price","Product"},
        		{"c1_d1","c10","100","Pera"},
        		{"c2_d1","c11","100","Manzana"},
        		{"c3_d1","c12","100","Banana"}
        		};
        
        TestCase_Mapper.testSheet(mappedSheet, distributor1Expected);
	}
	
	@Test
	public void testFindHeaderIndex(){
		String distributor1[][]={
        		{"ColumnA","ColumnB","ColumnC"},
        		{"c10","100.0","Pera"},
        		{"c11","100.0","Manzana"},
        		{"c12","100.0","Banana"}
        		};
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = HSSFSheetFactory.createSheetFromArray(workbook, "my sheet", distributor1);
		
		Integer indexColumnA = MapperFactory.findHeaderIndex(sheet, "ColumnA");
		Integer indexColumnB = MapperFactory.findHeaderIndex(sheet, "ColumnB");
		Integer indexColumnC = MapperFactory.findHeaderIndex(sheet, "ColumnC");
		
		assertEquals(new Integer(0), indexColumnA);
		assertEquals(new Integer(1), indexColumnB);
		assertEquals(new Integer(2), indexColumnC);
	}
	
	@Test
	public void testConfigureDistributorMappings(){
		Configuration configuration = new Configuration();
		
		configuration.setExternalColumnName("ExternalCode");
		configuration.setSuffix("-sufix");
		configuration.setDistributorName("distributorX");
		
		String distributor1[][]={
        		{"distributorX",HSSFMapper.INTERNAL_CODE_COLUMN_NAME},
        		{"10","1"},
        		{"11","2"},
        		{"12","3"}
        		};
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = HSSFSheetFactory.createSheetFromArray(workbook, "my sheet", distributor1);
		
		MapperFactory.configureDistributorMappings(configuration, sheet);
		
		String mapFor10 = configuration.getMappings().get("10");
		String mapFor11 = configuration.getMappings().get("11");
		String mapFor12 = configuration.getMappings().get("12");
		
		assertEquals("1", mapFor10);
		assertEquals("2", mapFor11);
		assertEquals("3", mapFor12);
	}

	@Test
	public void testGetMapperFromWorkbook() throws MapperException {
		String generalConfiguration[][]={
        		{MapperFactory.DISTRIBUTOR_COLUMN_NAME,MapperFactory.SUFFIX_COLUMN_NAME,MapperFactory.EXTERNAL_CODE_COLUMN_NAME},
        		{"distributor1","-d1","code-d1"}
        		};
		
		String distributor1Maps[][]={
        		{"distributor1",HSSFMapper.INTERNAL_CODE_COLUMN_NAME},
        		{"10","1"},
        		{"11","2"},
        		{"12","3"}
        		};
		
		String distributor1[][]={
        		{"code-d1","Price","Product"},
        		{"10","100.0","Pera"},
        		{"11","100.0","Manzana"},
        		{"12","100.0","Banana"}
        		};

		String distributor1Expected[][]={
        		{HSSFMapper.INTERNAL_CODE_COLUMN_NAME,"code-d1","Price","Product"},
        		{"1-d1","10","100.0","Pera"},
        		{"2-d1","11","100.0","Manzana"},
        		{"3-d1","12","100.0","Banana"}
        		};
		
		HSSFWorkbook workbookConfiguration = new HSSFWorkbook();
		HSSFSheetFactory.createSheetFromArray(workbookConfiguration, MapperFactory.GENERAL_CONFIGURATION_SHEET_NAME, generalConfiguration);
		HSSFSheetFactory.createSheetFromArray(workbookConfiguration, MapperFactory.MAPPINGS_SHEET_NAME, distributor1Maps);

        HSSFMapper mapper = MapperFactory.getMapperFromWorkbook(workbookConfiguration);
        
        HSSFWorkbook distributorDataWorkbook = new HSSFWorkbook();
        
        HSSFSheet externalSheet = HSSFSheetFactory.createSheetFromArray(distributorDataWorkbook, "distributor1", distributor1);
        ISheet mappedSheet = mapper.doMap("distributor1", new HSSFSheetFacade(externalSheet)).getSheet(HSSFMapper.MAPPED_SHEET_NAME);
        
        TestCase_Mapper.testSheet(mappedSheet, distributor1Expected);
	}
}
