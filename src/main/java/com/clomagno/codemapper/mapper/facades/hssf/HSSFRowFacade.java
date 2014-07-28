package com.clomagno.codemapper.mapper.facades.hssf;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;

import com.clomagno.codemapper.mapper.ICell;
import com.clomagno.codemapper.mapper.IRow;

public class HSSFRowFacade implements IRow {
	private Row row;
	
	public HSSFRowFacade(Row row) {
		this.row = row;
	}

	public Iterator<ICell> iterator() {
		return new HSSFCellIterator(row.cellIterator());
	}

	public ICell createCell(Integer cellIndex) {
		return new HSSFCellFacade(row.createCell(cellIndex));
	}

	public ICell getCell(Integer cellIndex) {
		return new HSSFCellFacade(row.getCell(cellIndex));
	}
}
