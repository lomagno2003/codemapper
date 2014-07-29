package com.clomagno.codemapper.mapper.facades.dbf;

import java.util.Iterator;

import com.clomagno.codemapper.mapper.ICell;
import com.clomagno.codemapper.mapper.IRow;

public class DBFRowFacade implements IRow {
	private Object[] cells;

	public DBFRowFacade(Object[] cells) {
		this.cells = cells;
	}

	public Iterator<ICell> iterator() {
		return new DBFCellIterator(cells);
	}

	public ICell createCell(Integer cellIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICell getCell(Integer cellIndex) {
		return new DBFCellFacade(cells[cellIndex]);
	}

}
