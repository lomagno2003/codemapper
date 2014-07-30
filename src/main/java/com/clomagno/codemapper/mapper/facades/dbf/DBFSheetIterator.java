package com.clomagno.codemapper.mapper.facades.dbf;

import java.io.InputStream;
import java.util.Iterator;

import com.clomagno.codemapper.mapper.ISheet;

public class DBFSheetIterator implements Iterator<ISheet> {
	private InputStream inputStream;
	
	private Integer sheetIndex;

	public DBFSheetIterator(InputStream inputStream) {
		this.inputStream = inputStream;
		this.sheetIndex = 0;
	}

	public boolean hasNext() {
		return sheetIndex == 0;
	}

	public ISheet next() {
		sheetIndex++;
		return new DBFSheetFacade(inputStream);
	}

	public void remove() {
		// TODO Auto-generated method stub
	}

}
