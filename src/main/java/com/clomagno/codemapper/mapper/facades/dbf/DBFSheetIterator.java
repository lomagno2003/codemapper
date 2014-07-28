package com.clomagno.codemapper.mapper.facades.dbf;

import java.util.Iterator;

import org.jamel.dbf.DbfReader;

import com.clomagno.codemapper.mapper.ISheet;

public class DBFSheetIterator implements Iterator<ISheet> {
	private DbfReader reader;

	public DBFSheetIterator(DbfReader reader) {
		this.reader = reader;
	}

	public boolean hasNext() {
		return false;
	}

	public ISheet next() {
		return new DBFSheetFacade(reader);
	}

	public void remove() {
		// TODO Auto-generated method stub
	}

}
