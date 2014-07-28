package com.clomagno.codemapper.mapper.facades.dbf;

import java.util.Iterator;

import org.jamel.dbf.DbfReader;

import com.clomagno.codemapper.mapper.ISheet;

public class DBFSheetIterator implements Iterator<ISheet> {
	private DbfReader reader;
	
	private Integer sheetIndex;

	public DBFSheetIterator(DbfReader reader) {
		this.reader = reader;
		this.sheetIndex = 0;
	}

	public boolean hasNext() {
		return sheetIndex == 0;
	}

	public ISheet next() {
		sheetIndex++;
		return new DBFSheetFacade(reader);
	}

	public void remove() {
		// TODO Auto-generated method stub
	}

}
