package com.clomagno.codemapper.mapper.facades.dbf;

import java.util.Iterator;

import org.jamel.dbf.DbfReader;

import com.clomagno.codemapper.mapper.IRow;

public class DBFRowIterator implements Iterator<IRow> {
	private DbfReader reader;
	
	private Boolean iteratorOnHeader = true;
	
	private Object[] auxRecord;

	public DBFRowIterator(DbfReader reader) {
		this.reader = reader;
		this.auxRecord = reader.nextRecord();
		
	}

	public boolean hasNext() {
		return this.auxRecord != null;
	}

	public IRow next() {
		Object[] result = null;
		
		if(iteratorOnHeader){
			iteratorOnHeader = false;
			//Generate header row
			result = new Object[reader.getHeader().getFieldsCount()];
			for(int i=0;i<reader.getHeader().getFieldsCount();i++){
				result[i] = reader.getHeader().getField(i).getName();
			}
		} else {
			result = auxRecord;
			auxRecord=reader.nextRecord();
		}
		
		return new DBFRowFacade(result);
	}

	public void remove() {
		// TODO Auto-generated method stub
	}

}
