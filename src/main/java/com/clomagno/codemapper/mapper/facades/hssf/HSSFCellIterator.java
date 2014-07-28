package com.clomagno.codemapper.mapper.facades.hssf;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.clomagno.codemapper.mapper.ICell;

public class HSSFCellIterator implements Iterator<ICell> {
	private Row row;
	
	private Integer actualCell;
	
	public HSSFCellIterator(Row row) {
		this.row = row;
		this.actualCell = 0;
	}

	public boolean hasNext() {
		return actualCell < row.getLastCellNum();
	}

	public ICell next() {
		Cell result = row.getCell(actualCell);
		actualCell++;
		return new HSSFCellFacade(result);
	}

	public void remove() {
		// TODO Auto-generated method stub
	}

}
