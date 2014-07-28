package com.clomagno.codemapper.mapper.facades.hssf;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;

import com.clomagno.codemapper.mapper.IRow;

public class HSSFRowIterator implements Iterator<IRow>{
	private Iterator<Row> iterator;
	
	public HSSFRowIterator(Iterator<Row> iterator){
		this.iterator = iterator;
	}
	
	public boolean hasNext() {
		return iterator.hasNext();
	}

	public IRow next() {
		return new HSSFRowFacade(iterator.next());
	}

	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
