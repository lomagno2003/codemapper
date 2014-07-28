package com.clomagno.codemapper.mapper.facades.dbf;

import java.util.Iterator;

import org.jamel.dbf.DbfReader;

import com.clomagno.codemapper.mapper.IRow;

public class DBFRowIterator implements Iterator<IRow> {
	private DbfReader reader;

	public DBFRowIterator(DbfReader reader) {
		this.reader = reader;
	}

	public boolean hasNext() {
		return reader.canSeek();
	}

	public IRow next() {
		return new DBFRowFacade(reader.nextRecord());
	}

	public void remove() {
		// TODO Auto-generated method stub
	}

}
