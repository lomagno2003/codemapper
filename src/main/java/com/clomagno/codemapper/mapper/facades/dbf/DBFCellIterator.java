package com.clomagno.codemapper.mapper.facades.dbf;

import java.util.Iterator;

import com.clomagno.codemapper.mapper.ICell;

public class DBFCellIterator implements Iterator<ICell> {
	private Object[] cells;
	
	private Integer actualCell;

	public DBFCellIterator(Object[] cells) {
		this.cells = cells;
		this.actualCell = 0;
	}

	public boolean hasNext() {
		return actualCell < cells.length;
	}

	public ICell next() {
		ICell result = new DBFCellFacade(cells[actualCell]);
		actualCell++;
		return result;
	}

	public void remove() {
		// TODO Auto-generated method stub
	}

}
