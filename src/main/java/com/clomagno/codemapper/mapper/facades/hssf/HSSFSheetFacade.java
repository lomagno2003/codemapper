package com.clomagno.codemapper.mapper.facades.hssf;

import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.clomagno.codemapper.mapper.IRow;
import com.clomagno.codemapper.mapper.ISheet;

public class HSSFSheetFacade implements ISheet{
	private HSSFSheet sheet;

	public HSSFSheetFacade(HSSFSheet sheet) {
		this.sheet = sheet;
	}

	public Iterator<IRow> iterator() {
		return new HSSFRowIterator(sheet.rowIterator());
	}

	public IRow createRow(Integer rowIndex) {
		return new HSSFRowFacade(sheet.createRow(rowIndex));
	}

	public String getName() {
		return sheet.getSheetName();
	}
}
