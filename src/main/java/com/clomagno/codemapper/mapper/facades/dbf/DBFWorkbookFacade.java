package com.clomagno.codemapper.mapper.facades.dbf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
	private InputStream inputStream;

	public DBFWorkbookFacade(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public Iterator<ISheet> iterator() {
		try {
			//Copy of the InputStream
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			
			while ((n = inputStream.read(buf)) >= 0)
			    baos.write(buf, 0, n);

			inputStream = new ByteArrayInputStream(baos.toByteArray());

			return new DBFSheetIterator(new ByteArrayInputStream(baos.toByteArray()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ISheet getSheet(String distributor) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			
			while ((n = inputStream.read(buf)) >= 0)
			    baos.write(buf, 0, n);
			
			inputStream = new ByteArrayInputStream(baos.toByteArray());
			
			return new DBFSheetFacade(new ByteArrayInputStream(baos.toByteArray()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void write(OutputStream outputStream) throws IOException {
		// TODO Auto-generated method stub
	}
}