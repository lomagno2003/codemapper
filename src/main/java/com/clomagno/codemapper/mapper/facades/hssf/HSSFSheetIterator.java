package com.clomagno.codemapper.mapper.facades.hssf;

import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.clomagno.codemapper.mapper.ISheet;

public class HSSFSheetIterator implements Iterator<ISheet> {
	private HSSFWorkbook workbook;
	
	private Integer actualElement;

	public HSSFSheetIterator(HSSFWorkbook workbook) {
		this.workbook = workbook;
		this.actualElement = 0;
	}

	public boolean hasNext() {
		return actualElement<workbook.getNumberOfSheets();
	}

	public ISheet next() {
		ISheet result = new HSSFSheetFacade(workbook.getSheetAt(actualElement));
		actualElement++;
		return result;
	}

	public void remove() {
		// TODO Auto-generated method stub

	}

}
