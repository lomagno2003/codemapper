package com.clomagno.codemapper.mapper.facades.hssf;

import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.clomagno.codemapper.mapper.IRow;

public class HSSFRowIterator implements Iterator<IRow>{
	private HSSFSheet sheet;
	
	private Integer actualRow;
	
	public HSSFRowIterator(HSSFSheet sheet){
		this.sheet = sheet;
		this.actualRow = 0;
	}
	
	public boolean hasNext() {
		return actualRow <= sheet.getLastRowNum();
	}

	public IRow next() {
		IRow result = new HSSFRowFacade(sheet.getRow(actualRow));
		actualRow++;
		return result;
	}

	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
