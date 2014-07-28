package com.clomagno.codemapper.mapper.facades.dbf;

import java.util.Iterator;

import org.jamel.dbf.DbfReader;

import com.clomagno.codemapper.mapper.IRow;

public class DBFRowIterator implements Iterator<IRow> {
	private DbfReader reader;
	
	private Object[] auxRecord;

	public DBFRowIterator(DbfReader reader) {
		this.reader = reader;
		this.auxRecord = reader.nextRecord();
		
	}

	public boolean hasNext() {
		return this.auxRecord != null;
	}

	public IRow next() {
		Object[] result = auxRecord;
		auxRecord=reader.nextRecord();
		return new DBFRowFacade(result);
	}

	public void remove() {
		// TODO Auto-generated method stub
	}

}
