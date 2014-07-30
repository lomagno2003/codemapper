package com.clomagno.codemapper.mapper.facades.dbf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.jamel.dbf.DbfReader;

import com.clomagno.codemapper.mapper.IRow;
import com.clomagno.codemapper.mapper.ISheet;

public class DBFSheetFacade implements ISheet {
	private InputStream inputStream;
	
	public DBFSheetFacade(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public Iterator<IRow> iterator() {
		try {
			//Copy of the InputStream
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			
			while ((n = inputStream.read(buf)) >= 0)
			    baos.write(buf, 0, n);
			
			inputStream = new ByteArrayInputStream(baos.toByteArray());
			
			return new DBFRowIterator(new DbfReader(new ByteArrayInputStream(baos.toByteArray())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public IRow createRow(Integer rowIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		return "DBFSheet";
	}

}
