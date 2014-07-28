package com.clomagno.codemapper.mapper.facades.dbf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.jamel.dbf.DbfReader;
import org.jamel.dbf.processor.DbfProcessor;

import com.clomagno.codemapper.mapper.IMapperFactory;
import com.clomagno.codemapper.mapper.ISheet;
import com.clomagno.codemapper.mapper.IWorkbook;
import com.clomagno.codemapper.mapper.Mapper;
import com.clomagno.codemapper.mapper.exceptions.BadHeadersException;
import com.clomagno.codemapper.mapper.exceptions.MapperException;
import com.clomagno.codemapper.test.mapper.TestCase_MapperFactory_OriginalFiles;

public class DBFWorkbookFacade implements IWorkbook {
	private DbfReader reader;

	public DBFWorkbookFacade(InputStream inputStream) {
		reader = new DbfReader(inputStream);
	}

	public Iterator<ISheet> iterator() {
		return new DBFSheetIterator(reader);
	}

	public ISheet getSheet(String distributor) {
		return new DBFSheetFacade(reader);
	}

	public void write(OutputStream outputStream) throws IOException {
		// TODO Auto-generated method stub
	}
}
