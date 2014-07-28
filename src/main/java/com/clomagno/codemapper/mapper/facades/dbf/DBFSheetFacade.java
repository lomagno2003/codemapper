package com.clomagno.codemapper.mapper.facades.dbf;

import java.util.Iterator;

import org.jamel.dbf.DbfReader;

import com.clomagno.codemapper.mapper.IRow;
import com.clomagno.codemapper.mapper.ISheet;

public class DBFSheetFacade implements ISheet {
	private DbfReader reader;
	
	public DBFSheetFacade(DbfReader reader) {
		this.reader = reader;
	}

	public Iterator<IRow> iterator() {
		return new DBFRowIterator(reader);
	}

	public IRow createRow(Integer rowIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
