package com.clomagno.codemapper.mapper.facades.dbf;

import com.clomagno.codemapper.mapper.ICell;

public class DBFCellFacade implements ICell {
	private Object content;

	public DBFCellFacade(Object object) {
		this.content = object;
	}

	public void setCellValue(String value) {
		content = value;
	}
	
	@Override
	public String toString(){
		return content.toString();
	}

}
