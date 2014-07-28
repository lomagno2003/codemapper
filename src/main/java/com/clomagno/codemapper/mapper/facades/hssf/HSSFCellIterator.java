package com.clomagno.codemapper.mapper.facades.hssf;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;

import com.clomagno.codemapper.mapper.ICell;

public class HSSFCellIterator implements Iterator<ICell> {
	private Iterator<Cell> iterator;
	
	public HSSFCellIterator(Iterator<Cell> iterator) {
		this.iterator = iterator;
	}

	public boolean hasNext() {
		return iterator.hasNext();
	}

	public ICell next() {
		return new HSSFCellFacade(iterator.next());
	}

	public void remove() {
		// TODO Auto-generated method stub
	}

}
